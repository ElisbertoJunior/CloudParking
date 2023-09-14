package br.com.elisbjr.cloudparking.controllers;

import br.com.elisbjr.cloudparking.entity.Parking;

import br.com.elisbjr.cloudparking.services.ParkingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/parking")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "CloudParking", description = "APIs for integration CloudParking")
public class ParkingController {
    @Autowired
    private ParkingService service;


    @PostMapping(value = "/save")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Parking> save(@RequestBody Parking parking) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(parking));
    }

    @GetMapping(value = "bring-all")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<Parking>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Parking> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PutMapping(value = "/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Parking> update(@PathVariable Long id, @RequestBody Parking updateParking) {
        return ResponseEntity.ok().body(service.updateParking(id, updateParking));
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<Parking>> delete(@PathVariable Long id) {
        service.delete(id);
        return findAll();

    }

    @PostMapping(value = "/checkout/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Parking> checkOut(@PathVariable Long id){
        return ResponseEntity.ok().body(service.checkOut(id));
    }

}
