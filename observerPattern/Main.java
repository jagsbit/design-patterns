package observerPattern;

public class Main {
    public static void main(String[] args) {
          PelamPelCoding pelamPelCoding=new PelamPelCoding("PelamPel coding");

          Subscriber harsh=new Subscriber("Harsh");
          Subscriber anshuman=new Subscriber("Anshuman");
          pelamPelCoding.register(anshuman);
          pelamPelCoding.register(harsh);

          pelamPelCoding.notifySubscribers("Read Repair in distributed system");
          pelamPelCoding.notifySubscribers("Rebalancing distributed sytem");

    }
}
