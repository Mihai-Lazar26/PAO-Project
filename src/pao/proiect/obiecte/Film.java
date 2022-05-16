package pao.proiect.obiecte;

import java.util.Objects;

public class Film {
    private String titlu;
    private String gen;
    private String rating;

    public Film(String titlu, String gen, String rating){
        this.titlu = titlu;
        this.gen = gen;
        this.rating = rating;
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

    public String getGen() {
        return gen;
    }

    public String getRating() {
        return rating;
    }

    public String getTitlu() {
        return titlu;
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
