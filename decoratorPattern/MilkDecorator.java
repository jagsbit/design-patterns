package decoratorPattern;

public class MilkDecorator extends Decorator{
    public MilkDecorator(Coffee coffee){
        super(coffee);
    }
    public String getDescription(){
         return coffee.getDescription()+" + Milk";
    }
    public double getCost(){
        return coffee.getCost()+10;
    }
}
