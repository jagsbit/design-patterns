package observerPattern;

import java.util.ArrayList;
import java.util.List;

public class PelamPelCoding implements YoutubeChannel {

    private String name;

    private List<Subscriber> subscribers=new ArrayList<>();
    PelamPelCoding(String name){
         this.name=name;
    }

    @Override
    public void register(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void deregister(Subscriber subscriber) {
         subscribers.remove(subscriber);
        
    }

    @Override
    public void notifySubscribers(String title) {
          for(Subscriber subscriber:subscribers){
                subscriber.notified(title,name);
          }
    }
    
}
