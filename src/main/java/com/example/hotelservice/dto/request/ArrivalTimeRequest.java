package com.example.hotelservice.dto.request;

import jakarta.validation.constraints.NotBlank;

public class ArrivalTimeRequest {

    @NotBlank(message = "checkIn is required")
    private String checkIn;

    private String checkOut;

    public ArrivalTimeRequest() {}

    public ArrivalTimeRequest(String checkIn, String checkOut) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public String getCheckIn() { return checkIn; }
    public void setCheckIn(String checkIn) { this.checkIn = checkIn; }
    public String getCheckOut() { return checkOut; }
    public void setCheckOut(String checkOut) { this.checkOut = checkOut; }
}
