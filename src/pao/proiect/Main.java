package pao.proiect;

import pao.proiect.CSV.CSVReader;
import pao.proiect.obiecte.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        CinemaData cinemaData = CinemaData.getInstance();
        cinemaData.load();
        CinemaServices cinemaServices = CinemaServices.getInstance();
        cinemaServices.meniu();
    }
}
