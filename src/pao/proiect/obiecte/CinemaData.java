package pao.proiect.obiecte;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

public class CinemaData {
    private static CinemaData instance = null;

    private ArrayList<User> users;
    private LinkedList<Film> filme;
    private ArrayList<Sala> sali;
    private ArrayList<Difuzare> difuzari;
    private ArrayList<Bilet> bilete;

    public static CinemaData getInstance() {
        if(instance == null) {
            instance = new CinemaData();
        }
        return instance;
    }

    private void tempLoadData(){
        this.users.add(new Client("c1", "ce1", "cp1", 10));
        this.users.add(new Client("c2", "ce2", "cp2", 20));
        this.users.add(new Admin("a1", "ae1", "ap1"));
        this.users.add(new Admin("a2", "ae2", "ap2"));

        this.filme.add(new Film("film 1", "actiune", "PG", 120));
        this.filme.add(new Film("film 2", "aventura", "R", 110));

        this.sali.add(new Sala("sala A", 100));
        this.sali.add(new Sala("Sala B", 50));

        this.difuzari.add(new Difuzare(this.filme.get(1), this.sali.get(1),
                LocalDateTime.of(2022, 5, 25, 20, 0)));
        this.difuzari.add(new Difuzare(this.filme.get(0), this.sali.get(0),
                LocalDateTime.of(2021, 6, 10, 15, 30)));
    }

    private CinemaData(){
        this.users = new ArrayList<User>();
        this.filme = new LinkedList<Film>();
        this.sali = new ArrayList<Sala>();
        this.difuzari = new ArrayList<Difuzare>();
        this.bilete = new ArrayList<Bilet>();

        tempLoadData();
    }

    public ArrayList<Difuzare> getDifuzari() {
        return difuzari;
    }

