package strategyPattern;

public class CredicardPayment implements PaymentStrategy{
    public void pay(){
        System.out.println("Payment Done with Credit Card");
    }
}
