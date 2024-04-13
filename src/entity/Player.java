package entity;
import main.CollisionChecker;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gamePanel;
    KeyHandler keyHandler;
    public final int screenX;
    public final int screenY;
    CollisionChecker collisionChecker;
    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        collisionChecker = new CollisionChecker(gamePanel);
        screenX = (gamePanel.screenWidth - gamePanel.tileSize) / 2;
        screenY = (gamePanel.screenHeight - gamePanel.tileSize) / 2;
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        x = gamePanel.tileSize * 11;
        y = gamePanel.tileSize * 6;
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
            }
            else if(keyHandler.downPressed) {
                direction = "down";
            }
            else if(keyHandler.leftPressed) {
                direction = "left";
            }
            else if(keyHandler.rightPressed) {
                direction = "right";
            }
            collisionOn = false;
            collisionChecker.checkTile(this);
            switch (direction) {
                case "up":
                    if(collisionOn == false) {
                        y -= speed;
                    }
                    break;
                case "down":
                    if(collisionOn == false) {
                        y += speed;
                    }
                    break;
                case "left":
                    if(collisionOn == false) {
                        x -= speed;
                    }
                    break;
                case "right":
                    if(collisionOn == false) {
                        x += speed;
                    }
                    break;
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
        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
