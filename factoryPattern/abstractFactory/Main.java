package factoryPattern.abstractFactory;

public class Main {
    public static void main(String[] args) {
        
        VehicleFactory factory1=new ElectricVehicleFactory();
        VehicleFactory factory2=new PetrolVehicleFactory();


        // electric vehicle

        Bike bike1=factory1.createBike();
        Car car1= factory1.createCar();

        // petro vehicle


        Bike bike2=factory2.createBike();
        Car car2=factory2.createCar();

        bike1.ride();
        car1.drive();
        bike2.ride();
        car2.drive();




    }
}
