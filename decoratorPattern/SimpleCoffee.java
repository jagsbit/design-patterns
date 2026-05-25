package decoratorPattern;

public class SimpleCoffee implements Coffee {
    public String getDescription(){
         return "Simple Coffee";
    }
    public double getCost(){
        return 50;
    }
}
