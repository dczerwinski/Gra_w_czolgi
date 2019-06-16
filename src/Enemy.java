import java.awt.*;

/**
 * Klasa enemy odpowiada za stworzenie obiektu wrogiego czołgu
 */
public class Enemy {
    private Image image;
    private int tankX,tankY;
    private char kierunek;

    /**
     * W konstruktorze losuję w którą stronę ma być czołg odwrucony oraz losuję jego położenie
     */
    public Enemy(){
        int temp = (int) (Math.random() * 1000 + 0);
        if(temp<250){
            this.image = Toolkit.getDefaultToolkit().getImage("enemyW.png");
            kierunek = 'W';
        }
        if(temp>=250 && temp <500){
            this.image = Toolkit.getDefaultToolkit().getImage("enemyS.png");
            kierunek = 'S';
        }
        if(temp>=500 && temp<=750){
            this.image = Toolkit.getDefaultToolkit().getImage("enemyD.png");
            kierunek='D';
        }
        if(temp>750){
            this.image = Toolkit.getDefaultToolkit().getImage("enemyA.png");
            kierunek='A';
        }
        tankX = (int) (Math.random() * 850 + 0);
        tankY = (int) (Math.random() * 800 + 0);
    }

    /**
     * zwraca koordynaty X wroga
     * @return
     */
    public int getTankX(){
        return tankX;
    }

    /**
     * zwraca koordynaty Y wroga
     * @return
     */
    public int getTankY(){
        return tankY;
    }

    /**
     * zwraca Image wroga
     * @return
     */
    public Image getImage(){
        return image;
    }

    /**
     * zwraca kierunek w jaki skierowany jest wrogi czołg
     * @return
     */
    public char getKierunek() {
        return kierunek;
    }
}
