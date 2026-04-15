package com.example.hotelservice.dto.response;

public class ArrivalTimeDto {
    private String checkIn;
    private String checkOut;

    public ArrivalTimeDto() {}

    public ArrivalTimeDto(String checkIn, String checkOut) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String checkIn;
        private String checkOut;

        public Builder checkIn(String checkIn) { this.checkIn = checkIn; return this; }
        public Builder checkOut(String checkOut) { this.checkOut = checkOut; return this; }
        public ArrivalTimeDto build() { return new ArrivalTimeDto(checkIn, checkOut); }
    }

    public String getCheckIn() { return checkIn; }
    public void setCheckIn(String checkIn) { this.checkIn = checkIn; }
    public String getCheckOut() { return checkOut; }
    public void setCheckOut(String checkOut) { this.checkOut = checkOut; }
}
