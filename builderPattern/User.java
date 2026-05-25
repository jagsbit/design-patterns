package builderPattern;

public class User {
    private int id;
    private String name;
    private double marks;

    public static class UserBuilder {
        private int id;
        private String name;
        private double marks;

        public UserBuilder id(int id){
            this.id=id;
            return this;
        }
        public UserBuilder name(String name){
            this.name=name;
            return this;
        }
        public UserBuilder marks(double marks){
            this.marks=marks;
            return this;
        }

        public User build(){
            return new User(this);
        }
        
    }

    public User(UserBuilder ub){
        this.id=ub.id;
        this.name=ub.name;
        this.marks=ub.marks;
    }

    public String toString(){
        return this.id+" : "+this.name+" : "+this.marks;
    }
}
