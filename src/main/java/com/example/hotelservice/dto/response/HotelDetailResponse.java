package com.example.hotelservice.dto.response;

import java.util.List;

public class HotelDetailResponse {
    private Long id;
    private String name;
    private String description;
    private String brand;
    private AddressDto address;
    private ContactsDto contacts;
    private ArrivalTimeDto arrivalTime;
    private List<String> amenities;

    public HotelDetailResponse() {}

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private String brand;
        private AddressDto address;
        private ContactsDto contacts;
        private ArrivalTimeDto arrivalTime;
        private List<String> amenities;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder brand(String brand) { this.brand = brand; return this; }
        public Builder address(AddressDto address) { this.address = address; return this; }
        public Builder contacts(ContactsDto contacts) { this.contacts = contacts; return this; }
        public Builder arrivalTime(ArrivalTimeDto arrivalTime) { this.arrivalTime = arrivalTime; return this; }
        public Builder amenities(List<String> amenities) { this.amenities = amenities; return this; }

        public HotelDetailResponse build() {
            HotelDetailResponse r = new HotelDetailResponse();
            r.id = this.id; r.name = this.name; r.description = this.description;
            r.brand = this.brand; r.address = this.address; r.contacts = this.contacts;
            r.arrivalTime = this.arrivalTime; r.amenities = this.amenities;
            return r;
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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
    public ArrivalTimeDto getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(ArrivalTimeDto arrivalTime) { this.arrivalTime = arrivalTime; }
    public List<String> getAmenities() { return amenities; }
    public void setAmenities(List<String> amenities) { this.amenities = amenities; }
}
