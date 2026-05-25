package adapterPattern;

public class Iphone {
    private IphoneCharger charger;
    public void setCharger(IphoneCharger charger){
         this.charger=charger;
    }
    public void chargePhone(){
         charger.charge();
         System.out.println("Iphone is Charged");
    }
}
