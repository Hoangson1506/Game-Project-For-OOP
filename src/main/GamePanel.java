package main;
import entity.Player;
import tile.TileManeger;

import javax.swing.*;
import java.awt.*;
public class GamePanel extends JPanel implements Runnable{
    // screen settings
    final int originalTileSize = 16; // 16*16 tile
    final int scale = 3; // scale the origin 3 times
    public int tileSize = originalTileSize * scale;
    final int maxScreenCol = 25;
    final byte maxScreenRow = 15;
    public int screenWidth = maxScreenCol * tileSize;
    public int screenHeight = maxScreenRow * tileSize;
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    public Player player = new Player(this, keyHandler);
    // world map settings
    public final int worldRow = 37;
    public final int worldColumn = 60;
    public final int worldWidth = worldColumn * tileSize;
    public final int worldHeight = worldRow * tileSize;
    TileManeger tileManeger = new TileManeger(this);
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
        double FPS = 120;
        double drawInterval = 1000000000/FPS;
        long lastTime = System.nanoTime();
        double delta = 0;
        long currentTime;
        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/ drawInterval;
            lastTime = System.nanoTime();
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }
    public void update() {
        player.update();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics g2 = (Graphics2D)g;
        tileManeger.drawMap(g2);
        player.drawPlayerImage(g2);
        g2.dispose();
    }
}
