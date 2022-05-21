package pao.proiect.obiecte;

import java.time.LocalDateTime;

public class Admin extends User{
    public Admin(String username, String email, String parola){
        super(username, email, parola);
    }

    protected void signInAdmin(String username, String email, String parola, String reParola){
        CinemaData cinemaData = CinemaData.getInstance();
        if(parola.equals(reParola)){
            User newUser = new Admin(username, email, parola);
            cinemaData.addUser(newUser);
        }
        else{
            System.out.println("Parolele nu se aseamana!");
        }
    }

    protected void addNewFilm(String titlu, String gen, String rating, int durata){
        CinemaData cinemaData = CinemaData.getInstance();
        Film newFilm = new Film(titlu, gen, rating, durata);
        cinemaData.addFilm(newFilm);
    }

    protected void addNewSala(String nume, int nrLocuri){
        CinemaData cinemaData = CinemaData.getInstance();
        Sala newSala = new Sala(nume, nrLocuri);
        cinemaData.addSala(newSala);
    }

    protected void addNewDifuzare(Film film, Sala sala, LocalDateTime date){
        CinemaData cinemaData = CinemaData.getInstance();
        Difuzare newDifuzare = new Difuzare(film, sala, date);
        cinemaData.addDifuzare(newDifuzare);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "username='" + this.getUsername() + '\'' +
                ", email='" + this.getEmail() + '\'' +
                '}';
    }
}
