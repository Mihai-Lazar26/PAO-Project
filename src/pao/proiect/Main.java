package pao.proiect;

import pao.proiect.obiecte.CinemaServices;

public class Main {

    public static void main(String[] args) {
        CinemaServices cinemaServices = CinemaServices.getInstance();
        cinemaServices.meniu();
    }
}
