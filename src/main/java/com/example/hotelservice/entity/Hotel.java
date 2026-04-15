package com.example.hotelservice.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "brand")
    private String brand;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "contacts_id")
    private Contacts contacts;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "arrival_time_id")
    private ArrivalTime arrivalTime;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "hotel_amenity",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    private Set<Amenity> amenities = new HashSet<>();

    public Hotel() {}

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private String brand;
        private Address address;
        private Contacts contacts;
        private ArrivalTime arrivalTime;
        private Set<Amenity> amenities = new HashSet<>();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder brand(String brand) { this.brand = brand; return this; }
        public Builder address(Address address) { this.address = address; return this; }
        public Builder contacts(Contacts contacts) { this.contacts = contacts; return this; }
        public Builder arrivalTime(ArrivalTime arrivalTime) { this.arrivalTime = arrivalTime; return this; }
        public Builder amenities(Set<Amenity> amenities) { this.amenities = amenities; return this; }

        public Hotel build() {
            Hotel h = new Hotel();
            h.id = this.id;
            h.name = this.name;
            h.description = this.description;
            h.brand = this.brand;
            h.address = this.address;
            h.contacts = this.contacts;
            h.arrivalTime = this.arrivalTime;
            h.amenities = this.amenities;
            return h;
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
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
    public Contacts getContacts() { return contacts; }
    public void setContacts(Contacts contacts) { this.contacts = contacts; }
    public ArrivalTime getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(ArrivalTime arrivalTime) { this.arrivalTime = arrivalTime; }
    public Set<Amenity> getAmenities() { return amenities; }
    public void setAmenities(Set<Amenity> amenities) { this.amenities = amenities; }
}
