package pao.proiect;

import pao.proiect.obiecte.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        LocalDateTime date = LocalDateTime.now();
        System.out.println(date);

        Film film = new Film("aaaa", "Actiune", "R");
        Sala sala = new Sala("Gigi", 10);

        Difuzare difuzare = new Difuzare(film, sala, date);
        ArrayList<Integer> ar = difuzare.getLocuriLibere();
        for(Integer x : ar){
            System.out.print(x);
            System.out.print(' ');
        }
        System.out.println();
        Sala sala2 = new Sala("Gigi", 5);
        difuzare.setSala(sala2);
        ar = difuzare.getLocuriLibere();
        for(Integer x : ar){
            System.out.print(x);
            System.out.print(' ');
        }
    }
}
