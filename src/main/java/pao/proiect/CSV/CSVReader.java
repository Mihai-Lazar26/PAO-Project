package pao.proiect.CSV;

import pao.proiect.obiecte.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;

public class CSVReader {
    public static CSVReader instance = null;
    private String usersPath;
    private String filmePath;
    private String saliPath;
    private String difuzariPath;
    private String biletePath;
    private String iduriPath;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private String auditingPath;

    public static CSVReader getInstance() {
        if(instance == null){
            instance = new CSVReader();
        }
        return instance;
    }

    private CSVReader(){
        String path = System.getProperty("user.dir") + "\\src\\main\\java\\pao\\proiect\\CSV";
        this.usersPath = path + "\\users.csv";
        this.filmePath = path + "\\filme.csv";
        this.saliPath = path + "\\sali.csv";
        this.difuzariPath = path + "\\difuzari.csv";
        this.biletePath = path + "\\bilete.csv";
        this.iduriPath = path + "\\iduri.csv";
        this.auditingPath = path + "\\auditing.csv";
    }

    public ArrayList<User> loadUsers(){
        ArrayList<User> users = new ArrayList<User>();
        String line;

        try{
            BufferedReader br = new BufferedReader(new FileReader(this.usersPath));
            line = br.readLine();
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                if(values[4].equals("null")){
                    Admin admin = new Admin(values[1], values[2], values[3]);
                    admin.setUserId(Integer.parseInt(values[0]));
                    users.add(admin);
                }
                else{
                    Client client = new Client(values[1], values[2], values[3], Integer.parseInt(values[4]));
                    client.setUserId(Integer.parseInt(values[0]));
                    users.add(client);
                }
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return users;
    }

    public LinkedList<Film> loadFilme(){
        LinkedList<Film> filme = new LinkedList<Film>();
        String line;
        try{
            BufferedReader br = new BufferedReader(new FileReader(this.filmePath));
            line = br.readLine();
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                Film film = new Film(values[1], values[2], values[3], Integer.parseInt(values[4]));
                film.setFilmId(Integer.parseInt(values[0]));
                filme.add(film);
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return filme;
    }

    public ArrayList<Sala> loadSali(){
        ArrayList<Sala> sali = new ArrayList<Sala>();
        String line;
        try{
            BufferedReader br = new BufferedReader(new FileReader(this.saliPath));
            line = br.readLine();
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                Sala sala = new Sala(values[1], Integer.parseInt(values[2]));
                sala.setSalaId(Integer.parseInt(values[0]));
                sali.add(sala);
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return sali;
    }

    public ArrayList<Difuzare> loadDifuzari(){
        ArrayList<Difuzare> difuzari = new ArrayList<Difuzare>();
        String line;
        try{
            BufferedReader br = new BufferedReader(new FileReader(this.difuzariPath));
            line = br.readLine();
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                String[] locuri = values[3].split(" ");
                Boolean[] valLocuri = new Boolean[locuri.length];
                for(int i = 0; i < locuri.length; ++i){
                    if(locuri[i].equals("F")){
                        valLocuri[i] = Boolean.FALSE;
                    }
                    else{
                        valLocuri[i] = Boolean.TRUE;
                    }
                }

                LocalDateTime localDateTime = LocalDateTime.parse(values[4], this.formatter);
//                Difuzare(int filmId, int salaId, Boolean locuri[], LocalDateTime date)
                Difuzare difuzare = new Difuzare(Integer.parseInt(values[1]), Integer.parseInt(values[2]), valLocuri, localDateTime);
                difuzare.setDifuzareId(Integer.parseInt(values[0]));
                difuzari.add(difuzare);

            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return difuzari;
    }

    public ArrayList<Bilet> loadBilete(){
        ArrayList<Bilet> bilete = new ArrayList<Bilet>();
        String line;
        try{
            BufferedReader br = new BufferedReader(new FileReader(this.biletePath));
            line = br.readLine();
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
//                public static Bilet newBilet(Client client, Difuzare difuzare, int loc, int pret)
                Bilet bilet = Bilet.newBilet(Integer.parseInt(values[1]), Integer.parseInt(values[2]),
                        Integer.parseInt(values[3]), Integer.parseInt(values[4]));
                bilet.setBiletId(Integer.parseInt(values[0]));
                bilete.add(bilet);
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return bilete;
    }

    public Integer[] loadIds(){
        Integer iduri[] = new Integer[5];
        for(Integer i = 0; i < 5; ++i){
            iduri[i] = 0;
        }
        String line;
        try{
            BufferedReader br = new BufferedReader(new FileReader(this.iduriPath));
            line = br.readLine();
            line = br.readLine();
            String[] values = line.split(",");
            for(int i = 0; i < values.length; ++i){
                iduri[i] = Integer.parseInt(values[i]);
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return iduri;
    }

    public void saveUsers(ArrayList<User> users){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.usersPath, false));
            PrintWriter pw = new PrintWriter(bw);
            String primaLinie = "userId,username,email,parola,age";
            pw.println(primaLinie);
            for(User u : users){
                if(u instanceof Client){
                    Client client = (Client) u;
                    pw.println("" + u.getUserId() + ',' + u.getUsername() + ',' + u.getEmail() + ',' + u.getParola() + ',' + client.getAge());
                }
                else{
                    pw.println("" + u.getUserId() + ',' + u.getUsername() + ',' + u.getEmail() + ',' + u.getParola() + ',' + "null");
                }
            }
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveFilme(LinkedList<Film> filme){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.filmePath, false));
            PrintWriter pw = new PrintWriter(bw);
            String primaLinie = "filmId,titlu,gen,rating,durata";
            pw.println(primaLinie);
            for(Film f : filme){
                pw.println("" + f.getFilmId() + ',' + f.getTitlu() + ',' + f.getGen() + ',' + f.getRating() + ',' + f.getDurata());
            }
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveSali(ArrayList<Sala> sali){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.saliPath, false));
            PrintWriter pw = new PrintWriter(bw);
            String primaLinie = "salaId,nume,nrLocuri";
            pw.println(primaLinie);
            for(Sala s : sali){
                pw.println("" + s.getSalaId() + ',' + s.getNume() + ',' + s.getNrLocuri());
            }
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveDifuzari(ArrayList<Difuzare> difuzari){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.difuzariPath, false));
            PrintWriter pw = new PrintWriter(bw);
            String primaLinie = "difuzareId,filmId,salaId,locuri,date";
            pw.println(primaLinie);
            for(Difuzare d : difuzari){
                Boolean listLocuri[] = d.getLocuri();
                String locuri = "";
                for(int i = 0; i < listLocuri.length - 1; ++i){
                    if(listLocuri[i] == Boolean.FALSE){
                        locuri += "F ";
                    }
                    else{
                        locuri += "T ";
                    }
                }
                if(listLocuri[listLocuri.length-1] == Boolean.FALSE){
                    locuri += "F";
                }
                else{
                    locuri += "T";
                }

                LocalDateTime date = d.getDate();
                String formatDate = date.format(formatter);

                pw.println("" + d.getDifuzareId() + ',' + d.getFilmId() + ',' + d.getSalaId() + ',' + locuri + ',' + formatDate);
            }
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveBilete(ArrayList<Bilet> bilete){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.biletePath, false));
            PrintWriter pw = new PrintWriter(bw);
            String primaLinie = "biletId,clientId,difuzareId,loc,pret";
            pw.println(primaLinie);
            for(Bilet b : bilete){
                pw.println("" + b.getBiletId() + ',' + b.getClientId() + ',' + b.getDifuzareId() + ',' + b.getLoc() + ',' + b.getPret());
            }
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveIduri(Integer iduri[]){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.iduriPath, false));
            PrintWriter pw = new PrintWriter(bw);
            String primaLinie = "userId,filmId,salaId,difuzareId,biletId";
            pw.println(primaLinie);
            pw.println("" + iduri[0] + ',' + iduri[1] + ',' + iduri[2] + ',' + iduri[3] + ',' + iduri[4]);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAuditing(String action, LocalDateTime timeStamp){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.auditingPath, true));
            PrintWriter pw = new PrintWriter(bw);
            String formatedDate = timeStamp.format(formatter);
            pw.println("" + action + ',' + formatedDate);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
