package com.example.hotelservice.service.impl;

import com.example.hotelservice.dto.request.HotelCreateRequest;
import com.example.hotelservice.dto.response.HotelDetailResponse;
import com.example.hotelservice.dto.response.HotelShortResponse;
import com.example.hotelservice.entity.Amenity;
import com.example.hotelservice.entity.Hotel;
import com.example.hotelservice.exception.HotelNotFoundException;
import com.example.hotelservice.mapper.HotelMapper;
import com.example.hotelservice.repository.AmenityRepository;
import com.example.hotelservice.repository.HotelRepository;
import com.example.hotelservice.service.HotelService;
import com.example.hotelservice.specification.HotelSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final AmenityRepository amenityRepository;
    private final HotelMapper hotelMapper;

    public HotelServiceImpl(HotelRepository hotelRepository,
                            AmenityRepository amenityRepository,
                            HotelMapper hotelMapper) {
        this.hotelRepository = hotelRepository;
        this.amenityRepository = amenityRepository;
        this.hotelMapper = hotelMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<HotelShortResponse> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(hotelMapper::toShortResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public HotelDetailResponse getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException("Hotel with id " + id + " not found"));
        return hotelMapper.toDetailResponse(hotel);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HotelShortResponse> searchHotels(String name, String brand, String city, String country, String amenities) {
        Specification<Hotel> spec = Specification
                .where(HotelSpecification.hasName(name))
                .and(HotelSpecification.hasBrand(brand))
                .and(HotelSpecification.hasCity(city))
                .and(HotelSpecification.hasCountry(country))
                .and(HotelSpecification.hasAmenity(amenities));

        return hotelRepository.findAll(spec).stream()
                .map(hotelMapper::toShortResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public HotelShortResponse createHotel(HotelCreateRequest request) {
        Hotel hotel = hotelMapper.toEntity(request);
        Hotel saved = hotelRepository.save(hotel);
        return hotelMapper.toShortResponse(saved);
    }

    @Override
    @Transactional
    public void addAmenities(Long id, List<String> amenityNames) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException("Hotel with id " + id + " not found"));

        for (String name : amenityNames) {
            Amenity amenity = amenityRepository.findByName(name)
                    .orElseGet(() -> amenityRepository.save(
                            Amenity.builder().name(name).build()
                    ));
            hotel.getAmenities().add(amenity);
        }

        hotelRepository.save(hotel);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Long> getHistogram(String param) {
        List<Hotel> hotels = hotelRepository.findAll();

        return switch (param.toLowerCase()) {
            case "brand" -> hotels.stream()
                    .filter(h -> h.getBrand() != null)
                    .collect(Collectors.groupingBy(Hotel::getBrand, Collectors.counting()));

            case "city" -> hotels.stream()
                    .filter(h -> h.getAddress() != null && h.getAddress().getCity() != null)
                    .collect(Collectors.groupingBy(h -> h.getAddress().getCity(), Collectors.counting()));

            case "country" -> hotels.stream()
                    .filter(h -> h.getAddress() != null && h.getAddress().getCountry() != null)
                    .collect(Collectors.groupingBy(h -> h.getAddress().getCountry(), Collectors.counting()));

            case "amenities" -> hotels.stream()
                    .flatMap(h -> h.getAmenities().stream())
                    .collect(Collectors.groupingBy(Amenity::getName, Collectors.counting()));

            default -> throw new IllegalArgumentException(
                    "Invalid parameter: '" + param + "'. Allowed values: brand, city, country, amenities"
            );
        };
    }
}
