package pao.proiect;

import pao.proiect.obiecte.*;

public class Main {

    public static void main(String[] args) {
        CinemaData cinemaData = CinemaData.getInstance();
        cinemaData.load();
        CinemaServices cinemaServices = CinemaServices.getInstance();
        cinemaServices.meniu();
    }
}
