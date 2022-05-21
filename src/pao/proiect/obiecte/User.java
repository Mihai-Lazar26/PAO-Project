package pao.proiect.obiecte;

import java.util.Objects;

abstract public class User {
    private int userId;
    private String username;
    private String email;
    private String parola;

    public User(String username, String email, String parola){
        this.userId = Iduri.getUserId();
        this.username = username;
        this.email = email;
        this.parola = parola;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public int getUserId() {
        return userId;
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
        if(this.parola.equals(parola)){
            return true;
        }
        return false;
    }

    public void changePassword(String parolaVeche, String parolaNoua, String reParolaNoua){
        if(this.checkPassword(parolaVeche)){
            if(parolaNoua.equals(reParolaNoua)){
                this.parola = parolaNoua;
                System.out.println("Parola a fost modificata cu succes!");
            }
            else {
                System.out.println("Parola introdusa este gresita!");
            }
        }
        else {
            System.out.println("Parola introdusa este gresita!");
        }
    }

    public Boolean login(String nume, String parola){
        if((this.username.equals(nume) || this.email.equals(nume)) && this.checkPassword(parola)){
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    public void afis(){
        System.out.println("Username: " + this.username);
        System.out.println("Email: " + this.email);
    }
}
