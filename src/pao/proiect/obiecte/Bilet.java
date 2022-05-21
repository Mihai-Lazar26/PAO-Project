package pao.proiect.obiecte;

import java.util.Objects;

public class Bilet {

    private int biletId;
    private int clientId;
    private int difuzareId;
    private int pret;
    private int loc;

    public static Bilet newBilet(Client client, Difuzare difuzare, int loc, int pret){
        if(difuzare.getLoc(loc).equals(Boolean.FALSE)){
            Bilet bilet = new Bilet(client, difuzare, loc, pret);
            return bilet;
        }
        return null;
    }

    private Bilet(Client client, Difuzare difuzare, int loc, int pret){
        this.biletId = Iduri.getBiletId();
        this.clientId = client.getUserId();
        this.difuzareId = difuzare.getDifuzareId();
        this.pret = pret;
        this.loc = loc;
    }

    public int getBiletId() {
        return biletId;
    }

    public Client getClient() {
        CinemaData cinemaData = CinemaData.getInstance();
        Client client = (Client) cinemaData.getUserById(this.clientId);
        return client;
    }

    public Difuzare getDifuzare() {
        CinemaData cinemaData = CinemaData.getInstance();
        Difuzare difuzare = cinemaData.getDifuzareById(this.difuzareId);
        return difuzare;
    }

    public int getPret() {
        return pret;
    }

    public int getLoc() {
        return loc;
    }

    public void setBiletId(int biletId) {
        this.biletId = biletId;
    }

//    public void setClient(Client client) {
//        this.clientId = client.getUserId();
//    }
//
//    public void setDifuzare(Difuzare difuzare) {
//        this.difuzareId = difuzare.getDifuzareId();
//    }

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

        return loc == bilet.loc && Objects.equals(this.getDifuzare(), bilet.getDifuzare());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getDifuzare(), loc);
    }

    @Override
    public String toString() {
        return "Bilet{" +
                "biletId=" + biletId +
                ", clientId=" + clientId +
                ", difuzareId=" + difuzareId +
                ", pret=" + pret +
                ", loc=" + loc +
                '}';
    }
}
