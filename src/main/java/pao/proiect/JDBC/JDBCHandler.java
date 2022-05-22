package pao.proiect.JDBC;

import pao.proiect.config.DatabaseConfiguration;
import pao.proiect.obiecte.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;

public class JDBCHandler {
    public static JDBCHandler instance = null;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static JDBCHandler getInstamnce() {
        if(instance == null){
            instance = new JDBCHandler();
        }
        return instance;
    }

    private JDBCHandler(){
        init();
    }

    public void createUser(){
//        userId,username,email,parola,age
        String query = "CREATE TABLE IF NOT EXISTS user " +
                "(userId INT, " +
                "username VARCHAR(100)," +
                "email VARCHAR(100)," +
                "parola VARCHAR(100)," +
                "age VARCHAR(10))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();

    }

    public void createFilm(){
//        filmId,titlu,gen,rating,durata
        String query = "CREATE TABLE IF NOT EXISTS film " +
                "(filmId INT, " +
                "titlu VARCHAR(100)," +
                "gen VARCHAR(100)," +
                "rating VARCHAR(100)," +
                "durata INT)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();

    }

    public void createSala(){
//        salaId,nume,nrLocuri
        String query = "CREATE TABLE IF NOT EXISTS sala " +
                "(salaId INT, " +
                "nume VARCHAR(100)," +
                "nrLocuri INT)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();

    }

    public void createDifuzari(){
//        difuzareId,filmId,salaId,locuri,date
        String query = "CREATE TABLE IF NOT EXISTS difuzare " +
                "(difuzareId INT, " +
                "filmId INT," +
                "salaId INT," +
                "locuri LONGTEXT," +
                "date VARCHAR(100))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();

    }

    public void createBilet(){
//        biletId,clientId,difuzareId,loc,pret
        String query = "CREATE TABLE IF NOT EXISTS bilet " +
                "(biletId INT, " +
                "clientId INT," +
                "difuzareId INT," +
                "loc INT," +
                "pret INT)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();

    }

    public void createIduri(){
//        userId,filmId,salaId,difuzareId,biletId
        String query = "CREATE TABLE IF NOT EXISTS iduri " +
                "(userId INT, " +
                "filmId INT," +
                "salaId INT," +
                "difuzareId INT," +
                "biletId INT)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();

    }

    public void createAuditing(){
//        name_of_action,timestamp
        String query = "CREATE TABLE IF NOT EXISTS auditing " +
                "(name_of_action VARCHAR(100), " +
                "timestamp VARCHAR(100))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();

    }


    public void init(){
        createUser();
        createFilm();
        createSala();
        createDifuzari();
        createBilet();
        createIduri();
        createAuditing();
    }

