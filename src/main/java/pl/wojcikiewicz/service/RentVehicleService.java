package pl.wojcikiewicz.service;

import pl.wojcikiewicz.model.*;
import pl.wojcikiewicz.service.exception.UserNotAdultException;
import pl.wojcikiewicz.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static pl.wojcikiewicz.model.RideInformation.SELECT_VEHICLES_IN_USE_QUERY;

public class RentVehicleService {

    private static final int ADULT_AGE = 18;
    private static final int RIDE_EXPIRATION_IN_SECONDS = 10800;

    private Connection connection;

    private Set<Vehicle> vehiclesInUse = new HashSet<>();
    private Set<RideInformation> ridesInformation = new HashSet<>();

    public RentVehicleService(Set<Vehicle> vehiclesInUse) { 
        this.connection = ConnectionUtil.getConnection();

        this.vehiclesInUse = vehiclesInUse;

    }

    public RideInformation rentVehicle(User user, Vehicle vehicle, Address startAddress)
            throws UserNotAdultException {

        if (TransportType.CAR == vehicle.getTransportType()) {
            if (!isUserAdult(user)) {
                String message = "You're not allowed to drive a car while age under 18!";
                throw new UserNotAdultException(message);
            }
        }

        RideInformation rideInformation = null;

        if (!isVehicleInUse(vehicle)) {
            if (isUserAllowedToRentVehicle(user)) {
                rideInformation = new RideInformation();
                rideInformation.setUser(user);
                rideInformation.setVehicle(vehicle);
                rideInformation.setStartAddress(startAddress);
                rideInformation.setStartDateTime(LocalDateTime.now());
            }
        }

        return rideInformation;
    }

    public Set<RideInformation> getExpiredRentedRides() {
        return null;
    }

    public boolean isUserAdult(User user) {
        if (user == null) {
            return false;
        }

        int currentYear = LocalDate.now().getYear();

        return (currentYear - user.getDob().getYear()) >= ADULT_AGE;
    }

    public boolean isVehicleInUse(Vehicle vehicle) {
        boolean inUse = false;

        try {
            Statement statement = this.connection.createStatement();
            String query = SELECT_VEHICLES_IN_USE_QUERY.concat(String.valueOf(vehicle.getId()));

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.last()) {
                String idEndAddress = resultSet.getString("id_end_address");

                inUse = idEndAddress == null ? false : true;
            }
        } catch (SQLException e) {
            // add logger
            e.printStackTrace();
        }

        return inUse;
    }

    private boolean isUserAllowedToRentVehicle(User user) {
        return ridesInformation.stream()
                .map(RideInformation::getUser)
                .filter(activeUser -> activeUser.getId().equals(user.getId()))
                .findFirst()
                .isPresent();
    }
}
