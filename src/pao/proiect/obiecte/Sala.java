package pao.proiect.obiecte;

import java.util.ArrayList;
import java.util.Objects;

public class Sala {
    protected int salaId;
    protected String nume;
    protected int nrLocuri;

    public Sala(String nume, int nrLocuri){
        this.salaId = Iduri.getSalaId();
        this.nume = nume;
        this.nrLocuri = nrLocuri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sala sala = (Sala) o;
        return Objects.equals(nume, sala.nume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, nrLocuri);
    }

    @Override
    public String toString() {
        return "Sala{" +
                "salaId=" + salaId +
                ", nume='" + nume + '\'' +
                ", nrLocuri=" + nrLocuri +
                '}';
    }
}
