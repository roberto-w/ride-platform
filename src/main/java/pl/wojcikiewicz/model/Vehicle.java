package pl.wojcikiewicz.model;

public class Vehicle {
    private Integer id;
    private TransportType transportType;
    private String model;

    public Vehicle() {
    }

    public Vehicle(TransportType transportType, String model) {
        this.transportType = transportType;
        this.model = model;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
