package br.com.elisbjr.cloudparking.services;

import br.com.elisbjr.cloudparking.entity.Parking;
import br.com.elisbjr.cloudparking.exception.FindNullException;
import br.com.elisbjr.cloudparking.exception.ParkingNullException;
import br.com.elisbjr.cloudparking.repository.ParkingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class ParkingService {

    @Autowired
    private ParkingRepository repository;

    @Transactional
    public Parking save(Parking parking) {

        if(parking.getLicense() == null || parking.getState() == null ||
                parking.getModel() == null || parking.getColor() == null)
            throw new ParkingNullException();

       if(parking.getEntryDate() == null)
           parking.setEntryDate(LocalDateTime.now());

        return repository.save(parking);
    }

    @Transactional
    public List<Parking> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Parking findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public Parking updateParking(Long id, Parking updateParking) {
        Parking newParking = findById(id);

        if(newParking == null)
            throw new FindNullException();
        newParking.setLicense(updateParking.getLicense());
        newParking.setState(updateParking.getState());
        newParking.setModel(updateParking.getModel());
        newParking.setColor(updateParking.getColor());

        return repository.save(newParking);

    }

    @Transactional
    public void delete(Long id) {
        Parking removeParking = findById(id);

        if(removeParking == null)
            throw new FindNullException();

        repository.deleteById(id);

    }

    @Transactional
    public Parking checkOut(Long id) {
        Parking parking = findById(id);

        if(parking == null)
            throw new FindNullException();

        parking.setExitDate(LocalDateTime.now());
        parking.setBill(ParkingCheckOut.getBill(parking));
        return repository.save(parking);
    }
}
