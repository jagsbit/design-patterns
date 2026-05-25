package factoryPattern.simpleFactory;

public class Main {
    public static void main(String[] args) {
         VehicleFactory factory=new VehicleFactory();
         Vehilce car=factory.getVehicle("Car ");
         Vehilce bike=factory.getVehicle("biKe ");

         car.wheels();
         bike.wheels();
    }
}
