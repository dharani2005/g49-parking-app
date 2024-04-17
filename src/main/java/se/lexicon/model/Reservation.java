package se.lexicon.model;

import java.time.LocalDateTime;

public class Reservation {
    private String id;
    private Customer customer;
    private ParkingSpot parkingSpot;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Vehicle associatedVehicle;

    public Reservation(Customer customer, ParkingSpot parkingSpot, int hours, Vehicle associatedVehicle) {
        this.customer = customer;
        this.parkingSpot = parkingSpot;
        this.startTime = LocalDateTime.now();
        setEndTime(hours);
        this.associatedVehicle = associatedVehicle;

    }

    public Reservation(String id, Customer customer, ParkingSpot parkingSpot, Vehicle associatedVehicle) {
        this.id = id;
        this.customer = customer;
        this.parkingSpot = parkingSpot;
        this.associatedVehicle = associatedVehicle;
    }

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Vehicle getAssociatedVehicle() {
        return associatedVehicle;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAssociatedVehicle(Vehicle associatedVehicle) {
        this.associatedVehicle = associatedVehicle;
    }

    public void setEndTime(int hours) {
        if (hours <= 0){
            throw new IllegalArgumentException("Hours should be a positive number");
        }
        this.endTime = startTime.plusHours(hours);
    }

    public void reserve() {
        if (customer.getReservation() != null)
            throw new IllegalArgumentException("Customer has already a reserved parking spot.");
        if (parkingSpot == null) throw new IllegalArgumentException("Parking Spot should not be null.");
        if (parkingSpot.isOccupied()) throw new IllegalArgumentException("Parking spot is already occupied!");
        parkingSpot.occupy();
        customer.setReservation(this);
    }

    public void cancel() {
        parkingSpot.vacate();
        customer.setReservation(null);
        //setCustomer(null);
    }

    /*public void setCustomer(Customer customer) {
        this.customer = customer;
    }*/

    public String getDescription() {

        StringBuilder builder = new StringBuilder();

        builder.append("Reservation Id: ").append(id);
        if (customer == null) {
            builder.append(", Customer: ").append(" - ");
        } else {
            builder.append(", Customer: ").append(customer.getDescription());
        }

        builder.append(", Parking Spot: ").append(parkingSpot.getSpotNumber())
                .append(", Start Time: ").append(startTime)
                .append(", End Time: ").append(endTime)
                .append(", Associated Vehicle: ").append(associatedVehicle.getLicensePlate());

        return builder.toString();

    }


}
