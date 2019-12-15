package pl.wojcikiewicz;

import pl.wojcikiewicz.service.RentVehicleService;

public class Runner {

    public static void main(String [] args) {
        //ConnectionUtil.getConnection();

        RentVehicleService rentVehicleService = new RentVehicleService(null);
        rentVehicleService.isVehicleInUse(null);
    }
}
