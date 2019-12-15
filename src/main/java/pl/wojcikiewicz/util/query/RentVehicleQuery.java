package pl.wojcikiewicz.util.query;

import pl.wojcikiewicz.model.User;
import pl.wojcikiewicz.model.Vehicle;

import java.util.Map;

public final class RentVehicleQuery {

    public static final String insertIntoQuery(Map<String, Object> queryParameters) {

        String query = "INSERT INTO ride_information"
                + " (id_user, id_vehicle, id_start_address, id_end_address, start_time, end_time, id_price)"
                + " VALUES (idUser, idVehicle, idStartAddress, idEndAddress, startTime, endTime, idPrice)";

        User user = (User) queryParameters.get("idUser");
        query.replace("idUser", String.valueOf(user.getId()));

        Vehicle vehicle = (Vehicle) queryParameters.get("idVehicle");
        query.replace("idVehicle", String.valueOf(vehicle.getId()));

        return query;
    }
}
