package pao.proiect.obiecte;

import java.util.Objects;

abstract public class User {
    private String username;
    private String email;
    private String parola;

    public User(String username, String email, String parola){
        this.username = username;
        this.email = email;
        this.parola = parola;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getUsername(){
        return this.username;
    }

    public String getEmail(){
        return this.email;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", parola='" + parola + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
//        return Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(parola, user.parola);
        return Objects.equals(username, user.username) || Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, parola);
    }

    public Boolean checkPassword(String parola){
        if(this.parola == parola){
            return true;
        }
        return false;
    }

    public void changePassword(String parola, String parolaNoua){
        if(this.checkPassword(parola)){
            this.parola = parolaNoua;
            System.out.println("Parola a fost modificata cu succes!");
        }
        else {
            System.out.println("Parola introdusa este gresita!");
        }
    }

    public Boolean login(String nume, String parola){
        if((this.username == nume || this.email == nume) && this.checkPassword(parola)){
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    public void afis(){
        System.out.println("Username: " + this.username);
        System.out.println("Email: " + this.email);
    }
}
