package pao.proiect.obiecte;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

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

    private void showUser(){
        if(this.user != null){
            System.out.println(this.user);
        }
        else{
            System.out.println("No user connected!");
        }
    }

    private void login(String nume, String parola){
        this.user = this.cinemaData.findUser(nume, parola);
        if(this.user == null){
            System.out.println("Numele sau/si parola au fost introduse gresit, incercati din nou!");
        }
        else{
            System.out.println("Conectarea s-a realizat cu succes!");
        }
    }

    private void logout(){
        this.user = null;
        System.out.println("Ati fost deconectat cu succes");
    }

    private void signInClient(String username, String email, String parola, String reParola, int age){
        if(parola.equals(reParola)){
            User newUser = new Client(username, email, parola, age);
            this.cinemaData.addUser(newUser);
        }
        else{
            System.out.println("Parolele nu se aseamana!");
        }
    }

    private void signInAdmin(String username, String email, String parola, String reParola){
        if(this.user instanceof Admin){
            Admin admin = (Admin) this.user;
            admin.signInAdmin(username, email, parola, reParola);
        }
        else{
            System.out.println("Nu aveti permisiune pentru aceasta actiune!");
        }

    }

    private void changePassword(String parolaVeche, String parolaNoua, String reParolaNoua){
        this.user.changePassword(parolaVeche, parolaNoua, reParolaNoua);
    }

    private void addNewFilm(String titlu, String gen, String rating, int durata){
        if(this.user instanceof Admin){
            Admin admin = (Admin) this.user;
            admin.addNewFilm(titlu, gen, rating, durata);
        }
        else{
            System.out.println("Nu aveti permisiune pentru aceasta actiune!");
        }
    }

    private void addNewSala(String nume, int nrLocuri){
        if(this.user instanceof Admin){
            Admin admin = (Admin) this.user;
            admin.addNewSala(nume, nrLocuri);
        }
        else{
            System.out.println("Nu aveti permisiune pentru aceasta actiune!");
        }
    }

    private void addNewDifuzare(Film film, Sala sala, LocalDateTime date){
        if(this.user instanceof Admin){
            Admin admin = (Admin) this.user;
            admin.addNewDifuzare(film, sala, date);
        }
        else{
            System.out.println("Nu aveti permisiune pentru aceasta actiune!");
        }
    }

    private void addNewBilet(Difuzare difuzare, int loc, int pret){
        if(this.user instanceof Client){
            Client client = (Client) this.user;
            Bilet newBilet = Bilet.newBilet(client, difuzare, loc, pret);
            if(newBilet != null){
                cinemaData.addBilet(newBilet);
            }
        }
    }

    private void cumparaBilet(){
        Scanner scanner = new Scanner(System.in);
        Integer comanda;
        ArrayList<Difuzare> difuzariDisponibile = cinemaData.findDifuzariValabile();
        if(difuzariDisponibile.size() > 0){
            System.out.println("Difuzarile disponibile sunt: ");
            Integer it = 1;
            for(Difuzare d : difuzariDisponibile){
                System.out.println("Difuzarea " + it + ':');
                System.out.println(d);
                it+=1;
            }
            System.out.print("Introduceti numarul difuzari pentru care cumparati biletul: ");
            comanda = scanner.nextInt();

            if (comanda < 1 || comanda > difuzariDisponibile.size()){
                System.out.println("Difuzarea cu numarul dat nu exista!");
            }
            else{
                Difuzare difuzare = difuzariDisponibile.get(comanda - 1);
                System.out.println(difuzare);
                ArrayList<Integer> locuri = difuzare.getLocuriLibere();
                System.out.println("Locurile libere sunt: ");
                for(Integer val : locuri){
                    System.out.print(val.toString() + ' ');
                }
                System.out.println();
                System.out.print("Introduceti numarul locului: ");
                comanda = scanner.nextInt();
                if(locuri.contains(comanda)){
                    Client client = (Client) this.user;
                    addNewBilet(difuzare, comanda, client.getPrice());
                }
                else{
                    System.out.println("Locul introdus este ocupat sau nu exista!");
                }
            }
        }
        else{
            System.out.println("Nu sunt difuzari disponibile!");
        }

    }

    private void anuleazaBilet(){
        Client client = (Client) this.user;
        ArrayList<Bilet> bileteClient = cinemaData.getClientBileteAnulabile(client);
        Integer comanda;
        Scanner scanner = new Scanner(System.in);
        Integer it = 1;
        if(bileteClient.size() > 0){
            for (Bilet b : bileteClient){
                System.out.println("Biletul " + it + ':');
                System.out.println(b);
                it += 1;
            }
            System.out.print("Introduceti numarul biletului pe care doriti sa-l anulati: ");
            comanda = scanner.nextInt();
            if(comanda < 1 || comanda > bileteClient.size()){
                System.out.println("Numarul biletului introdus nu exista!");
            }
            else{
                Bilet bilet = bileteClient.get(comanda-1);
                cinemaData.removeBilet(bilet);
            }
        }
        else{
            System.out.println("Nu aveti bilete care pot fi anulate!");
        }


    }

    private void comenziClient(){
        Scanner scanner = new Scanner(System.in);
        Integer comanda;
        while (Boolean.TRUE){
            System.out.println("Apasati tastele:\n" +
                    "  1.cumpara bilet\n" +
                    "  2.anuleaza bilet\n" +
                    "  3.schimba parola\n" +
                    "  4.arata userul curent\n" +
                    "  5.logout\n" +
                    "  6.exit");
            System.out.print("Comanda: ");
            comanda = scanner.nextInt();
            switch (comanda){
                case 1:
                    cumparaBilet();
                    break;
                case 2:
                    anuleazaBilet();
                    break;
                case 3:
                    System.out.print("Introduceti parola curenta: ");
                    String parola = scanner.next();
                    System.out.print("Introduceti parola noua: ");
                    String parolaNoua = scanner.next();
                    System.out.print("Reintroduceti parola noua: ");
                    String reParolaNoua = scanner.next();
                    changePassword(parola, parolaNoua, reParolaNoua);
                    break;
                case 4:
                    showUser();
                    break;
                case 5:
                    cinemaData.save();
                    logout();
                    break;
                case 6:
                    cinemaData.save();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Comanda nu exista!");
                    break;
            }
            if(comanda == 5){
                break;
            }
        }
    }

    private void comenziAdmin(){
        Scanner scanner = new Scanner(System.in);
        Integer comanda;
        while(Boolean.TRUE){
            System.out.println("Apasati tastele:\n" +
                    "  1.adaugare cont nou admin\n" +
                    "  2.adaugare film\n" +
                    "  3.adaugare sala\n" +
                    "  4.adaugare difuzare\n" +
                    "  5.eliminare admin\n" +
                    "  6.eliminare film\n" +
                    "  7.eliminare sala\n" +
                    "  8.eliminare difuzare\n" +
                    "  9.schimbare parola\n" +
                    "  10.arata datele\n" +
                    "  11.logout\n" +
                    "  12.exit");
            System.out.print("Comanda: ");
            comanda = scanner.nextInt();
            switch (comanda){
                case 1:
                    System.out.print("Introduceti username-ul: ");
                    String usernameA = scanner.next();
                    System.out.print("Introduceti email-ul: ");
                    String emailA = scanner.next();
                    System.out.print("Introduceti parola: ");
                    String parolaA = scanner.next();
                    System.out.print("Reintroduceti parola: ");
                    String reParolaA = scanner.next();
                    signInAdmin(usernameA, emailA, parolaA, reParolaA);
                    break;
                case 2:
                    System.out.print("Titlu film: ");
                    String titluF = scanner.next();
                    System.out.print("Gen film: ");
                    String genF = scanner.next();
                    System.out.print("Rating film: ");
                    String ratingF = scanner.next();
                    System.out.print("Durata film (minute): ");
                    int durataF = scanner.nextInt();
                    addNewFilm(titluF, genF, ratingF, durataF);
                    break;
                case 3:
                    System.out.print("Nume sala: ");
                    String numeS = scanner.next();
                    System.out.print("Numar locuri: ");
                    int nrLocuri = scanner.nextInt();
                    addNewSala(numeS, nrLocuri);
                    break;
                case 4:
                    LinkedList<Film> filme = cinemaData.getFilme();
                    ArrayList<Sala> sali = cinemaData.getSali();
                    Integer it = 1;
                    if(filme.size() == 0){
                        System.out.println("Nu exista filme ce pot fi selecate!");
                        break;
                    }
                    if(sali.size() == 0){
                        System.out.println("Nu exista sali ce pot fi selectate!");
                        break;
                    }
                    for(Film f : filme){
                        System.out.println("Filmul " + it.toString() + ":");
                        System.out.println(f);
                        it += 1;
                    }
                    System.out.print("Selectati numarul filmului: ");
                    Integer filmIt = scanner.nextInt();
                    Film selectedFilm;
                    if(filmIt >= 1 && filmIt <= filme.size()){
                        selectedFilm = filme.get(filmIt-1);
                    }
                    else{
                        System.out.println("Filmul selectat nu exista!");
                        break;
                    }

                    it = 1;
                    for(Sala s : sali){
                        System.out.println("Sala " + it.toString() + ":");
                        System.out.println(s);
                        it += 1;
                    }

                    System.out.print("Selecati numarul sali: ");
                    Integer salaIt = scanner.nextInt();
                    Sala selectedSala;
                    if(salaIt >= 1 && salaIt <= sali.size()){
                        selectedSala = sali.get(salaIt-1);
                    }
                    else{
                        System.out.println("Sala selectata nu exista!");
                        break;
                    }

                    System.out.print("Anul: ");
                    Integer an = scanner.nextInt();
                    System.out.print("Luna: ");
                    Integer luna = scanner.nextInt();
                    System.out.print("Ziua: ");
                    Integer zi = scanner.nextInt();
                    System.out.print("Ora: ");
                    Integer ora = scanner.nextInt();
                    System.out.print("Minutul: ");
                    Integer minut = scanner.nextInt();

                    LocalDateTime dateD = LocalDateTime.of(an, luna, zi, ora, minut, 0);

                    addNewDifuzare(selectedFilm, selectedSala, dateD);
                    break;
                case 5:
                    Admin admin = (Admin) this.user;
                    ArrayList<Admin> admini = cinemaData.getAdmini(admin);
                    if(admini.size() == 0){
                        System.out.println("Nu exista admini care pot fi eliminati!");
                        break;
                    }
                    Integer it5 = 1;
                    for(Admin a : admini){
                        System.out.println("Admin " + it5.toString() + ": ");
                        System.out.println(a);
                        it5 += 1;
                    }
                    System.out.print("Selectati numarul adminului pe care vreti sa-l eliminati: ");
                    Integer adminIt = scanner.nextInt();
                    if(adminIt < 1 || adminIt > admini.size()){
                        System.out.println("Adminul selectat nu exista!");
                        break;
                    }
                    Admin selectedAdmin = admini.get(adminIt-1);
                    cinemaData.removeUser(selectedAdmin);
                    break;
                case 6:
                    LinkedList<Film> filme6 = cinemaData.getFilme();
                    if(filme6.size() == 0){
                        System.out.println("Nu exista filme ce pot fi selectate!");
                        break;
                    }
                    Integer it6 = 1;
                    for(Film f : filme6){
                        System.out.println("Filmul " + it6.toString() + ':');
                        System.out.println(f);
                        it6 += 1;
                    }
                    System.out.print("Selectati numarul filmului pe care doriti sa-l eliminati: ");
                    Integer selectedFilmIt6 = scanner.nextInt();
                    if(selectedFilmIt6 < 0 || selectedFilmIt6 > filme6.size()){
                        System.out.println("Nu exista filmul selectat!");
                        break;
                    }
                    Film selectedFilm6 = filme6.get(selectedFilmIt6-1);
                    cinemaData.removeFilm(selectedFilm6);
                    break;
                case 7:
                    ArrayList<Sala> sali7 = cinemaData.getSali();
                    if(sali7.size() == 0){
                        System.out.println("Nu exista sali ce pot fi selectate!");
                        break;
                    }
                    Integer it7 = 1;
                    for(Sala s : sali7){
                        System.out.println("Sala " + it7.toString() + ':');
                        System.out.println(s);
                        it7 += 1;
                    }
                    System.out.print("Selectati sala: ");
                    Integer salaIt7 = scanner.nextInt();
                    if(salaIt7 < 1 || salaIt7 > sali7.size()){
                        System.out.println("Nu exista sala selectata!");
                        break;
                    }
                    Sala selectSala7 = sali7.get(salaIt7-1);
                    cinemaData.removeSala(selectSala7);
                    break;
                case 8:
                    ArrayList<Difuzare> difuzari8 = cinemaData.getDifuzari();
                    if(difuzari8.size() == 0){
                        System.out.println("Nu exista difuzari ce pot fi selectate!");
                        break;
                    }
                    Integer it8 = 1;
                    for(Difuzare d : difuzari8){
                        System.out.println("Difuzare " + it8.toString() + ":");
                        System.out.println(d);
                        it8 += 1;
                    }
                    System.out.println("Selectati difuzarea: ");
                    Integer difIt8 = scanner.nextInt();
                    if(difIt8 < 1 || difIt8 > difuzari8.size()){
                        System.out.println("Nu exista difuzarea selectata!");
                        break;
                    }
                    Difuzare selectDif = difuzari8.get(difIt8-1);
                    cinemaData.removeDifuzare(selectDif);
                    break;
                case 9:
                    System.out.print("Introduceti parola curenta: ");
                    String parola = scanner.next();
                    System.out.print("Introduceti parola noua: ");
                    String parolaNoua = scanner.next();
                    System.out.print("Reintroduceti parola noua: ");
                    String reParolaNoua = scanner.next();
                    changePassword(parola, parolaNoua, reParolaNoua);
                    break;
                case 10:
                    ArrayList<User> usersShow = cinemaData.getUsers();
                    LinkedList<Film> filmeShow = cinemaData.getFilme();
                    ArrayList<Sala> saliShow = cinemaData.getSali();
                    ArrayList<Difuzare> difuzariShow = cinemaData.getDifuzari();
                    ArrayList<Bilet> bileteShow = cinemaData.getBilete();

                    System.out.println("Users:");
                    for(User u : usersShow){
                        System.out.println(u);
                    }
                    System.out.println("Filme:");
                    for(Film f : filmeShow){
                        System.out.println(f);
                    }
                    System.out.println("Sali:");
                    for(Sala s : saliShow){
                        System.out.println(s);
                    }
                    System.out.println("Difuzari:");
                    for(Difuzare d : difuzariShow){
                        System.out.println(d);
                    }
                    System.out.println("Bilete:");
                    for(Bilet b : bileteShow){
                        System.out.println(b);
                    }
                    break;
                case 11:
                    cinemaData.save();
                    logout();
                    break;
                case 12:
                    cinemaData.save();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Comanda nu exista");
                    break;
            }
            if(comanda == 11){
                break;
            }
        }
    }

    public void meniu(){

        Scanner scanner = new Scanner(System.in);
        Integer comanda;
        while (Boolean.TRUE){
            System.out.println("Apasati tastele:\n" +
                    "  1.login\n" +
                    "  2.sign in\n" +
                    "  3.exit");
            System.out.print("Comanda: ");
            comanda = scanner.nextInt();
            switch (comanda){
                case 1:
                    System.out.print("Introduceti numele sau emailul: ");
                    String nume = scanner.next();
                    System.out.print("Introduceti paroloa: ");
                    String parola = scanner.next();
                    login(nume, parola);
                    if(this.user != null){
                        if(this.user instanceof Client){
                            comenziClient();
                        }
                        else if(this.user instanceof Admin) {
                            comenziAdmin();
                        }
                    }
                    break;
                case 2:
                    System.out.print("Introduceti username-ul: ");
                    String usernameC = scanner.next();
                    System.out.print("Introduceti email-ul: ");
                    String emailC = scanner.next();
                    System.out.print("Introduceti parola: ");
                    String parolaC = scanner.next();
                    System.out.print("Reintroduceti parola: ");
                    String reParolaC = scanner.next();
                    System.out.print("Introduceti varsta: ");
                    Integer age = scanner.nextInt();
                    signInClient(usernameC, emailC, parolaC, reParolaC, age);
                    break;
                case 3:
                    cinemaData.save();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Comanda nu exista!");
                    break;
            }
        }
    }
}
