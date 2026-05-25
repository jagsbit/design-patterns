package factoryPattern.simpleFactory;

public class VehicleFactory {
    public Vehilce getVehicle(String vehicle){
        vehicle=vehicle.trim().toLowerCase();
        Vehilce v=null;
        switch (vehicle) {
            case "bike":
                v=new Bike();
                break;
            case "car":
                v=new Car();
                break;
        
            default:
                v=new Bike();
                break;
        }

        return v;
    }
}
