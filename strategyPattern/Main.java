package strategyPattern;

public class Main {
    public static void main(String[] args) {
        PaymentContext paymentContext=new PaymentContext();
       // paymentContext.pay();
        paymentContext.setPaymentStrategy(new UPIPayment());
        paymentContext.pay();
        paymentContext.setPaymentStrategy(new CredicardPayment());
        paymentContext.pay();

    }
}
