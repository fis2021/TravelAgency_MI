package org.ta.services;

import javafx.util.Pair;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.ta.exceptions.LocationAlreadyExistsException;
import org.ta.model.Trip;


import java.util.List;
import java.util.Objects;
import java.util.LinkedList;

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
        //tripRepository.update(trip);
    }

    public static ObjectRepository<Trip> getTripRepository() {
        return tripRepository;
    }

    public static List<Trip> getAllTrips() {
        return tripRepository.find().toList();
    }

    private static void checkLocationDoesNotAlreadyExist(String location) throws LocationAlreadyExistsException {
        for (Trip user : tripRepository.find()) {
            if (Objects.equals(location, user.getLocation()))
                throw new LocationAlreadyExistsException(location);
        }
    }



/*
    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }


    public static String getLoggedUser(String username){
        for (User user : tripRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                return username;
        }
        return "";
    }

    public static String getUserRole(String username){
        for (User user : tripRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                if(Objects.equals(user.getRole(),"Customer"))
                    return "Customer";
                else
                    return "Travel Agency";
        }
        return "";
    }

*/

}
