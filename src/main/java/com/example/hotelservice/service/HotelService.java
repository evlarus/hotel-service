package com.example.hotelservice.service;

import com.example.hotelservice.dto.request.HotelCreateRequest;
import com.example.hotelservice.dto.response.HotelDetailResponse;
import com.example.hotelservice.dto.response.HotelShortResponse;

import java.util.List;
import java.util.Map;

public interface HotelService {

    List<HotelShortResponse> getAllHotels();

    HotelDetailResponse getHotelById(Long id);

    List<HotelShortResponse> searchHotels(String name, String brand, String city, String country, String amenities);

    HotelShortResponse createHotel(HotelCreateRequest request);

    void addAmenities(Long id, List<String> amenities);

    Map<String, Long> getHistogram(String param);
}
