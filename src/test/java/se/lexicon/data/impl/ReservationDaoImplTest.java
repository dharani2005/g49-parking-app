package se.lexicon.data.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.model.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationDaoImplTest {
    private ReservationDaoImpl reservationDao;
    @BeforeEach
    public void setup()
    {
        reservationDao = new ReservationDaoImpl();
    }
    @Test
    public void testCreate()
    {
        Customer customer = new Customer("abcd","1234");
        ParkingSpot parkingSpot = new ParkingSpot(123,456);
        Vehicle vehicle = new Vehicle("12srt", VehicleType.CAR);
        Reservation reservation = new Reservation(customer,parkingSpot,2,vehicle);
        Reservation actualValue = reservationDao.create(reservation);
        Reservation expectedValue = reservation;
        assertEquals(expectedValue,actualValue);
    }
    @Test
    public  void testFindReservation()
    {
        Customer customer = new Customer("abcd","1234");
        ParkingSpot parkingSpot = new ParkingSpot(123,456);
        Vehicle vehicle = new Vehicle("12srt", VehicleType.CAR);
        Reservation reservation = new Reservation("abcd",customer,parkingSpot,vehicle);
        Reservation actualValue = reservationDao.create(reservation);
        //finding existing reservation//
        Optional<Reservation> reservationOptional = reservationDao.find("abcd");
        assertTrue(reservationOptional.isPresent());
        //finding existing reservation//
        Optional<Reservation> reservationOptional1 = reservationDao.find("efgh");
        assertFalse(reservationOptional1.isPresent());
    }



}
