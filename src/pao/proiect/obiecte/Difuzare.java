package pao.proiect.obiecte;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Difuzare {
    private Film film;
    private Sala sala;
    private Boolean locuriOcupate[];
    private LocalDateTime date;

    public Difuzare(Film film, Sala sala){
        this.date = LocalDateTime.now();
        this.film = film;
        this.sala = sala;

        this.locuriOcupate = new Boolean[sala.nrLocuri];
        for(int i = 0; i < this.sala.nrLocuri; ++i){
            this.locuriOcupate[i] = Boolean.FALSE;
        }
    }

    public Difuzare(Film film, Sala sala, LocalDateTime date){
        this.date = date;
        this.film = film;
        this.sala = sala;

        this.locuriOcupate = new Boolean[sala.nrLocuri];
        for(int i = 0; i < this.sala.nrLocuri; ++i){
            this.locuriOcupate[i] = Boolean.FALSE;
        }
    }

    public void setLoc(int nrLoc, Boolean bool){
        this.locuriOcupate[nrLoc-1] = bool;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
        Boolean oldLocuri[] = this.locuriOcupate;
        this.locuriOcupate = new Boolean[this.sala.nrLocuri];
        for(int i = 0; i < this.sala.nrLocuri; ++i){
            this.locuriOcupate[i] = oldLocuri[i];
        }
    }

    public Film getFilm() {
        return film;
    }

    public Sala getSala() {
        return sala;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Boolean getLoc(int loc){
        return this.locuriOcupate[loc-1];
    }


    public ArrayList<Integer> getLocuriLibere(){
        ArrayList<Integer> locuri = new ArrayList<Integer>();
        for(int i = 0; i < this.sala.nrLocuri; ++i){
            if(this.locuriOcupate[i] == Boolean.FALSE){
                locuri.add(i+1);
            }
        }
        return locuri;
    }

    public ArrayList<Integer> getLocuriOcupate(){
        ArrayList<Integer> locuri = new ArrayList<Integer>();
        for(int i = 0; i < this.sala.nrLocuri; ++i){
            if(this.locuriOcupate[i] == Boolean.TRUE){
                locuri.add(i+1);
            }
        }
        return locuri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Difuzare difuzare = (Difuzare) o;

        LocalDateTime dateA1 = this.date;
        LocalDateTime dateA2 = dateA1.plusMinutes(this.film.getDurata());
        LocalDateTime dateB1 = difuzare.date;
        LocalDateTime dateB2 = dateB1.plusMinutes(difuzare.film.getDurata());

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

        return Objects.equals(sala, difuzare.sala) && intersect;
    }

    @Override
    public int hashCode() {
        return Objects.hash(film, sala, date);
    }
}
