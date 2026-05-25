package singletonPattern;

public class User {
    private static User user=null;
    private User(){
        System.out.println("User Created");
    }

    public static User getUser(){
         if(user==null){
            synchronized(User.class){
                if(user==null){
                      user=new User();
                }
        
             }
         }

          return user;
    }


}
