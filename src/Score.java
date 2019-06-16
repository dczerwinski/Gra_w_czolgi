import java.awt.*;

/**
 * Klasa Score odpowiada za wyświetlenie odpowiedniego napisu wyników
 */
public class Score {
    private Image napis;
    private Image w_1,w_2;
    private String w1_s,w2_s;

    /**
     * w konstruktorze przypisuję odpowiednie obrazku do zmiennych
     */
    public Score(){
        w1_s = "liczba0.png";
        w2_s = "liczba0.png";
        napis = Toolkit.getDefaultToolkit().getImage("napis.png");
        w_1 = Toolkit.getDefaultToolkit().getImage(w1_s);
        w_2 = Toolkit.getDefaultToolkit().getImage(w2_s);
    }

    /**
     * metoda count zapisuje odpowiednie cyfry (wyniku) według podanego wyniku
     * @param wynik
     */
    private void Count(int wynik){
        int x = wynik/10;
        w1_s = "liczba"+x+".png";
        x = wynik%10;
        w2_s = "liczba"+x+".png";
    }

    /**
     * zwraca obrazek napisu
     * @return
     */
    public Image getNapis() {
        return napis;
    }

    /**
     * zwraca obrazek pierwszej cyfry napisu według podanego wyniku
     * @param wynik
     * @return
     */
    public Image getW_1(int wynik) {
        Count(wynik);
        w_1 = Toolkit.getDefaultToolkit().getImage(w1_s);
        return w_1;
    }

    /**
     * zwraca obrazek drugiej cyfry napisu według podanego wyniku
     * @param wynik
     * @return
     */
    public Image getW_2(int wynik) {
        w_2 = Toolkit.getDefaultToolkit().getImage(w2_s);
        return w_2;
    }
}
