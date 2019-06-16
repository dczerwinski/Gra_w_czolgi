import java.awt.*;

/**
 * Klasa Shoot odpowiada za animację lecącego pocisku
 */
public class Shoot {
    private  Image image;
    private char kierunek;
    private int bulletX,bulletY,max;

    /**
     * do konstruktora podaję koordynaty x i y obiektu który wystrzelił pocisk oraz kierunek jego zwrotu
     * @param kierunek
     * @param x
     * @param y
     */
    public Shoot(char kierunek, int x, int y){
        this.image = Toolkit.getDefaultToolkit().getImage("bullet.png");
        this.kierunek = kierunek;
        if(kierunek == 'S' || kierunek == 'W'){
            this.bulletX = x+20;
            this.bulletY = y;
        }
        else if(kierunek == 'A'){
            this.bulletX = x-40;
            this.bulletY = y+20;
        }
        else{
            this.bulletX = x;
            this.bulletY = y+20;
        }
        max = 0;
    }

    /**
     * zwraca koordynate X pocisku
     * @return
     */
    public int getBulletX(){
        return bulletX;
    }

    /**
     * zwraca koordynate Y pocisku
     * @return
     */
    public  int getBulletY(){
        return  bulletY;
    }

    /**
     * funkcja zwraca obrazek pocisku
     * @return
     */
    public Image getImage() {
        return image;
    }

    /**
     * funkcja odpowiada za poruszanie poruszanie sie pocisku, zwraca true jezeli pocisk dalej leci lub false jezeli już osiąngął maxymalną długość lotu
     * @return
     */
    public boolean tura(){
        switch (kierunek){
            case 'W':
                bulletY--;
                break;
            case 'S':
                bulletY++;
                break;
            case 'A':
                bulletX--;
                break;
            case 'D':
                bulletX++;
                break;
        }
        max++;
        if(max>300)return false;
        else return true;
    }

}
