package pao.proiect.obiecte;

import java.time.LocalDateTime;
import java.util.ArrayList;

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
}
