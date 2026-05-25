package decoratorPattern;

public class Main {
    public static void main(String[] args) {
          Coffee coffee=new SimpleCoffee();
          System.out.println(coffee.getDescription());
          System.out.println(coffee.getCost());
          coffee=new MilkDecorator(coffee);
          System.out.println(coffee.getDescription());
          System.out.println(coffee.getCost());
          coffee=new SugarDecorator(coffee);
          System.out.println(coffee.getDescription());
          System.out.println(coffee.getCost());
    }
}
