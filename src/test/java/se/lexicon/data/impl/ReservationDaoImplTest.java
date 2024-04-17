package se.lexicon.data.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.data.ReservationDao;
import se.lexicon.model.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationDaoImplTest {
    private ReservationDaoImpl reservationDao;
    private  Customer customer;
    private ParkingSpot parkingSpot;
    private Vehicle vehicle;
    @BeforeEach
    public void setup()
    {
        reservationDao = new ReservationDaoImpl();
        customer=new Customer(123,"abcd","1234");
        parkingSpot = new ParkingSpot(123,456);
        vehicle = new Vehicle("12srt", VehicleType.CAR);
    }
    @Test
    public void testCreate()
    {
        Reservation reservation = new Reservation(customer,parkingSpot,2,vehicle);
        Reservation actualValue = reservationDao.create(reservation);
        Reservation expectedValue = reservation;
        assertEquals(expectedValue,actualValue);
    }
    @Test
    public  void testFindReservation()
    {
        Reservation reservation = new Reservation("abcd",customer,parkingSpot,vehicle);
        reservationDao.create(reservation);
        //finding existing reservation//
        Optional<Reservation> reservationOptional = reservationDao.find("abcd");
        assertTrue(reservationOptional.isPresent());
        assertEquals(reservation,reservationOptional.get());
        //finding existing reservation//
        Optional<Reservation> reservationOptional1 = reservationDao.find("efgh");
        assertFalse(reservationOptional1.isPresent());
    }
    @Test
    public void testRemoveReservation(){
        //Assign
        Reservation reservation = new Reservation("abcd",customer,parkingSpot,vehicle);
        Reservation actualValue = reservationDao.create(reservation);

        //Act
        boolean result1 = reservationDao.remove("abcd");
        boolean result2 = reservationDao.remove("abcdefg");

        //Assert
        assertTrue(result1);
        assertFalse(result2);
    }
   @Test
    public void testFindReservationByCustomerId()
    {
        //Assign
        Reservation reservation = new Reservation("121",customer,parkingSpot,vehicle);
        reservationDao.create(reservation);

        //Act
       Reservation expectedValue = reservationDao.findByCustomerId(123);
      //assertEquals(expectedValue, actualValue);

        //Assert
      assertEquals("abcd", expectedValue.getCustomer().getName());
        //Todo
    }
    @Test
    public void testFindReservationByVehicleLicensePlate()
    {
        Reservation reservation = new Reservation("abcd",customer,parkingSpot,vehicle);
        Reservation actualValue=reservationDao.create(reservation);
        Reservation expectedValue = reservationDao.findByVehicleLicensePlate("12srt");
       assertEquals(expectedValue.getAssociatedVehicle().getLicensePlate(),vehicle.getLicensePlate());
    //Todo
    }
    @Test
    public void testFindReservationByParkingSpotNumber() {
        //Todo
        Reservation reservation = new Reservation("abcd",customer,parkingSpot,vehicle);
        Reservation actualValue=reservationDao.create(reservation);
        Reservation expectedValue = reservationDao.findByParkingSpotNumber(123);
        assertEquals(expectedValue.getParkingSpot().getSpotNumber(),parkingSpot.getSpotNumber());
    }

}

