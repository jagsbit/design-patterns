package decoratorPattern;

public abstract class Decorator implements Coffee {
    public Coffee coffee;
    public Decorator(Coffee coffee){
        this.coffee=coffee;
    }
}
