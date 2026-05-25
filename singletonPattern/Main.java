package singletonPattern;

public class Main {
    public static void main(String[] args) {
          User user=User.getUser();
          User user2=User.getUser();
          System.out.println(user.hashCode());
          System.out.println(user2.hashCode());

          Student s1=Student.INSTANCE;
          Student s2=Student.INSTANCE;
          System.out.println(s1.hashCode());
          System.out.println(s2.hashCode());
    }
}
