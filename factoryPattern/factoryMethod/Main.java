package factoryPattern.factoryMethod;

public class Main {
   public static void main(String[] args) {
       
        VehicleFactory factory1=new BikeFactory();
        VehicleFactory factory2=new CarFactory();

        Vehicle bike=factory1.createVehicle();
        Vehicle car=factory2.createVehicle();

       
        car.wheels();
         bike.wheels();
   }
}
