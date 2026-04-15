package com.example.hotelservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "arrival_times")
public class ArrivalTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "check_in")
    private String checkIn;

    @Column(name = "check_out")
    private String checkOut;

    public ArrivalTime() {}

    public ArrivalTime(Long id, String checkIn, String checkOut) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private String checkIn;
        private String checkOut;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder checkIn(String checkIn) { this.checkIn = checkIn; return this; }
        public Builder checkOut(String checkOut) { this.checkOut = checkOut; return this; }
        public ArrivalTime build() { return new ArrivalTime(id, checkIn, checkOut); }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCheckIn() { return checkIn; }
    public void setCheckIn(String checkIn) { this.checkIn = checkIn; }
    public String getCheckOut() { return checkOut; }
    public void setCheckOut(String checkOut) { this.checkOut = checkOut; }
}
