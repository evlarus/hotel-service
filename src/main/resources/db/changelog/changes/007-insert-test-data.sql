-- liquibase formatted sql

-- changeset evlarus:007
INSERT INTO addresses (house_number, street, city, country, post_code) VALUES (9,  'Pobediteley Avenue',    'Minsk',   'Belarus', '220004');
INSERT INTO addresses (house_number, street, city, country, post_code) VALUES (15, 'Lenina Street',         'Moscow',  'Russia',  '101000');
INSERT INTO addresses (house_number, street, city, country, post_code) VALUES (3,  'Pervomayskaya Street',  'Mogilev', 'Belarus', '212030');
INSERT INTO addresses (house_number, street, city, country, post_code) VALUES (22, 'Nemiga Street',         'Minsk',   'Belarus', '220004');
INSERT INTO addresses (house_number, street, city, country, post_code) VALUES (5,  'Tverskaya Street',      'Moscow',  'Russia',  '125009');

INSERT INTO contacts (phone, email) VALUES ('+375 17 309-80-00', 'doubletreeminsk.info@hilton.com');
INSERT INTO contacts (phone, email) VALUES ('+7 495 123-45-67',  'info@marriottmoscow.com');
INSERT INTO contacts (phone, email) VALUES ('+375 222 12-34-56', 'info@hotelmogilev.by');
INSERT INTO contacts (phone, email) VALUES ('+375 17 200-00-00', 'info@europaminsk.com');
INSERT INTO contacts (phone, email) VALUES ('+7 495 987-65-43',  'info@novotelmoscow.com');

INSERT INTO arrival_times (check_in, check_out) VALUES ('14:00', '12:00');
INSERT INTO arrival_times (check_in, check_out) VALUES ('15:00', '11:00');
INSERT INTO arrival_times (check_in, check_out) VALUES ('14:00', '12:00');
INSERT INTO arrival_times (check_in, check_out) VALUES ('13:00', '11:00');
INSERT INTO arrival_times (check_in, check_out) VALUES ('15:00', '12:00');

INSERT INTO hotels (name, description, brand, address_id, contacts_id, arrival_time_id) VALUES
('DoubleTree by Hilton Minsk',
 'The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel''s 20th floor ...',
 'Hilton', 1, 1, 1);

INSERT INTO hotels (name, description, brand, address_id, contacts_id, arrival_time_id) VALUES
('Marriott Moscow',
 'A luxury hotel in the heart of Moscow offering world-class amenities and exceptional service.',
 'Marriott', 2, 2, 2);

INSERT INTO hotels (name, description, brand, address_id, contacts_id, arrival_time_id) VALUES
('Hotel Mogilev',
 'A comfortable hotel in Mogilev city center with modern facilities and friendly staff.',
 'Local', 3, 3, 3);

INSERT INTO hotels (name, description, brand, address_id, contacts_id, arrival_time_id) VALUES
('Europa Hotel Minsk',
 'A historic hotel in the heart of Minsk offering elegant rooms and premium service.',
 'Local', 4, 4, 4);

INSERT INTO hotels (name, description, brand, address_id, contacts_id, arrival_time_id) VALUES
('Novotel Moscow',
 'Modern hotel in central Moscow with spacious rooms and excellent business facilities.',
 'Accor', 5, 5, 5);

INSERT INTO amenities (name) VALUES ('Free parking');
INSERT INTO amenities (name) VALUES ('Free WiFi');
INSERT INTO amenities (name) VALUES ('Non-smoking rooms');
INSERT INTO amenities (name) VALUES ('Concierge');
INSERT INTO amenities (name) VALUES ('On-site restaurant');
INSERT INTO amenities (name) VALUES ('Fitness center');
INSERT INTO amenities (name) VALUES ('Pet-friendly rooms');
INSERT INTO amenities (name) VALUES ('Room service');
INSERT INTO amenities (name) VALUES ('Business center');
INSERT INTO amenities (name) VALUES ('Meeting rooms');

INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (1, 1);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (1, 2);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (1, 3);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (1, 4);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (1, 5);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (1, 6);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (2, 2);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (2, 4);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (2, 5);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (2, 6);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (2, 8);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (2, 9);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (2, 10);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (3, 1);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (3, 2);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (3, 3);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (4, 2);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (4, 3);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (4, 4);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (4, 5);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (4, 8);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (5, 1);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (5, 2);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (5, 5);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (5, 6);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (5, 9);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (5, 10);
