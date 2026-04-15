package com.example.hotelservice.dto.response;

public class AddressDto {
    private Integer houseNumber;
    private String street;
    private String city;
    private String country;
    private String postCode;

    public AddressDto() {}

    public AddressDto(Integer houseNumber, String street, String city, String country, String postCode) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.country = country;
        this.postCode = postCode;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Integer houseNumber;
        private String street;
        private String city;
        private String country;
        private String postCode;

        public Builder houseNumber(Integer houseNumber) { this.houseNumber = houseNumber; return this; }
        public Builder street(String street) { this.street = street; return this; }
        public Builder city(String city) { this.city = city; return this; }
        public Builder country(String country) { this.country = country; return this; }
        public Builder postCode(String postCode) { this.postCode = postCode; return this; }
        public AddressDto build() { return new AddressDto(houseNumber, street, city, country, postCode); }
    }

    public Integer getHouseNumber() { return houseNumber; }
    public void setHouseNumber(Integer houseNumber) { this.houseNumber = houseNumber; }
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public String getPostCode() { return postCode; }
    public void setPostCode(String postCode) { this.postCode = postCode; }
}
