package com.example.hotelservice.specification;

import com.example.hotelservice.entity.Amenity;
import com.example.hotelservice.entity.Hotel;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class HotelSpecification {

    private HotelSpecification() {}

    public static Specification<Hotel> hasName(String name) {
        return (root, query, cb) -> name == null ? null :
                cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Hotel> hasBrand(String brand) {
        return (root, query, cb) -> brand == null ? null :
                cb.like(cb.lower(root.get("brand")), "%" + brand.toLowerCase() + "%");
    }

    public static Specification<Hotel> hasCity(String city) {
        return (root, query, cb) -> {
            if (city == null) return null;
            Join<Object, Object> address = root.join("address", JoinType.LEFT);
            return cb.like(cb.lower(address.get("city")), "%" + city.toLowerCase() + "%");
        };
    }

    public static Specification<Hotel> hasCountry(String country) {
        return (root, query, cb) -> {
            if (country == null) return null;
            Join<Object, Object> address = root.join("address", JoinType.LEFT);
            return cb.like(cb.lower(address.get("country")), "%" + country.toLowerCase() + "%");
        };
    }

    public static Specification<Hotel> hasAmenity(String amenity) {
        return (root, query, cb) -> {
            if (amenity == null) return null;
            Join<Hotel, Amenity> amenities = root.join("amenities", JoinType.LEFT);
            query.distinct(true);
            return cb.like(cb.lower(amenities.get("name")), "%" + amenity.toLowerCase() + "%");
        };
    }
}
