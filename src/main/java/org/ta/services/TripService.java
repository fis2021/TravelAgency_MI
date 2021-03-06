package org.ta.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.ta.controllers.LoginController;
import org.ta.exceptions.LocationAlreadyExistsException;
import org.ta.model.Trip;

import java.util.ArrayList;
import java.util.Objects;

public class TripService {

    private static ObjectRepository<Trip> tripRepository;
    private static Nitrite database;

    public static void initDatabase() {
        FileSystemService.initDirectory();
        database = Nitrite.builder()
                .filePath(FileSystemService.getPathToFile("tripss.db").toFile())
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
            if(  (trip.getBook().equals("0") ) && trip.getAllSet().equals(LoginController.getLoggedUser() ))
            list.add(trip);
        }
        return list;
    }

    public static ArrayList<Trip> getAllTripsCustomer(){
        ArrayList<Trip> list = new ArrayList<>();
        for(Trip trip : tripRepository.find()) {
            if( (trip.getBook().equals("0") ) && !trip.getPeriod().equals("?" ))
                list.add(trip);
        }
        return list;
    }

    public static void clearTrip(String location, String period,String price){
        for(Trip trip:tripRepository.find()) {
            if(trip.getLocation()!=null && trip.getLocation().equals(location) && trip.getPeriod().equals(period) && trip.getPrice().equals(price)) {
                tripRepository.remove(trip);
                break;
            }
        }
    }

    public static void clearTripRec( String period,String price){
        for(Trip trip:tripRepository.find()) {
            if(trip.getLocation()!=null && trip.getPeriod().equals(period) && trip.getPrice().equals(price)) {
                tripRepository.remove(trip);
                break;
            }
        }
    }

    public static ArrayList<Trip> getMyTripsCustomer(){
        ArrayList<Trip> list = new ArrayList<>();
        for(Trip trip : tripRepository.find()) {
            if( trip.getBook().equals(LoginController.getLoggedUser() ))
                list.add(trip);
        }
        return list;
    }

    public static ArrayList<Trip> getMyBookedTrips(){
        ArrayList<Trip> list = new ArrayList<>();
        for(Trip trip : tripRepository.find()) {
            if( ( !(trip.getBook().equals("0") ) && trip.getAllSet().equals(LoginController.getLoggedUser() )) || (trip.getPeriod().equals("?") ))
                list.add(trip);
        }
        return list;
    }

    public static void BookThisTrip(Trip bookTrip){
        for(Trip trip:tripRepository.find()) {
            if(trip.getLocation()!=null && trip.getLocation().equals(bookTrip.getLocation()) ) {
                trip.setBook(LoginController.getLoggedUser());
                tripRepository.update(trip);
                break;
            }
        }
    }

    public static void AllSetThisTrip(Trip bookTrip){
        for(Trip trip:tripRepository.find()) {
            if(trip.getLocation()!=null && trip.getLocation().equals(bookTrip.getLocation()) ) {
                trip.setAllSet("0");
                tripRepository.update(trip);
                break;
            }
        }
    }

    public static void setBookRec(Trip bookTrip){

        for(Trip trip:tripRepository.find()) {
            if(trip.getLocation()!=null && trip.getLocation().equals(bookTrip.getLocation()) ) {
                trip.setBook(bookSearch());
                tripRepository.update(trip);
                break;
            }
        }
    }
    public static String bookSearch() {
        for (Trip tripSearch : tripRepository.find()) {
            if (tripSearch.getLocation() != null && tripSearch.getPeriod().equals("?")) {
                return tripSearch.getAllSet();
            }

        }
        return "";
    }

    public static Nitrite getDatabase(){
        return database;
    }
}
