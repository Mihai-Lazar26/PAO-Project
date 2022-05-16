package pao.proiect.obiecte;

public class Client extends User{
    int age;
    public Client(String username, String email, String parola, String age){
        super(username, email, parola);
        this.age = Integer.parseInt(age);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
