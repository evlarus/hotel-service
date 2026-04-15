package com.example.hotelservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllHotels_shouldReturn200AndList() throws Exception {
        mockMvc.perform(get("/property-view/hotels"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(5));
    }

    @Test
    void getHotelById_shouldReturn200ForExistingHotel() throws Exception {
        mockMvc.perform(get("/property-view/hotels/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("DoubleTree by Hilton Minsk"))
                .andExpect(jsonPath("$.brand").value("Hilton"))
                .andExpect(jsonPath("$.address").isMap())
                .andExpect(jsonPath("$.contacts").isMap())
                .andExpect(jsonPath("$.amenities").isArray());
    }

    @Test
    void getHotelById_shouldReturn404ForNonExistingHotel() throws Exception {
        mockMvc.perform(get("/property-view/hotels/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404));
    }

    @Test
    void searchHotels_byCity_shouldReturnFilteredResults() throws Exception {
        mockMvc.perform(get("/property-view/search").param("city", "Minsk"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void searchHotels_byBrand_shouldReturnFilteredResults() throws Exception {
        mockMvc.perform(get("/property-view/search").param("brand", "Hilton"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void searchHotels_noParams_shouldReturnAll() throws Exception {
        mockMvc.perform(get("/property-view/search"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void createHotel_shouldReturn201() throws Exception {
        String body = """
                {
                  "name": "New Test Hotel",
                  "brand": "TestBrand",
                  "address": {
                    "houseNumber": 10,
                    "street": "Test Street",
                    "city": "Grodno",
                    "country": "Belarus",
                    "postCode": "230000"
                  },
                  "contacts": {
                    "phone": "+375 152 00-00-00",
                    "email": "grodno@test.com"
                  },
                  "arrivalTime": {
                    "checkIn": "14:00"
                  }
                }
                """;

        mockMvc.perform(post("/property-view/hotels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("New Test Hotel"));
    }

    @Test
    void createHotel_shouldReturn400WhenNameMissing() throws Exception {
        String body = """
                {
                  "brand": "TestBrand",
                  "address": {
                    "houseNumber": 1,
                    "street": "Test Street",
                    "city": "Minsk",
                    "country": "Belarus",
                    "postCode": "220000"
                  },
                  "contacts": { "phone": "+375 17 000-00-00", "email": "test@test.com" },
                  "arrivalTime": { "checkIn": "14:00" }
                }
                """;

        mockMvc.perform(post("/property-view/hotels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void addAmenities_shouldReturn200() throws Exception {
        String body = """
                ["Pool", "Sauna"]
                """;

        mockMvc.perform(post("/property-view/hotels/1/amenities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());
    }

    @Test
    void addAmenities_shouldReturn404ForNonExistingHotel() throws Exception {
        mockMvc.perform(post("/property-view/hotels/999/amenities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[\"Pool\"]"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getHistogram_byCity_shouldReturn200() throws Exception {
        mockMvc.perform(get("/property-view/histogram/city"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Minsk").exists())
                .andExpect(jsonPath("$.Moscow").exists());
    }

    @Test
    void getHistogram_byAmenities_shouldReturn200() throws Exception {
        mockMvc.perform(get("/property-view/histogram/amenities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['Free WiFi']").exists());
    }

    @Test
    void getHistogram_byBrand_shouldReturn200() throws Exception {
        mockMvc.perform(get("/property-view/histogram/brand"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Hilton").exists());
    }

    @Test
    void getHistogram_invalidParam_shouldReturn400() throws Exception {
        mockMvc.perform(get("/property-view/histogram/invalid"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400));
    }
}
