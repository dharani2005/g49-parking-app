package se.lexicon.data.impl;

import se.lexicon.data.ParkingSpotDao;
import se.lexicon.model.ParkingSpot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

  public class ParkingSpotDaoImpl implements ParkingSpotDao {
   private List<ParkingSpot> storage= new ArrayList<>();

    @Override
    public ParkingSpot create(ParkingSpot parkingSpot) {
        if(parkingSpot==null)throw new IllegalArgumentException("parkingspot is null");
        Optional<ParkingSpot> parkingSpotOptional=find(parkingSpot.getSpotNumber());
        if(parkingSpotOptional.isPresent())throw new IllegalArgumentException("This parkingspot is already created");
        storage.add(parkingSpot);
        return parkingSpot;
    }

    @Override
    public Optional<ParkingSpot> find(int spotNumber) {
        for(ParkingSpot parkingspot: storage){
            if(parkingspot.getSpotNumber()==spotNumber){
                return Optional.of(parkingspot);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean remove(int spotNumber) {
        Optional<ParkingSpot> parkingSpotOptional=find(spotNumber);
        if(!parkingSpotOptional.isPresent()){
            return false;}
            storage.remove(parkingSpotOptional.get());
        return true;
    }

    @Override
    public List<ParkingSpot> findAll() {
        return new ArrayList<>(storage);
    }


    @Override
    public List<ParkingSpot> findByAreaCode(int areaCode) {
       List<ParkingSpot> spots=new ArrayList<>();
        for(ParkingSpot parkingspot:storage ) {
            if (parkingspot.getAreaCode() == areaCode) {
            spots.add(parkingspot);
            }
        }
      return spots;
    }

    @Override
    public void occupyParkingSpot(int spotNumber) {
        for(ParkingSpot parkingSpot: storage) {
        if(parkingSpot.getSpotNumber()==spotNumber) {
            parkingSpot.isOccupied();
        }
        }
        throw new IllegalArgumentException("spotnumber is not found");
    }

    @Override
    public void vacateParkingSpot(int spotNumber) {
        for(ParkingSpot parkingSpot: storage){
            if(parkingSpot.getSpotNumber()==spotNumber){
                parkingSpot.vacate();
            }
        }
        throw new IllegalArgumentException("spotnumber is not found");
    }

}
