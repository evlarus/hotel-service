package com.example.hotelservice.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "amenities")
public class Amenity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    public Amenity() {}

    public Amenity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private String name;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Amenity build() { return new Amenity(id, name); }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Amenity amenity)) return false;
        return Objects.equals(name, amenity.name);
    }

    @Override
    public int hashCode() { return Objects.hash(name); }
}
