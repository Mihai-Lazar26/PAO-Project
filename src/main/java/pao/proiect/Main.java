package pao.proiect;

import pao.proiect.JDBC.JDBCHandler;
import pao.proiect.config.DatabaseConfiguration;
import pao.proiect.obiecte.*;

import java.sql.Connection;


public class Main {

    public static void main(String[] args) {
        CinemaData cinemaData = CinemaData.getInstance();
        cinemaData.load();
        CinemaServices cinemaServices = CinemaServices.getInstance();
        cinemaServices.meniu();
    }
}
