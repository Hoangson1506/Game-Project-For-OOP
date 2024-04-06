package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManeger {
    GamePanel gamePanel;
    Tile[] tiles;
    int mapTileNum[][];
    public TileManeger(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[15];
        mapTileNum = new int[gamePanel.worldRow][gamePanel.worldColumn];
        getTileImage();
        loadMap("/maps/worldmap.txt");
    }
    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int row = 0;
            int col = 0;
            while(row < gamePanel.worldRow && col < gamePanel.worldColumn) {
                String line = br.readLine();
                while(col < gamePanel.worldColumn) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[row][col] = num;
                    col++;
                }
                if(col == gamePanel.worldColumn) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }
    public void getTileImage() {
        try{
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water01.png"));
            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }
    public void drawMap(Graphics g2) {
        int col = 0;
        int row = 0;
        while(row < gamePanel.worldRow) {
            while(col < gamePanel.worldColumn) {
                int tileNum = mapTileNum[row][col];
                int x = col * gamePanel.tileSize;
                int y = row * gamePanel.tileSize;
                int screenX = x - gamePanel.player.x + gamePanel.player.screenX;
                int screenY = y - gamePanel.player.y + gamePanel.player.screenY;
                g2.drawImage(tiles[tileNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
                col++;
            }
            col = 0;
            row++;
        }
    }
}
