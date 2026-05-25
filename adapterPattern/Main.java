package adapterPattern;

public class Main {
    public static void main(String[] args) {
           Iphone iphone17=new Iphone();
           IphoneCharger charger=new Adapter(new RealmeCharger());
           iphone17.setCharger(charger);
           iphone17.chargePhone();
    }
}
