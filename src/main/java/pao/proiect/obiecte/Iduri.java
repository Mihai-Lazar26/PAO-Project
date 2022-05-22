package pao.proiect.obiecte;

public class Iduri {
    private static int userId = 0;
    private static int filmId = 0;
    private static int salaId = 0;
    private static int difuzareId = 0;
    private static int biletId = 0;

    public static int getUserId() {
        userId += 1;
        return userId;
    }

    public static int getFilmId() {
        filmId += 1;
        return filmId;
    }

    public static int getSalaId() {
        salaId += 1;
        return salaId;
    }

    public static int getDifuzareId() {
        difuzareId += 1;
        return difuzareId;
    }

    public static int getBiletId() {
        biletId += 1;
        return biletId;
    }

    public static void setUserId(int userId) {
        Iduri.userId = userId;
    }

    public static void setFilmId(int filmId) {
        Iduri.filmId = filmId;
    }

    public static void setSalaId(int salaId) {
        Iduri.salaId = salaId;
    }

    public static void setDifuzareId(int difuzareId) {
        Iduri.difuzareId = difuzareId;
    }

    public static void setBiletId(int biletId) {
        Iduri.biletId = biletId;
    }
}
