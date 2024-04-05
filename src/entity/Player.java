package entity;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gamePanel;
    KeyHandler keyHandler;
    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player2/player_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player2/player_up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player2/player_down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player2/player_down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player2/player_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player2/player_stillleft.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player2/player_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player2/player_stillright.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {
        if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
            if(keyHandler.upPressed) {
                direction = "up";
                y -= speed;
            }
            else if(keyHandler.downPressed) {
                direction = "down";
                y += speed;
            }
            else if(keyHandler.leftPressed) {
                direction = "left";
                x -= speed;
            }
            else if(keyHandler.rightPressed) {
                direction = "right";
                x += speed;
            }
            spriteCounter++;
            if(spriteCounter >= 12) {
                if(spriteNumber == 1) spriteNumber = 2;
                else spriteNumber = 1;
                spriteCounter = 0;
            }
        }
    }
    public void drawPlayerImage(Graphics g2) {
        // g2.setColor(Color.red);
        // g2.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNumber == 1) {
                    image = up1;
                } else if (spriteNumber == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNumber == 1) {
                    image = down1;
                } else if (spriteNumber == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                    image = left1;
                } else if (spriteNumber == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNumber == 1) {
                    image = right1;
                }
                else if(spriteNumber == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
