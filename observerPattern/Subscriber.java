package observerPattern;

public class Subscriber {
    private String name;
    Subscriber(String name){
            this.name=name;
    }
    public void notified(String title,String channelName){
         System.out.println("Hyy "+name+" :"+title+": upload by "+channelName);
    }
}
