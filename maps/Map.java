import javax.swing.*;
import java.awt.*;

public class Map extends JPanel {
    final private int ROWS = 25;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < ROWS; j++) {
                g.drawRect(25 * i, 25 * j, 25, 25);
            }
        }
    }
}
