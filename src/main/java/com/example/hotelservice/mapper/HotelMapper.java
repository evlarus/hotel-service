package com.example.hotelservice.mapper;

import com.example.hotelservice.dto.request.HotelCreateRequest;
import com.example.hotelservice.dto.response.*;
import com.example.hotelservice.entity.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HotelMapper {

    public HotelShortResponse toShortResponse(Hotel hotel) {
        return HotelShortResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .description(hotel.getDescription())
                .address(formatAddress(hotel.getAddress()))
                .phone(hotel.getContacts() != null ? hotel.getContacts().getPhone() : null)
                .build();
    }

    public HotelDetailResponse toDetailResponse(Hotel hotel) {
        List<String> amenityNames = hotel.getAmenities().stream()
                .map(Amenity::getName)
                .sorted()
                .collect(Collectors.toList());

        return HotelDetailResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .description(hotel.getDescription())
                .brand(hotel.getBrand())
                .address(toAddressDto(hotel.getAddress()))
                .contacts(toContactsDto(hotel.getContacts()))
                .arrivalTime(toArrivalTimeDto(hotel.getArrivalTime()))
                .amenities(amenityNames)
                .build();
    }

    public Hotel toEntity(HotelCreateRequest request) {
        Address address = Address.builder()
                .houseNumber(request.getAddress().getHouseNumber())
                .street(request.getAddress().getStreet())
                .city(request.getAddress().getCity())
                .country(request.getAddress().getCountry())
                .postCode(request.getAddress().getPostCode())
                .build();

        Contacts contacts = Contacts.builder()
                .phone(request.getContacts().getPhone())
                .email(request.getContacts().getEmail())
                .build();

        ArrivalTime arrivalTime = ArrivalTime.builder()
                .checkIn(request.getArrivalTime().getCheckIn())
                .checkOut(request.getArrivalTime().getCheckOut())
                .build();

        return Hotel.builder()
                .name(request.getName())
                .description(request.getDescription())
                .brand(request.getBrand())
                .address(address)
                .contacts(contacts)
                .arrivalTime(arrivalTime)
                .amenities(new HashSet<>())
                .build();
    }

    private String formatAddress(Address address) {
        if (address == null) return null;
        return address.getHouseNumber() + " " + address.getStreet() + ", "
                + address.getCity() + ", " + address.getPostCode() + ", " + address.getCountry();
    }

    private AddressDto toAddressDto(Address address) {
        if (address == null) return null;
        return AddressDto.builder()
                .houseNumber(address.getHouseNumber())
                .street(address.getStreet())
                .city(address.getCity())
                .country(address.getCountry())
                .postCode(address.getPostCode())
                .build();
    }

    private ContactsDto toContactsDto(Contacts contacts) {
        if (contacts == null) return null;
        return ContactsDto.builder()
                .phone(contacts.getPhone())
                .email(contacts.getEmail())
                .build();
    }

    private ArrivalTimeDto toArrivalTimeDto(ArrivalTime arrivalTime) {
        if (arrivalTime == null) return null;
        return ArrivalTimeDto.builder()
                .checkIn(arrivalTime.getCheckIn())
                .checkOut(arrivalTime.getCheckOut())
                .build();
    }
}
