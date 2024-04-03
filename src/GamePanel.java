import javax.swing.*;
import java.awt.*;
public class GamePanel extends JPanel implements Runnable{
    // screen settings
    final byte originalTileSize = 16; // 16*16 tile
    final byte scale = 3; // scale the origin 3 times
    final int tileSize = originalTileSize * scale;
    final byte maxScreenCol = 36;
    final byte maxScreenRow = 25;
    final int screenWidth = maxScreenCol * tileSize;
    final int screenHeight = maxScreenRow * tileSize;
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 3;
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.PINK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        while(gameThread != null) {
            update();
            repaint();
        }
    }
    public void update() {
        if(keyHandler.upPressed) {
            playerY -= playerSpeed;
        }
        else if(keyHandler.downPressed) {
            playerY += playerSpeed;
        }
        else if(keyHandler.leftPressed) {
            playerX -= playerSpeed;
        }
        else if(keyHandler.rightPressed) {
            playerX += playerSpeed;
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics g2 = (Graphics2D)g;
        g2.setColor(Color.red);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
}
