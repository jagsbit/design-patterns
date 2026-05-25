package factoryPattern.abstractFactory;

public class PetrolVehicleFactory implements VehicleFactory {
    public Car createCar(){
         return new PetrolCar();
    }
    public Bike createBike(){
        return new PetrolBike();
    }
}
