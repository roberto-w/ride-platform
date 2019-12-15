//package pl.wojcikiewicz.service;
//
//import org.junit.Before;
//import org.junit.Test;
//import pl.wojcikiewicz.model.RideInformation;
//import pl.wojcikiewicz.model.TransportType;
//import pl.wojcikiewicz.model.User;
//import pl.wojcikiewicz.model.Vehicle;
//import pl.wojcikiewicz.service.exception.UserNotAdultException;
//
//import java.time.LocalDate;
//import java.util.HashSet;
//import java.util.Set;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//public class RentVehicleServiceTest {
//
//    private RentVehicleService rentVehicleService;
//    private Set<Vehicle> vehicles;
//
//    @Before
//    public void setUp() {
//        Vehicle testVehicle = createTestVehicle(TransportType.CAR, "Model Test");
//
//        this.vehicles = new HashSet<>();
//        this.vehicles.add(testVehicle);
//    }
//
//    @Test
//    public void isUserAdult_whenUserIsEighteenOrOlder_shouldReturnTrue() {
//        // given
//        this.rentVehicleService = new RentVehicleService(vehicles);
//
//        final User testUser =
//                new User("Name01", "LastName01", LocalDate.of(1990, 10, 17));
//
//        // when
//        boolean result = this.rentVehicleService.isUserAdult(testUser);
//
//        // then
//        assertThat(result, is(true));
//        //assertEquals(true, result);
//    }
//
////    @Test
////    public void isUserAdult_whenUserIsBelowEighteenOr_shouldReturnFalse() {
////
////    }
//
//    @Test
//    public void isUserAdult_whenUserIsNull_shouldReturnFalse() {
//        // given
//        this.rentVehicleService = new RentVehicleService(vehicles);
//
//        final User testUser = null;
//
//        // when
//        boolean result = this.rentVehicleService.isUserAdult(testUser);
//
//        // then
//        assertEquals(false, result);
//    }
//
//    @Test
//    public void rentVehicle_whenCorrectInputData_thenVehicleIsRent() throws UserNotAdultException {
//        // given
//        this.rentVehicleService = new RentVehicleService(vehicles);
//
//        // when
//        RideInformation result = this.rentVehicleService.rentVehicle(null, null, null);
//
//        // then
//        assertNotNull(result);
//        assertNotNull(result.getStartDateTime());
//        assertThat(result.getStartDateTime().toLocalDate(), is(LocalDate.now()));
//    }
//
//    @Test(expected = UserNotAdultException.class)
//    public void rentVehicle_whenIsIsNotAdult_thenThrowException() throws UserNotAdultException {
//        // given
//        Vehicle vehicle = new Vehicle();
//        vehicle.setTransportType(TransportType.CAR);
//
//        this.rentVehicleService = new RentVehicleService(vehicles);
//
//        User testUser =
//                new User("Name01", "LastName01", LocalDate.of(2010, 10, 17));
//        // when
//        this.rentVehicleService.rentVehicle(testUser, vehicle, null);
//    }
//
//    private Vehicle createTestVehicle(TransportType transportType, String model) {
//        Vehicle vehicle = new Vehicle();
//        vehicle.setTransportType(transportType);
//        vehicle.setModel(model);
//
//        return vehicle;
//    }
//}