package org.ta.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.ta.exceptions.LocationAlreadyExistsException;
import org.ta.model.Trip;

import java.util.Objects;

public class TripService {

    private static ObjectRepository<Trip> tripRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(FileSystemService.getPathToFile("trips.db").toFile())
                .openOrCreate("test", "test");

        tripRepository = database.getRepository(Trip.class);
    }

    public static void addTrip(String location, String imgSrc, double price, String period, boolean book) throws LocationAlreadyExistsException {
        checkLocationDoesNotAlreadyExist(location);
        tripRepository.insert(new Trip(location, imgSrc,price, period, book));
    }

    public static ObjectRepository<Trip> getTripRepository() {
        return tripRepository;
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
