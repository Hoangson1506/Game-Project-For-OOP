package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gamePanel;
    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public void checkTile(Entity entity) {
        int entityLeftX = entity.x + entity.solidArea.x;
        int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftX/gamePanel.tileSize;
        int entityRightCol = entityRightX/gamePanel.tileSize;
        int entityTopRow = entityTopY/gamePanel.tileSize;
        int entityBottomRow = entityBottomY/gamePanel.tileSize;

        int tileNum1, tileNum2;
        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopY - entity.speed)/gamePanel.tileSize;
                tileNum1 = gamePanel.tileManeger.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = gamePanel.tileManeger.mapTileNum[entityTopRow][entityRightCol];
                if(gamePanel.tileManeger.tiles[tileNum1].collision == true
                    || gamePanel.tileManeger.tiles[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomY + entity.speed)/gamePanel.tileSize;
                tileNum1 = gamePanel.tileManeger.mapTileNum[entityBottomRow][entityLeftCol];
                tileNum2 = gamePanel.tileManeger.mapTileNum[entityBottomRow][entityRightCol];
                if(gamePanel.tileManeger.tiles[tileNum1].collision == true
                        || gamePanel.tileManeger.tiles[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.speed)/gamePanel.tileSize;
                tileNum1 = gamePanel.tileManeger.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = gamePanel.tileManeger.mapTileNum[entityBottomRow][entityLeftCol];
                if(gamePanel.tileManeger.tiles[tileNum1].collision == true
                        || gamePanel.tileManeger.tiles[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.speed)/gamePanel.tileSize;
                tileNum1 = gamePanel.tileManeger.mapTileNum[entityTopRow][entityRightCol];
                tileNum2 = gamePanel.tileManeger.mapTileNum[entityBottomRow][entityRightCol];
                if(gamePanel.tileManeger.tiles[tileNum1].collision == true
                        || gamePanel.tileManeger.tiles[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
