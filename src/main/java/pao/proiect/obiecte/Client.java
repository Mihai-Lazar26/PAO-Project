package pao.proiect.obiecte;

public class Client extends User{
    int age;
    public Client(String username, String email, String parola, int age){
        super(username, email, parola);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Client{" +
                "userId=" + this.getUserId() +
                ", username='" + this.getUsername() + '\'' +
                ", email='" + this.getEmail() + '\'' +
                ", age=" + age +
                '}';
    }

    public Integer getPrice(){
        if(this.age <= 10){
            return 10;
        }
        if(this.age <= 20){
            return 15;
        }

        return 20;
    }
}
