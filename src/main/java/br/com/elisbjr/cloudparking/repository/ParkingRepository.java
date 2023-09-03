package br.com.elisbjr.cloudparking.repository;

import br.com.elisbjr.cloudparking.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {

}
