import java.awt.*;

/**
 *Klasa odpowiadajaća za wyświetlenie animacji wybuchu podczas niszczenia wroga
*/
public class Destroy {
    private Image image;
    private int tankX,tankY,count,time;

    /**
     * Do konstruktora podaje koordynaty czołgu który został zniszczony
     * @param tankX
     * @param tankY
     */
    public Destroy(int tankX, int tankY){
        this.image = Toolkit.getDefaultToolkit().getImage("boom1.png");
        this.tankX = tankX;
        this.tankY = tankY;
        this.count = 0;
        this.time = 50;
    }

    /**
     * funkcja która zwraca odpowedni obrazek wybuchu w zależności od tego ile czasu minelo (ile razy zmienna count została inkrementowana
     * @return
     */
    public Image getImage(){
        if(count<=time)this.image = Toolkit.getDefaultToolkit().getImage("boom1.png");
        else if(count>time && count<=(2*time))this.image = Toolkit.getDefaultToolkit().getImage("boom2.png");
        else if(count>(2*time) && count<=(3*time))this.image = Toolkit.getDefaultToolkit().getImage("boom3.png");
        else if(count>(3*time) && count<=(4*time))this.image = Toolkit.getDefaultToolkit().getImage("boom4.png");
        else this.image = Toolkit.getDefaultToolkit().getImage("boom5.png");
        count++;
        return image;
    }

    /**
     * funkcja służy do sprawdzenia statusu wybuchu, jeżeli zwraca false oznacza to żę wybuch sie skończył w przeciwnym wypadku zwarac a true
     * @return
     */
    public boolean checkStatus(){
        if(count>5*time)return false;
        return true;
    }

    /**
     * funkcja zwraca parametr X wybuchu
     * @return
     */
    public int getX(){
        return tankX;
    }

    /**
     * funkcja zwraca parametr Y wybuchu
     * @return
     */
    public int getY(){
        return tankY;
    }
}
