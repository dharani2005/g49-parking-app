package se.lexicon.data.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.model.Vehicle;
import se.lexicon.model.VehicleType;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleDaoImplTest {


    private VehicleDaoImpl vehicleDao;

    @BeforeEach
    public void setUp() {
        vehicleDao = new VehicleDaoImpl();

    }

    //1. testCreate
    @Test
    public void testCreate() {
        Vehicle vehicle = new Vehicle("ABC123", VehicleType.CAR);
        Vehicle actualValue = vehicleDao.create(vehicle);
        Vehicle expectedValue = vehicle;

        assertEquals(expectedValue, actualValue);
        assertTrue(vehicleDao.find("ABC123").isPresent());
    }

    //2. testCreateExistingVehicle
    @Test
    public void testCreateExistingVehicle() {
        Vehicle vehicle = new Vehicle("ABC123", VehicleType.CAR);
        vehicleDao.create(vehicle);
        assertThrows(IllegalArgumentException.class, () -> vehicleDao.create(vehicle));
    }

    //3. testFindExistingVehicle

    @Test
    public void testFindExistingVehicle() {
        Vehicle vehicle = new Vehicle("DFG456", VehicleType.CAR);
        vehicleDao.create(vehicle);

        Optional<Vehicle> foundVehicle = vehicleDao.find("DFG456");
        assertTrue(foundVehicle.isPresent());
        assertEquals(vehicle, foundVehicle.get());

    }


    //4. testFindNonExistingVehicle
    @Test
    public void testFindNonExistingVehicle() {
        Optional<Vehicle> foundVehicle = vehicleDao.find("DFG456");
        assertFalse(foundVehicle.isPresent());
    }

    //5. testRemoveExistingVehicle
    @Test
    public void testRemoveExistingVehicle() {
        Vehicle vehicle = new Vehicle("DFG456", VehicleType.CAR);
        vehicleDao.create(vehicle);

        //Remove the vehicle from DAO
        assertTrue(vehicleDao.remove("DFG456"));

        //Assert that the removed vehicle is not found in the DAO
        assertFalse(vehicleDao.find("DFG456").isPresent());
    }


    //6. testRemoveNonExistingVehicle
    @Test
    public void testRemoveNonExistingVehicle() {
        boolean removed = vehicleDao.remove("MNB455");
        //Assert that the vehicle is not removed
        assertFalse(removed);
    }

    @Test
    public void testUpdate() {
        //Create a new Vehicle object
        Vehicle originalVehicle = new Vehicle("DFG456", VehicleType.CAR);
        //Adding the original vehicle to DAO
        vehicleDao.create(originalVehicle);

        //Create an update Vehicle object with the same license plate but different type
        Vehicle updatedVehicle = new Vehicle("DFG456", VehicleType.TRUCK);
        //Updating the vehicle in the DAO
        vehicleDao.update(updatedVehicle);

        // Finding the updated vehicle from DAO
        Optional<Vehicle> result = vehicleDao.find("DFG456");

        //Asserting that the vehicle is found
        assertTrue(result.isPresent());

        //Asserting that the type of the found vehicle is updated
        assertEquals(result.get().getType(), VehicleType.TRUCK);


    }


}
