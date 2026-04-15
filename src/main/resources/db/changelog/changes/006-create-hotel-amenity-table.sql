-- liquibase formatted sql

-- changeset evlarus:006
CREATE TABLE hotel_amenity (
    hotel_id   BIGINT NOT NULL,
    amenity_id BIGINT NOT NULL,
    PRIMARY KEY (hotel_id, amenity_id),
    CONSTRAINT fk_hotel_amenity_hotel   FOREIGN KEY (hotel_id)   REFERENCES hotels(id),
    CONSTRAINT fk_hotel_amenity_amenity FOREIGN KEY (amenity_id) REFERENCES amenities(id)
);
