package com.example.hotelservice.service;

import com.example.hotelservice.dto.request.ArrivalTimeRequest;
import com.example.hotelservice.dto.request.HotelCreateRequest;
import com.example.hotelservice.dto.response.*;
import com.example.hotelservice.entity.*;
import com.example.hotelservice.exception.HotelNotFoundException;
import com.example.hotelservice.mapper.HotelMapper;
import com.example.hotelservice.repository.AmenityRepository;
import com.example.hotelservice.repository.HotelRepository;
import com.example.hotelservice.service.impl.HotelServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HotelServiceTest {

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private AmenityRepository amenityRepository;

    @Spy
    private HotelMapper hotelMapper;

    @InjectMocks
    private HotelServiceImpl hotelService;

    @Test
    void getAllHotels_shouldReturnListOfShortResponses() {
        when(hotelRepository.findAll()).thenReturn(List.of(buildHotel()));

        var result = hotelService.getAllHotels();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Test Hotel");
        assertThat(result.get(0).getPhone()).isEqualTo("+375 17 000-00-00");
    }

    @Test
    void getHotelById_shouldReturnDetailResponse() {
        when(hotelRepository.findById(1L)).thenReturn(Optional.of(buildHotel()));

        var result = hotelService.getHotelById(1L);

        assertThat(result.getName()).isEqualTo("Test Hotel");
        assertThat(result.getBrand()).isEqualTo("TestBrand");
        assertThat(result.getAddress()).isNotNull();
        assertThat(result.getContacts().getEmail()).isEqualTo("test@test.com");
    }

    @Test
    void getHotelById_shouldThrowWhenNotFound() {
        when(hotelRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> hotelService.getHotelById(99L))
                .isInstanceOf(HotelNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void createHotel_shouldReturnShortResponse() {
        Hotel hotel = buildHotel();
        when(hotelRepository.save(any())).thenReturn(hotel);

        var result = hotelService.createHotel(buildCreateRequest());

        assertThat(result.getName()).isEqualTo("Test Hotel");
        verify(hotelRepository).save(any());
    }

    @Test
    void addAmenities_shouldAddNewAmenity() {
        Hotel hotel = buildHotel();
        when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotel));
        when(amenityRepository.findByName("Pool")).thenReturn(Optional.empty());
        when(amenityRepository.save(any())).thenReturn(Amenity.builder().id(1L).name("Pool").build());

        hotelService.addAmenities(1L, List.of("Pool"));

        verify(hotelRepository).save(hotel);
        assertThat(hotel.getAmenities()).anyMatch(a -> a.getName().equals("Pool"));
    }

    @Test
    void addAmenities_shouldNotDuplicateExistingAmenity() {
        Amenity existing = Amenity.builder().id(1L).name("Free WiFi").build();
        Hotel hotel = buildHotel();
        hotel.getAmenities().add(existing);

        when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotel));
        when(amenityRepository.findByName("Free WiFi")).thenReturn(Optional.of(existing));

        hotelService.addAmenities(1L, List.of("Free WiFi"));

        assertThat(hotel.getAmenities()).hasSize(1);
    }

    @Test
    void addAmenities_shouldThrowWhenHotelNotFound() {
        when(hotelRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> hotelService.addAmenities(99L, List.of("Free WiFi")))
                .isInstanceOf(HotelNotFoundException.class);
    }

    @Test
    void getHistogram_byCity_shouldReturnGroupedResult() {
        when(hotelRepository.findAll()).thenReturn(List.of(buildHotel(), buildHotel()));

        var result = hotelService.getHistogram("city");

        assertThat(result).containsKey("Minsk");
        assertThat(result.get("Minsk")).isEqualTo(2L);
    }

    @Test
    void getHistogram_byAmenities_shouldReturnGroupedResult() {
        Hotel hotel = buildHotel();
        hotel.getAmenities().add(Amenity.builder().id(1L).name("Free WiFi").build());
        when(hotelRepository.findAll()).thenReturn(List.of(hotel));

        var result = hotelService.getHistogram("amenities");

        assertThat(result).containsKey("Free WiFi");
    }

    @Test
    void getHistogram_shouldThrowForInvalidParam() {
        assertThatThrownBy(() -> hotelService.getHistogram("invalid"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid parameter");
    }

    private Hotel buildHotel() {
        return Hotel.builder()
                .id(1L)
                .name("Test Hotel")
                .description("Test description")
                .brand("TestBrand")
                .address(Address.builder()
                        .id(1L).houseNumber(1).street("Test St")
                        .city("Minsk").country("Belarus").postCode("220000")
                        .build())
                .contacts(Contacts.builder()
                        .id(1L).phone("+375 17 000-00-00").email("test@test.com")
                        .build())
                .arrivalTime(ArrivalTime.builder()
                        .id(1L).checkIn("14:00").checkOut("12:00")
                        .build())
                .amenities(new HashSet<>())
                .build();
    }

    private HotelCreateRequest buildCreateRequest() {
        HotelCreateRequest request = new HotelCreateRequest();
        request.setName("Test Hotel");
        request.setBrand("TestBrand");
        request.setAddress(new AddressDto(1, "Test St", "Minsk", "Belarus", "220000"));
        request.setContacts(new ContactsDto("+375 17 000-00-00", "test@test.com"));
        request.setArrivalTime(new ArrivalTimeRequest("14:00", "12:00"));
        return request;
    }
}
