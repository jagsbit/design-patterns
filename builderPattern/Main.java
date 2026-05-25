package builderPattern;

public class Main {
    public static void main(String[] args) {
         
        User user1=new User.UserBuilder()
                    .id(1)
                    .name("jags")
                    .marks(100)
                    .build();
        User user2=new User.UserBuilder()
                    .id(2)
                    .name("bhc")
                    .build();
        System.out.println(user1);
        System.out.println(user2);
        
        
        
    }
}
