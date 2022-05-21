package pao.proiect.obiecte;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Difuzare {

    private int difuzareId;
    private int filmId;
    private int salaId;
    private Boolean locuri[];
    private LocalDateTime date;

    public Difuzare(Film film, Sala sala){
        this.date = LocalDateTime.now();
        this.difuzareId = Iduri.getDifuzareId();
        this.filmId = film.getFilmId();
        this.salaId = sala.salaId;

        this.locuri = new Boolean[sala.nrLocuri];
        for(int i = 0; i < sala.nrLocuri; ++i){
            this.locuri[i] = Boolean.FALSE;
        }
    }

    public Difuzare(Film film, Sala sala, LocalDateTime date){
        this.date = date;
        this.difuzareId = Iduri.getDifuzareId();
        this.difuzareId = Iduri.getDifuzareId();
        this.filmId = film.getFilmId();
        this.salaId = sala.salaId;

        this.locuri = new Boolean[sala.nrLocuri];
        for(int i = 0; i < sala.nrLocuri; ++i){
            this.locuri[i] = Boolean.FALSE;
        }
    }

    public void setDifuzareId(int difuzareId) {
        this.difuzareId = difuzareId;
    }

    public void setLoc(int nrLoc, Boolean bool){
        this.locuri[nrLoc-1] = bool;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setFilmId(Film film) {
        this.filmId = film.getFilmId();
    }

    public void setSala(Sala sala) {
        this.salaId = sala.salaId;
        CinemaData cinemaData = CinemaData.getInstance();
        Sala salaNoua = cinemaData.getSalaById(this.salaId);

        Boolean oldLocuri[] = this.locuri;
        this.locuri = new Boolean[salaNoua.nrLocuri];

        for(int i = 0; i < salaNoua.nrLocuri; ++i){
            this.locuri[i] = oldLocuri[i];
        }
    }

    public int getDifuzareId() {
        return difuzareId;
    }

    public Film getFilm() {
        CinemaData cinemaData = CinemaData.getInstance();
        Film film = cinemaData.getFilmById(this.filmId);
        return film;
    }

    public Sala getSala() {
        CinemaData cinemaData = CinemaData.getInstance();
        Sala sala = cinemaData.getSalaById(this.salaId);
        return sala;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Boolean getLoc(int loc){
        return this.locuri[loc-1];
    }


    public ArrayList<Integer> getLocuriLibere(){
        ArrayList<Integer> locuri = new ArrayList<Integer>();
        Sala sala = this.getSala();
        for(int i = 0; i < sala.nrLocuri; ++i){
            if(this.locuri[i] == Boolean.FALSE){
                locuri.add(i+1);
            }
        }
        return locuri;
    }

    public ArrayList<Integer> getLocuriOcupate(){
        ArrayList<Integer> locuri = new ArrayList<Integer>();
        Sala sala = this.getSala();
        for(int i = 0; i < sala.nrLocuri; ++i){
            if(this.locuri[i] == Boolean.TRUE){
                locuri.add(i+1);
            }
        }
        return locuri;
    }

    public void afisLocuri(){
        for (int i = 0; i < this.locuri.length; ++i){
            System.out.print(i+1);
            System.out.print(": ");
            System.out.print(this.locuri[i]);
            System.out.println();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Difuzare difuzare = (Difuzare) o;
        Film film1 = this.getFilm();
        Film film2 = difuzare.getFilm();

        LocalDateTime dateA1 = this.date;
        LocalDateTime dateA2 = dateA1.plusMinutes(film1.getDurata());
        LocalDateTime dateB1 = difuzare.date;
        LocalDateTime dateB2 = dateB1.plusMinutes(film2.getDurata());

        int pauzaIntreDifuzari = 10;
        dateA1 = dateA1.minusMinutes(pauzaIntreDifuzari);
        dateA2 = dateA2.plusMinutes(pauzaIntreDifuzari);
        dateB1 = dateB1.minusMinutes(pauzaIntreDifuzari);
        dateB2 = dateB2.plusMinutes(pauzaIntreDifuzari);

        Boolean intersect = Boolean.FALSE;

        if( (dateA1.isBefore(dateB1) && dateA2.isAfter(dateB1)) ||
            (dateA1.isBefore(dateB2) && dateA2.isAfter(dateB2)) ||
            (dateB1.isBefore(dateA1) && dateB2.isAfter(dateA1)) ||
            (dateB1.isBefore(dateA2) && dateB2.isAfter(dateA2))){
            intersect = Boolean.TRUE;
        }

        Sala sala1 = this.getSala();
        Sala sala2 = difuzare.getSala();

        return Objects.equals(sala1, sala2) && intersect;
    }

    @Override
    public int hashCode() {
        Film film = this.getFilm();
        Sala sala = this.getSala();
        return Objects.hash(film, sala, date);
    }

    @Override
    public String toString() {
        return "Difuzare{" +
                "difuzareId=" + difuzareId +
                ", filmId=" + this.getFilm().toString() +
                ", salaId=" + this.getSala().toString() +
                ", date=" + date +
                '}';
    }
}
