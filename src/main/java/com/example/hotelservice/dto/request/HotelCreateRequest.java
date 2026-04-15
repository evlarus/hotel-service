package com.example.hotelservice.dto.request;

import com.example.hotelservice.dto.response.AddressDto;
import com.example.hotelservice.dto.response.ContactsDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class HotelCreateRequest {

    @NotBlank(message = "name is required")
    private String name;

    private String description;

    @NotBlank(message = "brand is required")
    private String brand;

    @Valid
    @NotNull(message = "address is required")
    private AddressDto address;

    @Valid
    @NotNull(message = "contacts is required")
    private ContactsDto contacts;

    @Valid
    @NotNull(message = "arrivalTime is required")
    private ArrivalTimeRequest arrivalTime;

    public HotelCreateRequest() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public AddressDto getAddress() { return address; }
    public void setAddress(AddressDto address) { this.address = address; }
    public ContactsDto getContacts() { return contacts; }
    public void setContacts(ContactsDto contacts) { this.contacts = contacts; }
    public ArrivalTimeRequest getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(ArrivalTimeRequest arrivalTime) { this.arrivalTime = arrivalTime; }
}
