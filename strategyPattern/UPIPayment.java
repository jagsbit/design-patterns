package strategyPattern;

public class UPIPayment implements PaymentStrategy{
    public void pay(){
        System.out.println("Payment done with Upi");
    }
}
