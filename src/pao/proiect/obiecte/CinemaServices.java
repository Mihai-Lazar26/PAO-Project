package pao.proiect.obiecte;

import java.time.LocalDateTime;

public class CinemaServices {
    private static CinemaServices instance = null;

    private User user = null;
    private CinemaData cinemaData = CinemaData.getInstance();

    private CinemaServices(){}

    public static CinemaServices getInstance() {

        if(instance == null) {
            instance = new CinemaServices();
        }
        return instance;
    }

    public void login(String nume, String parola){
        this.user = this.cinemaData.findUser(nume, parola);
        if(this.user == null){
            System.out.println("Numele sau/si parola au fost introduse gresit, incercati din nou!");
        }
        else{
            System.out.println("Conectarea s-a realizat cu succes!");
        }
    }

    public void logout(){
        this.user = null;
        System.out.println("Ati fost deconectat cu succes");
    }

    public void signInClient(String username, String email, String parola, String reParola, int age){
        if(parola == reParola){
            User newUser = new Client(username, email, parola, age);
            this.cinemaData.addUser(newUser);
        }
        else{
            System.out.println("Parolele nu se aseamana!");
        }
    }

    public void signInAdmin(String username, String email, String parola, String reParola){
        if(this.user instanceof Admin){
            if(parola == reParola){
                User newUser = new Admin(username, email, parola);
                this.cinemaData.addUser(newUser);
            }
            else{
                System.out.println("Parolele nu se aseamana!");
            }
        }
        else{
            System.out.println("Nu aveti permisiune pentru aceasta actiune!");
        }

    }

    public void addNewFilm(String titlu, String gen, String rating, int durata){
        if(this.user instanceof Admin){
            Film newFilm = new Film(titlu, gen, rating, durata);
            cinemaData.addFilm(newFilm);
        }
        else{
            System.out.println("Nu aveti permisiune pentru aceasta actiune!");
        }
    }

    public void addNewSala(String nume, int nrLocuri){
        if(this.user instanceof Admin){
            Sala newSala = new Sala(nume, nrLocuri);
            cinemaData.addSala(newSala);
        }
        else{
            System.out.println("Nu aveti permisiune pentru aceasta actiune!");
        }
    }

    public void addNewDifuzare(Film film, Sala sala, LocalDateTime date){
        if(this.user instanceof Admin){
            Difuzare newDifuzare = new Difuzare(film, sala, date);
            cinemaData.addDifuzare(newDifuzare);
        }
        else{
            System.out.println("Nu aveti permisiune pentru aceasta actiune!");
        }
    }

    public void addNewBilet(Difuzare difuzare, int pret, int loc){
        if(this.user instanceof Client){
            Client client = (Client) this.user;
            Bilet newBilet = Bilet.newBilet(client, difuzare, pret, loc);
            if(newBilet != null){
                cinemaData.addBilet(newBilet);
            }
        }
    }
}
