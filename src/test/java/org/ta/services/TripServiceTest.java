package org.ta.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.ta.controllers.LoginController;
import org.ta.exceptions.LocationAlreadyExistsException;
import org.ta.exceptions.UsernameAlreadyExistsException;
import org.ta.model.Trip;
import org.ta.model.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TripServiceTest {
    public static final String ADMIN = "admin";
    public static final String ADMIN2 = "admin2";
    public static final String ADMIN3 = "admin3";

    @AfterEach
    void tearDown() {
        System.out.println("After each");
        TripService.getDatabase().close();
        UserService.getDatabase().close();
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before Class");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After Class");
    }

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = "trip.db";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        TripService.initDatabase();
        UserService.initDatabase();
    }
    @Test
    @DisplayName("Database is initialized, and there are no trips")
    void testDatabaseIsInitializedAndNoTripIsPersisted() {
        assertThat(TripService.getAllTrips()).isNotNull();
        assertThat(TripService.getAllTrips()).isEmpty();
    }

    @Test
    @DisplayName("Trip is successfully persisted to Database")
    void testTripIsAddedToDatabase() throws LocationAlreadyExistsException, UsernameAlreadyExistsException {
        UserService.addUser(ADMIN, ADMIN, "Travel Agent");
        LoginController.setLoggedUser(ADMIN);
        TripService.addTrip(ADMIN, ADMIN, ADMIN);
        assertThat(TripService.getAllTrips()).isNotEmpty();
        assertThat(TripService.getAllTrips()).size().isEqualTo(1);
        Trip trip = TripService.getAllTrips().get(0);
        assertThat(trip).isNotNull();
        assertThat(trip.getLocation()).isEqualTo(ADMIN);
        assertThat(trip.getPeriod()).isEqualTo(ADMIN);
        assertThat(trip.getPrice()).isEqualTo(ADMIN);
        assertThat(trip.getBook()).isEqualTo("0");
        assertThat(trip.getAllSet()).isEqualTo(ADMIN);
    }
    @Test
    @DisplayName("Trip can not be added twice")
    void testTripCanNotBeAddedTwice() {
        assertThrows(LocationAlreadyExistsException.class, () -> {
            UserService.addUser(ADMIN, ADMIN, "Travel Agent");
            LoginController.setLoggedUser(ADMIN);
            TripService.addTrip(ADMIN, ADMIN, ADMIN);
            TripService.addTrip(ADMIN, ADMIN, ADMIN);
        });
    }
    @Test
    @DisplayName("Trip is successfully persisted to Database")
    void testGetAllTripsCustomer() throws LocationAlreadyExistsException, UsernameAlreadyExistsException {

        UserService.addUser(ADMIN2, ADMIN, "Travel Agent");
        UserService.addUser(ADMIN, ADMIN, "Customer");

        LoginController.setLoggedUser(ADMIN2);
        TripService.addTrip(ADMIN, ADMIN, ADMIN);

        assertThat(TripService.getAllTrips()).size().isEqualTo(1);
        Trip trip = TripService.getAllTrips().get(0);

        LoginController.setLoggedUser(ADMIN);
        assertThat(TripService.getAllTripsCustomer()).size().isEqualTo(1);
        TripService.BookThisTrip(trip);

        LoginController.setLoggedUser(ADMIN2);
        TripService.addTrip(ADMIN3, ADMIN, ADMIN);
        TripService.AllSetThisTrip(trip);

        LoginController.setLoggedUser(ADMIN);

        Trip tr = TripService.getTrip(ADMIN3);
        assertThat(TripService.getMyBookedTrips()).size().isEqualTo(0);
      //  Trip trip1 = TripService.getAllTripsCustomer().get(1);
        LoginController.setLoggedUser(ADMIN2);
        TripService.clearTrip(ADMIN3, ADMIN, ADMIN);
        assertThat(TripService.getAllTripsCustomer()).size().isEqualTo(0);
        assertThat(TripService.getMyTripsCustomer()).size().isEqualTo(0);
    }
}