    public ArrayList<User> loadUsers(){
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        String query = "SELECT * FROM user";

        ArrayList<User> users = new ArrayList<User>();

        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                Integer userId = resultSet.getInt(1);
                String username = resultSet.getString(2);
                String email = resultSet.getString(3);
                String parola = resultSet.getString(4);
                String age = resultSet.getString(5);

                if(age.equals("null")){
                    Admin admin = new Admin(username, email, parola);
                    admin.setUserId(userId);

                    users.add(admin);
                }
                else{
                    Client client = new Client(username, email, parola, Integer.parseInt(age));
                    client.setUserId(userId);

                    users.add(client);
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();
        return users;
    }

    public LinkedList<Film> loadFilme(){
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        String query = "SELECT * FROM film";

        LinkedList<Film> filme = new LinkedList<Film>();

        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                Integer filmId = resultSet.getInt(1);
                String titlu = resultSet.getString(2);
                String gen = resultSet.getString(3);
                String rating = resultSet.getString(4);
                Integer durata = resultSet.getInt(5);

                Film film = new Film(titlu, gen, rating, durata);
                film.setFilmId(filmId);

                filme.add(film);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();
        return filme;
    }

    public ArrayList<Sala> loadSali(){
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        String query = "SELECT * FROM sala";

        ArrayList<Sala> sali = new ArrayList<Sala>();

        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
//                salaId,nume,nrLocuri
                Integer salaId = resultSet.getInt(1);
                String nume = resultSet.getString(2);
                Integer nrLocuri = resultSet.getInt(3);

                Sala sala = new Sala(nume, nrLocuri);
                sala.setSalaId(salaId);

                sali.add(sala);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();
        return sali;
    }

    public ArrayList<Difuzare> loadDifuzari(){
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        String query = "SELECT * FROM difuzare";

        ArrayList<Difuzare> difuzari = new ArrayList<Difuzare>();

        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
//                difuzareId,filmId,salaId,locuri,date
                Integer difuzareId = resultSet.getInt(1);
                Integer filmId = resultSet.getInt(2);
                Integer salaId = resultSet.getInt(3);
                String locuriStr = resultSet.getString(4);
                String date = resultSet.getString(5);

                String[] locuri = locuriStr.split(" ");
                Boolean[] valLocuri = new Boolean[locuri.length];
                for(int i = 0; i < locuri.length; ++i){
                    if(locuri[i].equals("F")){
                        valLocuri[i] = Boolean.FALSE;
                    }
                    else{
                        valLocuri[i] = Boolean.TRUE;
                    }
                }
                LocalDateTime localDateTime = LocalDateTime.parse(date, this.formatter);
                Difuzare difuzare = new Difuzare(filmId, salaId, valLocuri, localDateTime);
                difuzare.setDifuzareId(difuzareId);

                difuzari.add(difuzare);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();
        return difuzari;
    }

    public ArrayList<Bilet> loadBilete(){
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        String query = "SELECT * FROM bilet";

        ArrayList<Bilet> bilete = new ArrayList<Bilet>();

        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
//                biletId,clientId,difuzareId,loc,pret
                Integer biletId = resultSet.getInt(1);
                Integer clientId = resultSet.getInt(2);
                Integer difuzareId = resultSet.getInt(3);
                Integer loc = resultSet.getInt(4);
                Integer pret = resultSet.getInt(5);

                Bilet bilet = Bilet.newBilet(clientId, difuzareId, loc, pret);
                bilet.setBiletId(biletId);

                bilete.add(bilet);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();
        return bilete;
    }

    public Integer[] loadIds(){
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        String query = "SELECT * FROM iduri";

        Integer iduri[] = new Integer[5];
        for(Integer i = 0; i < 5; ++i){
            iduri[i] = 0;
        }

        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
//                userId,filmId,salaId,difuzareId,biletId
                Integer userId = resultSet.getInt(1);
                Integer filmId = resultSet.getInt(2);
                Integer salaId = resultSet.getInt(3);
                Integer difuzareId = resultSet.getInt(4);
                Integer biletId = resultSet.getInt(5);

                iduri[0] = userId;
                iduri[1] = filmId;
                iduri[2] = salaId;
                iduri[3] = difuzareId;
                iduri[4] = biletId;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();
        return iduri;
    }

    public void saveUsers(ArrayList<User> users){
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        String query = "DELETE FROM user";

        try(Statement statement = connection.createStatement()){
            statement.execute(query);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
//        userId,username,email,parola,age
        query = "INSERT INTO user (userId, username, email, parola, age) VALUES (?, ?, ?, ?, ?)";


        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for(User u : users){
                preparedStatement.setInt(1, u.getUserId());
                preparedStatement.setString(2, u.getUsername());
                preparedStatement.setString(3, u.getEmail());
                preparedStatement.setString(4, u.getParola());
                if(u instanceof Client){
                    Client client = (Client) u;
                    Integer age = client.getAge();
                    preparedStatement.setString(5, age.toString());
                }
                else if (u instanceof Admin){
                    preparedStatement.setString(5, "null");
                }
                preparedStatement.executeUpdate();
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();
    }

    public void saveFilme(LinkedList<Film> filme){
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        String query = "DELETE FROM film";

        try(Statement statement = connection.createStatement()){
            statement.execute(query);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
//        filmId,titlu,gen,rating,durata
        query = "INSERT INTO film (filmId, titlu, gen, rating, durata) VALUES (?, ?, ?, ?, ?)";


        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for(Film f : filme){
                preparedStatement.setInt(1, f.getFilmId());
                preparedStatement.setString(2, f.getTitlu());
                preparedStatement.setString(3, f.getGen());
                preparedStatement.setString(4, f.getRating());
                preparedStatement.setInt(5, f.getDurata());
                preparedStatement.executeUpdate();
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();
    }

    public void saveSali(ArrayList<Sala> sali){
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        String query = "DELETE FROM sala";

        try(Statement statement = connection.createStatement()){
            statement.execute(query);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
//        salaId,nume,nrLocuri
        query = "INSERT INTO sala (salaId, nume, nrLocuri) VALUES (?, ?, ?)";


        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for(Sala s : sali){
                preparedStatement.setInt(1, s.getSalaId());
                preparedStatement.setString(2, s.getNume());
                preparedStatement.setInt(3, s.getNrLocuri());
                preparedStatement.executeUpdate();
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();
    }

    public void saveDifuzari(ArrayList<Difuzare> difuzari){
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        String query = "DELETE FROM difuzare";

        try(Statement statement = connection.createStatement()){
            statement.execute(query);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
//        difuzareId,filmId,salaId,locuri,date
        query = "INSERT INTO difuzare (difuzareId, filmId, salaId, locuri, date) VALUES (?, ?, ?, ?, ?)";


        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for(Difuzare d : difuzari){
                preparedStatement.setInt(1, d.getDifuzareId());
                preparedStatement.setInt(2, d.getFilmId());
                preparedStatement.setInt(3, d.getSalaId());

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

                preparedStatement.setString(4, locuri);

                LocalDateTime date = d.getDate();
                String formatDate = date.format(formatter);

                preparedStatement.setString(5, formatDate);

                preparedStatement.executeUpdate();
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();
    }

    public void saveBilete(ArrayList<Bilet> bilete){
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        String query = "DELETE FROM bilet";

        try(Statement statement = connection.createStatement()){
            statement.execute(query);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
//        biletId,clientId,difuzareId,loc,pret
        query = "INSERT INTO bilet (biletId, clientId, difuzareId, loc, pret) VALUES (?, ?, ?, ?, ?)";


        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for(Bilet b : bilete){
                preparedStatement.setInt(1, b.getBiletId());
                preparedStatement.setInt(2, b.getClientId());
                preparedStatement.setInt(3, b.getDifuzareId());
                preparedStatement.setInt(4, b.getLoc());
                preparedStatement.setInt(5, b.getPret());

                preparedStatement.executeUpdate();
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();
    }

    public void saveIduri(Integer iduri[]){
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        String query = "DELETE FROM iduri";

        try(Statement statement = connection.createStatement()){
            statement.execute(query);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
//        userId,filmId,salaId,difuzareId,biletId
        query = "INSERT INTO iduri (userId, filmId, salaId, difuzareId, biletId) VALUES (?, ?, ?, ?, ?)";


        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, iduri[0]);
            preparedStatement.setInt(2, iduri[1]);
            preparedStatement.setInt(3, iduri[2]);
            preparedStatement.setInt(4, iduri[3]);
            preparedStatement.setInt(5, iduri[4]);

            preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();
    }

    public void saveAuditing(String action, LocalDateTime timeStamp){
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
//        name_of_action,timestamp
        String query = "INSERT INTO auditing (name_of_action,timestamp) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, action);
            String formatedDate = timeStamp.format(formatter);
            preparedStatement.setString(2, formatedDate);

            preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        DatabaseConfiguration.closeDatabaseConnection();
    }

}
