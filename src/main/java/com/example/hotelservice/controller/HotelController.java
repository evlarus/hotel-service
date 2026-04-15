package com.example.hotelservice.controller;

import com.example.hotelservice.dto.request.HotelCreateRequest;
import com.example.hotelservice.dto.response.HotelDetailResponse;
import com.example.hotelservice.dto.response.HotelShortResponse;
import com.example.hotelservice.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/property-view")
@Tag(name = "Hotels", description = "Hotel management API")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/hotels")
    @Operation(summary = "Get all hotels", description = "Returns a list of all hotels with brief information")
    @ApiResponse(responseCode = "200", description = "List of hotels returned successfully")
    public ResponseEntity<List<HotelShortResponse>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @GetMapping("/hotels/{id}")
    @Operation(summary = "Get hotel by ID", description = "Returns detailed information about a specific hotel")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Hotel found"),
            @ApiResponse(responseCode = "404", description = "Hotel not found")
    })
    public ResponseEntity<HotelDetailResponse> getHotelById(
            @Parameter(description = "Hotel ID") @PathVariable Long id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

    @GetMapping("/search")
    @Operation(summary = "Search hotels", description = "Search hotels by name, brand, city, country or amenities")
    @ApiResponse(responseCode = "200", description = "Search results returned successfully")
    public ResponseEntity<List<HotelShortResponse>> searchHotels(
            @Parameter(description = "Hotel name") @RequestParam(required = false) String name,
            @Parameter(description = "Hotel brand") @RequestParam(required = false) String brand,
            @Parameter(description = "City") @RequestParam(required = false) String city,
            @Parameter(description = "Country") @RequestParam(required = false) String country,
            @Parameter(description = "Amenity name") @RequestParam(required = false) String amenities) {
        return ResponseEntity.ok(hotelService.searchHotels(name, brand, city, country, amenities));
    }

    @PostMapping("/hotels")
    @Operation(summary = "Create hotel", description = "Creates a new hotel and returns brief information")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Hotel created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    public ResponseEntity<HotelShortResponse> createHotel(@Valid @RequestBody HotelCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(request));
    }

    @PostMapping("/hotels/{id}/amenities")
    @Operation(summary = "Add amenities", description = "Adds a list of amenities to a hotel")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Amenities added successfully"),
            @ApiResponse(responseCode = "404", description = "Hotel not found")
    })
    public ResponseEntity<Void> addAmenities(
            @Parameter(description = "Hotel ID") @PathVariable Long id,
            @RequestBody List<String> amenities) {
        hotelService.addAmenities(id, amenities);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/histogram/{param}")
    @Operation(summary = "Get histogram", description = "Returns count of hotels grouped by parameter (brand, city, country, amenities)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Histogram returned successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid parameter")
    })
    public ResponseEntity<Map<String, Long>> getHistogram(
            @Parameter(description = "Parameter: brand, city, country, amenities") @PathVariable String param) {
        return ResponseEntity.ok(hotelService.getHistogram(param));
    }
}
