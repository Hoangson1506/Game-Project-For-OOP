import javax.swing.*;
import java.awt.*;
public class GamePanel extends JPanel{
    // screen settings
    final byte originalTileSize = 16; // 16*16 tile
    final byte scale = 3; // scale the origin 3 times
    final int tileSize = originalTileSize * scale;
    final byte maxScreenCol = 36;
    final byte maxScreenRow = 25;
    final int screenWidth = maxScreenCol * tileSize;
    final int screenHeight = maxScreenRow * tileSize;
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.PINK);
        this.setDoubleBuffered(true);
    }
}
