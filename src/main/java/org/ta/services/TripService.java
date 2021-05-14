package org.ta.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.ta.exceptions.LocationAlreadyExistsException;
import org.ta.model.Trip;

import java.util.ArrayList;
import java.util.Objects;

public class TripService {

    private static ObjectRepository<Trip> tripRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(FileSystemService.getPathToFile("trips.db").toFile())
                .openOrCreate("test", "test");

        tripRepository = database.getRepository(Trip.class);
    }
    public static void addTrip(String location, String price, String period) throws LocationAlreadyExistsException {
        checkLocationDoesNotAlreadyExist(location);
        tripRepository.insert(new Trip(location,price, period));
    }
    public static void checkLocationDoesNotAlreadyExist(String location) throws LocationAlreadyExistsException {
        for (Trip trip : tripRepository.find()) {
            if (Objects.equals(location, trip.getLocation()))
                throw new LocationAlreadyExistsException(location);
        }
    }
    public static ArrayList<Trip> getAllTrips(){
        ArrayList<Trip> list = new ArrayList<>();
        for(Trip trip : tripRepository.find()) {
            list.add(trip);
        }
        return list;
    }
    public static Trip getTrip(String location){
        for(Trip trip : tripRepository.find())
            if(Objects.equals(location, trip.getLocation()))
                return trip;
        return null;
    }

}
