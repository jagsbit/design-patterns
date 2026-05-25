package decoratorPattern;

public class SugarDecorator extends Decorator{
    public SugarDecorator(Coffee coffee){
        super(coffee);
    }
    public String getDescription(){
         return coffee.getDescription()+" + Sugar";
    }
    public double getCost(){
        return coffee.getCost()+10;
    }
}
