package adapterPattern;

public class Adapter implements IphoneCharger {
    private AndroidCharger androidCharger;
    Adapter(AndroidCharger androidCharger){
            this.androidCharger=androidCharger;
    }
    public void charge(){
        androidCharger.charge();
    }
}