    public ArrayList<Sala> getSali() {
        return sali;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public LinkedList<Film> getFilme() {
        return filme;
    }

    public ArrayList<Bilet> getBilete() {
        return bilete;
    }

    public void setDifuzari(ArrayList<Difuzare> difuzari) {
        this.difuzari = difuzari;
    }

    public void setFilme(LinkedList<Film> filme) {
        this.filme = filme;
    }

    public void setSali(ArrayList<Sala> sali) {
        this.sali = sali;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void setBilete(ArrayList<Bilet> bilete) {
        this.bilete = bilete;
    }

    public void addUser(User user){

        if(this.userIn(user) == Boolean.FALSE){
            this.users.add(user);
            System.out.println("Utilizatorul a fost adaugat cu succes!");
        }
        else{
            System.out.println("Utilizatorul nu a fost adaugat!");
        }

    }

    public void addFilm(Film film){
        if(this.filmIn(film) == Boolean.FALSE){
            this.filme.add(film);
            System.out.println("Filmul a fost adaugat cu succes!");
        }
        else{
            System.out.println("Filmul nu a fost adaugat!");
        }

    }

    public void addSala(Sala sala){
        if(this.salaIn(sala) == Boolean.FALSE){
            this.sali.add(sala);
            System.out.println("Sala a fost adaugata cu succes!");
        }
        else {
            System.out.println("Sala nu a fost adaugata!");
        }

    }

    public void addDifuzare(Difuzare difuzare){
        if(this.difuzareIn(difuzare) == Boolean.FALSE){
            this.difuzari.add(difuzare);
            System.out.println("Difuzarea a fost adaugata cu succes!");
        }
        else {
            System.out.println("Difuzarea nu a fost adaugata!");
        }

    }

    public void addBilet(Bilet bilet){
        if(this.biletIn(bilet) == Boolean.FALSE){
            this.bilete.add(bilet);
            Difuzare dif = bilet.getDifuzare();
            dif.setLoc(bilet.getLoc(), Boolean.TRUE);
            System.out.println("Biletul a fost adaugat cu succes!");
        }
        else {
            System.out.println("Biletul nu a fost adaugat");
        }

    }

    public void removeUser(User user){
        ArrayList<User> userCopy = new ArrayList<User>(this.users);
        for(User u : userCopy){
            if(u.equals(user)){
                this.users.remove(u);
                System.out.println("Utilizatorul a fost eliminat cu succes!");
                return;
            }
        }
        System.out.println("Utilizatorul nu a fost eliminat!");
    }

    public void removeFilm(Film film){
        LinkedList<Film> filmeCopy = new LinkedList<Film>(this.filme);
        ArrayList<Difuzare> difuzariCopy = new ArrayList<Difuzare>(this.difuzari);
        ArrayList<Bilet> bileteCopy = new ArrayList<Bilet>(this.bilete);
        for(Film f : filmeCopy){
            if(f.equals(film)){
                for(Difuzare d : difuzariCopy){
                    if(f.equals(d.getFilm())){
                        for(Bilet b : bileteCopy){
                            if(d.equals(b.getDifuzare())){
                                this.bilete.remove(b);
                            }
                        }
                        this.difuzari.remove(d);
                    }
                }
                this.filme.remove(f);
                System.out.println("Filmul a fost eliminat cu succes!");
                return;
            }
        }
        System.out.println("Filmul nu a fost eliminat!");
    }

    public void removeSala(Sala sala){
        ArrayList<Sala> saliCopy = new ArrayList<Sala>(this.sali);
        ArrayList<Difuzare> difuzariCopy = new ArrayList<Difuzare>(this.difuzari);
        ArrayList<Bilet> bileteCopy = new ArrayList<Bilet>(this.bilete);
        for(Sala s : saliCopy){
            if(s.equals(sala)){
                for(Difuzare d : difuzariCopy){
                    if(s.equals(d.getSala())){
                        for(Bilet b : bileteCopy){
                            if(d.equals(b.getDifuzare())){
                                this.bilete.remove(b);
                            }
                        }
                        this.difuzari.remove(d);
                    }
                }
                this.sali.remove(s);
                System.out.println("Sala a fost eliminata cu succes!");
                return;
            }
        }
        System.out.println("Sala nu a fost eliminata");
    }

    public void removeDifuzare(Difuzare difuzare){
        ArrayList<Difuzare> difuzariCopy = new ArrayList<Difuzare>(this.difuzari);
        ArrayList<Bilet> bileteCopy = new ArrayList<Bilet>(this.bilete);
        for(Difuzare d : difuzariCopy){
            if(d.equals(difuzare)){
                for(Bilet b : bileteCopy){
                    if(d.equals(b.getDifuzare())){
                        this.bilete.remove(b);
                    }
                }
                this.difuzari.remove(d);
                System.out.println("Difuzarea a fost eliminata cu succes!");
                return;
            }
        }
        System.out.println("Difuzarea nu a fost eliminata!");
    }

    public ArrayList<Difuzare> findDifuzariValabile(){
        ArrayList<Difuzare> difuzariDisponibile = new ArrayList<Difuzare>();
        for(Difuzare d : this.difuzari){
            ArrayList<Integer> locuri = d.getLocuriLibere();
            if(locuri.size() != 0 && LocalDateTime.now().isBefore(d.getDate())){
                difuzariDisponibile.add(d);
            }
        }
        return difuzariDisponibile;
    }


    public void removeBilet(Bilet bilet){
        ArrayList<Bilet> bileteCopy = new ArrayList<Bilet>(this.bilete);
        for(Bilet b : bileteCopy){
            if(b.equals(bilet)){
                for(Difuzare d : this.difuzari){
                    if(d.equals(b.getDifuzare())){
                        d.setLoc(b.getLoc(), Boolean.FALSE);
                        break;
                    }
                }
                this.bilete.remove((b));
                System.out.println("Biletul a fost eliminat cu succes!");
                return;
            }
        }
        System.out.println("Biletul nu a fost eliminat");
    }

    public Boolean userIn(User user){
        for (User u : this.users){
            if(u.equals(user)){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public Boolean filmIn(Film film){
        for(Film f : this.filme){
            if(f.equals(film)){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public Boolean salaIn(Sala sala){
        for(Sala s : this.sali){
            if(s.equals(sala)){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public Boolean difuzareIn(Difuzare difuzare){
        for(Difuzare d : this.difuzari){
            if(d.equals(difuzare)){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public Boolean biletIn(Bilet bilet){

        for(Bilet b : this.bilete){
            if(b.equals(bilet)){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public User findUser(String nume, String parola){
        for(User u : this.users){
            if(u.login(nume, parola)) {
                return u;
            }
        }

        return null;
    }

    public User getUserById(int id){
        for(User u : this.users){
            if(u.getUserId() == id){
                return u;
            }
        }
        return null;
    }

    public Film getFilmById(int id){
        for(Film f : this.filme){
            if(f.getFilmId() == id){
                return f;
            }
        }
        return null;
    }

    public Sala getSalaById(int id){
        for (Sala s : this.sali){
            if(s.salaId == id){
                return s;
            }
        }
        return null;
    }

    public Difuzare getDifuzareById(int id){
        for(Difuzare d : this.difuzari){
            if(d.getDifuzareId() == id){
                return d;
            }
        }
        return null;
    }

    public Bilet getBiletById(int id){
        for(Bilet b : this.bilete){
            if(b.getBiletId() == id){
                return b;
            }
        }
        return null;
    }

    public ArrayList<Bilet> getClientBileteAnulabile(Client client){
        ArrayList<Bilet> bileteClient = new ArrayList<Bilet>();
        for (Bilet b : this.bilete){
            Client clientBilet = b.getClient();
            Difuzare difuzareBilet = b.getDifuzare();
            LocalDateTime dateDifuzare = difuzareBilet.getDate();
            dateDifuzare = dateDifuzare.minusDays(1);

            if(client.equals(clientBilet) && LocalDateTime.now().isBefore(dateDifuzare)){
                bileteClient.add(b);
            }
        }
        return  bileteClient;
    }

    public ArrayList<Admin> getAdmini(Admin admin){
        ArrayList<Admin> admini = new ArrayList<Admin>();
        for(User u : this.users){
            if(u instanceof Admin){
                Admin adminObj = (Admin) u;
                if(adminObj.equals(admin)){
                    continue;
                }

                admini.add(adminObj);
            }
        }
        return admini;
    }

}
