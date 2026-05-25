package strategyPattern;

public class PaymentContext {
    private PaymentStrategy paymentStrategy;
    public void setPaymentStrategy(PaymentStrategy paymentStrategy){
         this.paymentStrategy=paymentStrategy;
    }
    public void pay(){
        if (paymentStrategy==null) {
            throw new RuntimeException("Payment Failed");
        }
        paymentStrategy.pay();
    }
}
