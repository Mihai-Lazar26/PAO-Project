package pao.proiect.obiecte;

import java.util.ArrayList;
import java.util.Objects;

public class Bilet {
    private Client client;
    private Difuzare difuzare;
    private int pret;
    private int loc;

    public static Bilet newBilet(Client client, Difuzare difuzare, int pret, int loc){
        if(difuzare.getLoc(loc) == Boolean.FALSE){
            Bilet bilet = new Bilet(client, difuzare, pret, loc);
            return bilet;
        }
        return null;
    }

    private Bilet(Client client, Difuzare difuzare, int pret, int loc){
        this.client = client;
        this.difuzare = difuzare;
        this.pret = pret;
        this.loc = loc;
    }

    public Client getClient() {
        return client;
    }

    public Difuzare getDifuzare() {
        return difuzare;
    }

    public int getPret() {
        return pret;
    }

    public int getLoc() {
        return loc;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setDifuzare(Difuzare difuzare) {
        this.difuzare = difuzare;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public void setLoc(int loc) {
        this.loc = loc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bilet bilet = (Bilet) o;
        return loc == bilet.loc && Objects.equals(difuzare, bilet.difuzare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(difuzare, loc);
    }
}
