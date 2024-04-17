package se.lexicon.data.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.model.ParkingSpot;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingSpotDaoImplTest {
    private ParkingSpotDaoImpl testobject;

   @BeforeEach
   public void setup()
    {
     testobject = new ParkingSpotDaoImpl();
    }
    @Test
    public void testCreate(){
        ParkingSpot parkingSpot = new ParkingSpot(123,456);
        ParkingSpot actualValue = testobject.create(parkingSpot);
        ParkingSpot expectedValue = parkingSpot;
        assertEquals(expectedValue,actualValue);
        assertTrue(testobject.find(123).isPresent());
   }
   @Test
    public void testFindBySpotNumber(){
       ParkingSpot parkingSpot = new ParkingSpot(123,456);
       ParkingSpot actualValue = testobject.create(parkingSpot);
       //test finding existing parkingspot//
       Optional<ParkingSpot> parkingSpotOptional=testobject.find(123);
       assertTrue(parkingSpotOptional.isPresent());
       //test finding non-existing parkingspot//
       Optional<ParkingSpot> parkingSpotOptional1=testobject.find(234);
       assertFalse(parkingSpotOptional1.isPresent());


   }
    @Test
    public void testRemoveBySpotNumber(){
       ParkingSpot parkingSpot = new ParkingSpot(123,456);
       ParkingSpot actualValue = testobject.create(parkingSpot);
       assertTrue(testobject.remove(123));
       //test to remove non-existing parkingspot//
       assertFalse(testobject.remove(234));
    }
    @Test
    public void testFindAllCustomers()
    {
        ParkingSpot input = new ParkingSpot(123,456);
        ParkingSpot result = testobject.create(input);
        List<ParkingSpot> parkingspots = testobject.findAll();
        assertEquals(1,parkingspots.size());
    }
    @Test
    public void testFindAllParkingSpotEmptyLst()
    {
        List<ParkingSpot> parkingspots = testobject.findAll();
        assertEquals(0,parkingspots.size());
    }

}
