import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * główna funkcja programu odpowiada za logikę oraz wyświetlanie wszystkiego
 */
public class Panel extends JPanel implements KeyListener, ActionListener {
    private Timer timer;
    private int delay = 1;
    private int tankX,tankY,tankW,tankH,bulletX,bulletY,predkosc,odl;
    private Image tank_img,map_img;
    private char kierunek;
    private boolean strzal;
    private java.util.List<Shoot> lista_strzalow = new ArrayList<Shoot>();
    private java.util.List<Enemy> lista_wrogow = new ArrayList<Enemy>();
    private java.util.List<Destroy> lista_boom = new ArrayList<Destroy>();
    private Score score;
    private int wynik;
    private int l_zyc;
    private Image hearts[];
    private java.util.List<Image> lista_przeszkod = new ArrayList<Image>();
    private java.util.List<Shoot> enemy_shoot = new ArrayList<Shoot>();
    private int licznik,max_l_wrogow;
    private Image end;

    /**
     * w konstruktorze odpowiednio przypisuje wartości zmiennnym
     */
    public Panel(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay,this);
        timer.start();
        tankY=500;
        tankX=100;
        tankW=60;
        tankH=60;
        tank_img = Toolkit.getDefaultToolkit().getImage("tankW.png");
        map_img = Toolkit.getDefaultToolkit().getImage("map.png");
        kierunek = 'W';
        strzal = false;
        bulletX = tankX;
        bulletY = tankY;
        predkosc = 10;
        odl=0;
        wynik = 0;
        score = new Score();
        l_zyc = 3;
        hearts = new Image[3];
        hearts[0] = Toolkit.getDefaultToolkit().getImage("heart.png");
        hearts[1] = Toolkit.getDefaultToolkit().getImage("heart.png");
        hearts[2] = Toolkit.getDefaultToolkit().getImage("heart.png");
        licznik=0;
        end = Toolkit.getDefaultToolkit().getImage("end.png");
        max_l_wrogow = 5;
    }

    /**
     * metoda rysuję mape gry oraz obiekty
     * @param g
     */
    public void paint(Graphics g){

        g.setColor(Color.GRAY);
        g.fillRect(00,0,1500,900);

        Graphics2D g2d=(Graphics2D)g;
        g2d.drawImage(map_img, 0, 0, this);

        for(int i=0; i<lista_strzalow.size(); i++){
            boolean temp=false;
            g2d.drawImage(lista_strzalow.get(i).getImage(),lista_strzalow.get(i).getBulletX(),lista_strzalow.get(i).getBulletY(),20,20,this);
            for(int j=0; j<lista_wrogow.size(); j++){
                if(kolizja(lista_strzalow.get(i).getBulletX(),lista_strzalow.get(i).getBulletY(),lista_wrogow.get(j).getTankX(),lista_wrogow.get(j).getTankY()) == true){
                    lista_boom.add(new Destroy(lista_wrogow.get(j).getTankX(),lista_wrogow.get(j).getTankY()));
                    lista_wrogow.remove(j);
                    lista_strzalow.remove(i);
                    j=lista_wrogow.size()+10;
                    temp = true;
                    wynik++;
                    max_l_wrogow = max_l_wrogow + 3;
                }
            }
            if(temp == false){
                if(lista_strzalow.get(i).tura() == false)lista_strzalow.remove(i);
            }
        }

        for(int i=0; i<enemy_shoot.size(); i++){
            boolean temp = true;
            g2d.drawImage(enemy_shoot.get(i).getImage(),enemy_shoot.get(i).getBulletX(),enemy_shoot.get(i).getBulletY(),20,20,this);
            if(kolizja(enemy_shoot.get(i).getBulletX(),enemy_shoot.get(i).getBulletY(),tankX,tankY) == true){
                l_zyc--;
                enemy_shoot.remove(i);
                temp = false;
            }
            if(temp == true){
                if(enemy_shoot.get(i).tura() == false)enemy_shoot.remove(i);
            }
        }

        for(int i=0; i<lista_boom.size(); i++){
            g2d.drawImage(lista_boom.get(i).getImage(),lista_boom.get(i).getX(),lista_boom.get(i).getY(),this);
            if(lista_boom.get(i).checkStatus() == false){
                lista_boom.remove(i);
                break;
            }
        }

        for(int i=0; i<lista_wrogow.size(); i++){
            for(int j=0; j<lista_wrogow.size(); j++){
                if(i!=j){
                    if(kolizja(lista_wrogow.get(i).getTankX(),lista_wrogow.get(i).getTankY(),lista_wrogow.get(j).getTankX(),lista_wrogow.get(j).getTankY())){
                        lista_wrogow.remove(j);
                        break;
                    }
                }
            }
            g2d.drawImage(lista_wrogow.get(i).getImage(),lista_wrogow.get(i).getTankX(),lista_wrogow.get(i).getTankY(),60,60,this);
        }

        g2d.drawImage(tank_img, tankX, tankY, tankW, tankH, this);

        g2d.drawImage(score.getNapis(),0,0,this);
        g2d.drawImage(score.getW_1(wynik),score.getNapis().getWidth(this),0,this);
        g2d.drawImage(score.getW_2(wynik),score.getNapis().getWidth(this) + 48,0,this);
        for(int i=0; i<3;i++){
            g2d.drawImage(hearts[i],800+(i*30),0,this);
        }

        if(l_zyc <= 0){
            g2d.drawImage(end,0,0,this);
        }
    }

    /**
     * metoda wywołuję się co sekunę według timera
     * @param e
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        licznik++;
        if((Math.random() * 1000 + 0) < 5 && lista_wrogow.size()<max_l_wrogow){
            lista_wrogow.add(new Enemy());
            if(kolizja(lista_wrogow.get(lista_wrogow.size()-1).getTankX(),lista_wrogow.get(lista_wrogow.size()-1).getTankY(),tankX,tankY)){
                lista_wrogow.remove(lista_wrogow.size()-1);
            }
        }

        if(l_zyc == 2)hearts[2] = Toolkit.getDefaultToolkit().getImage("heart2.png");
        if(l_zyc == 1)hearts[1] = Toolkit.getDefaultToolkit().getImage("heart2.png");
        if(l_zyc == 0)hearts[0] = Toolkit.getDefaultToolkit().getImage("heart2.png");

        if(licznik>200){
            for(int i=0; i<lista_wrogow.size(); i++){
                enemy_shoot.add(new Shoot(lista_wrogow.get(i).getKierunek(),lista_wrogow.get(i).getTankX(),lista_wrogow.get(i).getTankY()));
            }
            licznik=0;
        }


        timer.start();
        repaint();
    }

    /**
     * funkcja odpowiada za wciśnięcie klawisza
     * @param keyEvent
     */
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    /**
     * funkcja odpowiada za naciśniecie klawisza
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_S:
                kierunek = 'S';
                tank_img = Toolkit.getDefaultToolkit().getImage("tankS.png");
                if(!kolizja(tankX,tankY+predkosc)){
                    tankY+=predkosc;
                }
                break;
            case KeyEvent.VK_W:
                kierunek = 'W';
                tank_img = Toolkit.getDefaultToolkit().getImage("tankW.png");
                if(!kolizja(tankX,tankY-predkosc)){
                    tankY-=predkosc;
                }
                break;
            case KeyEvent.VK_A:
                kierunek = 'A';
                tank_img = Toolkit.getDefaultToolkit().getImage("tankA.png");
                if(!kolizja(tankX-predkosc,tankY)){
                    tankX-=predkosc;
                }
                break;
            case KeyEvent.VK_D:
                kierunek = 'D';
                tank_img = Toolkit.getDefaultToolkit().getImage("tankD.png");
                if(!kolizja(tankX+predkosc,tankY)){
                    tankX+=predkosc;
                }
                break;
            case KeyEvent.VK_F:
                if(lista_strzalow.size()<1)
                    lista_strzalow.add(new Shoot(kierunek,tankX,tankY));
                break;
        }
        if(tankX > 870)tankX = tankX - predkosc;
        if(tankY > 820)tankY = tankY - predkosc;
        if(tankX<-10)tankX +=predkosc;
        if(tankY<-10)tankY +=predkosc;

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    /**
     * metoda zwraca true jezeli występuje kolizja miedzy podanymi parami koordynatów lub false jezeli nie
     * @param bx
     * @param by
     * @param ex
     * @param ey
     * @return
     */
    boolean kolizja(int bx,int by, int ex, int ey){
        int odl_m = 50;
        if((bx> ex - odl_m) && (bx< ex + odl_m) && (by> ey - odl_m) && (by< ey + odl_m))return  true;
        else return false;
    }

    /**
     * przeciążona funkcja kolizji zwraca true jezeli występuje kolizja pomiedzy podanymi koordynatami a jakimś obiektem typu Enemy
     * @param x
     * @param y
     * @return
     */
    boolean kolizja(int x ,int y){
        int odl_m = 30;
        for(int i=0; i<lista_wrogow.size(); i++){
            if((x> lista_wrogow.get(i).getTankX() - odl_m) && (x< lista_wrogow.get(i).getTankX() + odl_m) &&
                    (y> lista_wrogow.get(i).getTankY() - odl_m) && (y< lista_wrogow.get(i).getTankY() + odl_m))return  true;
        }
        return false;
    }
}
