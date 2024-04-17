package se.lexicon.data.impl;

import se.lexicon.data.ReservationDao;
import se.lexicon.model.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationDaoImpl implements ReservationDao {
    private List<Reservation> storage = new ArrayList<>();

    @Override
    public Reservation create(Reservation reservation) {
        if (reservation == null) throw new IllegalArgumentException("reservation is null");
        Optional<Reservation> reservationOptional = find(reservation.getId());
        if (reservationOptional.isPresent()) throw new IllegalArgumentException(("reservation already exists"));
        storage.add(reservation);
        return reservation;
    }

    @Override
    public Optional<Reservation> find(String id) {
        for (Reservation reservation : storage) {
            if (reservation.getId() == id) {
                return Optional.of(reservation);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean remove(String id) {
        Optional<Reservation> reservationOptional = find(id);
        if (!reservationOptional.isPresent()) {
            return false;
        }
        storage.remove(reservationOptional.get());
        return true;
    }

    @Override
    public Reservation findByCustomerId(int customerId) {
        for (Reservation reservation : storage) {
            if (reservation.getCustomer().getId() == customerId) {
                return reservation;
            }
        }
        throw new IllegalArgumentException("Resevation with this customerid not found");
    }

    @Override
    public Reservation findByVehicleLicensePlate(String licensePlate) {
        for (Reservation reservation : storage) {
            if (reservation.getAssociatedVehicle().getLicensePlate() == licensePlate) {
                return reservation;
            }
        }
        throw new IllegalArgumentException("Reservation with this vehicle licence plate is not found");
    }

    @Override
    public Reservation findByParkingSpotNumber(int spotNumber) {
        for (Reservation reservation : storage) {
            if (reservation.getParkingSpot().getSpotNumber() == spotNumber) {
                return reservation;
            }
        }

        throw new IllegalArgumentException("Reservation not found with this parkingspotnumber");
    }
}
