import java.awt.*;
import javax.swing.JFrame;

/**
 * klasa Main to główna klasa programu  oraz metoda main odpowiada za jego uruchomienie oraz wyświetlenie okna
 */

public class Main {

    public static void main(String[] args) {
        JFrame obj = new JFrame();
        Panel panel = new Panel();


        obj.setBounds(0,0,930,900);
        obj.setBackground(Color.DARK_GRAY);
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        obj.add(panel);



    }
}
