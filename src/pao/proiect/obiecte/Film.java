package pao.proiect.obiecte;

import java.util.Objects;

public class Film {
    private int filmId;
    private String titlu;
    private String gen;
    private String rating;
    private int durata;

    public Film(String titlu, String gen, String rating, int durata){
        this.filmId = Iduri.getFilmId();
        this.titlu = titlu;
        this.gen = gen;
        this.rating = rating;
        this.durata = durata; // In minute
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public void setTitlu(String titlu){
        this.titlu = titlu;
    }

    public void setGen(String gen){
        this.gen = gen;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public int getFilmId() {
        return filmId;
    }

    public String getGen() {
        return gen;
    }

    public String getRating() {
        return rating;
    }

    public String getTitlu() {
        return titlu;
    }

    public int getDurata() {
        return durata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(titlu, film.titlu) && Objects.equals(gen, film.gen) && Objects.equals(rating, film.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titlu, gen, rating);
    }

    @Override
    public String toString() {
        return "Film{" +
                "titlu='" + titlu + '\'' +
                ", gen='" + gen + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }

}
