package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;

public class Player extends Entity{

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

   // public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        attackArea.width=36;
        attackArea.height=36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY= gp.tileSize * 23;
        speed = 4;
        direction = "down";

        //PLAYER STATUS
        maxLife = 6;
        life = maxLife;
    }
    public void getPlayerImage(){
        up1 = setup("/player/up1",gp.tileSize,gp.tileSize);
        up2 = setup("/player/up2",gp.tileSize,gp.tileSize);
        down1 = setup("/player/down1",gp.tileSize,gp.tileSize);
        down2 = setup("/player/down2",gp.tileSize,gp.tileSize);
        left1 = setup("/player/left1",gp.tileSize,gp.tileSize);
        left2 = setup("/player/left2",gp.tileSize,gp.tileSize);
        right1 = setup("/player/right1",gp.tileSize,gp.tileSize);
        right2 = setup("/player/right2",gp.tileSize,gp.tileSize);
    }
    public void getPlayerAttackImage(){
        attackUp1 = setup("/player/attack-up1",gp.tileSize,gp.tileSize*2);
        attackUp2  = setup("/player/attack-up2",gp.tileSize,gp.tileSize*2);
        attackDown1  = setup("/player/attack-down1",gp.tileSize,gp.tileSize*2);
        attackDown2  = setup("/player/attack-down2",gp.tileSize,gp.tileSize*2);
        attackLeft1  = setup("/player/attack-left1",gp.tileSize*2,gp.tileSize);
        attackLeft2  = setup("/player/attack-left2",gp.tileSize*2,gp.tileSize);
        attackRight1  = setup("/player/attack-right1",gp.tileSize*2,gp.tileSize);
        attackRight2  = setup("/player/attack-right2",gp.tileSize*2,gp.tileSize);
    }
    public void update() {

        if(attacking ==true){
            attacking();
        }
        else if (keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {

            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }

            //Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //Check object collision
            int objIndex = gp.cChecker.checkObject(this,true);
            pickUpObject(objIndex);

            //Check npc collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //check monster collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            //Check event
            gp.eHandler.checkEvent();



            //if collision is false, player can move
            if (collisionOn == false && keyH.enterPressed == false) {
                switch (direction) {
                    case "up" -> {worldY -= speed;}
                    case "down" -> {worldY += speed;}
                    case "left" -> {worldX -= speed;}
                    case "right" -> {worldX += speed;}
                }
            }

            gp.keyH.enterPressed = false;

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1)
                    spriteNum = 2;
                else if (spriteNum == 2)
                    spriteNum = 1;
                spriteCounter = 0;
            }

        }
        /*else{
            standCounter++;
            if(standCounter == 20){
                spriteNum = 1;
                standCounter = 0;
            }
        }*/

        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    public void attacking(){

        spriteCounter++;

        if(spriteCounter <=5){
            spriteNum = 1;
        }
        if(spriteCounter > 5 && spriteCounter <=25){
            spriteNum = 2;

            //SAVE THE CURRENT worldX, worldY ,solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //Adjust player's worldX/Y for the attackArea
            switch(direction){
                case "up"->{ worldY -= attackArea.height;}
                case "down"->{ worldY += attackArea.height;}
                case "left"->{worldX -= attackArea.width;}
                case "right"->{worldX += attackArea.width;}
            }
            //atackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            //check monster collision with the updated worldX,worldY and solidArea
            int monsterIndex = gp.cChecker.checkEntity(this,gp.monster);
            damageMonster(monsterIndex);

            //after checking collision restore the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        if(spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }

    }
    public void pickUpObject(int i){
        if(i!=999){


/*
            String objectName = gp.obj[i].name;

            switch (objectName){
                case "Key"->{
                    gp.playSE(2);
                    hasKey++;
                    gp.obj[i]=null;
                    gp.ui.showMessage("Got a key!");
                }
                case "Door"->{
                    if(hasKey!=0){
                        gp.playSE(3);
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage("You opened door!");
                    }
                    else{
                        gp.ui.showMessage("You need a key!");
                    }
                }
                case "Boots"->{
                    gp.playSE(1);
                    speed+=1;
                    gp.obj[i]=null;
                    gp.ui.showMessage("Speed up !");
                }
                case "Chest"->{
                    gp.playSE(3);
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                }
            }*/
        }
    }
    public void interactNPC(int i){
        if(gp.keyH.enterPressed == true) {
            if(i!=999){
                if(gp.keyH.enterPressed){
                    gp.gameState = gp.DIALOGUE_STATE;
                    gp.npc[i].speak();
                }
            }
            else{
                if(gp.keyH.enterPressed == true){
                    attacking = true;
                }
            }
        }

    }

    public void contactMonster(int i){
        if(i != 999){
            if(invincible == false){
                life -= 1;
                invincible= true;
            }

        }
    }
    public void damageMonster(int i){
        if(i != 999){
            if(gp.monster[i].invincible == false){

                gp.monster[i].life -= 1;
                gp.monster[i].invincible = true;

                if(gp.monster[i].life <= 0){
                    gp.monster[i] = null;
                }

            }
        } else {

        }
    }
    public void draw (Graphics2D g2){

            BufferedImage image = null;
            int tempScreenX = screenX;
            int tempScreenY = screenY;

            switch (direction) {

                case "up" -> {
                    if(attacking == false){
                        if (spriteNum == 1) image = up1;
                        if (spriteNum == 2) image = up2;
                    }
                    if(attacking == true){
                        tempScreenY = screenY - gp.tileSize;
                        if (spriteNum == 1) image = attackUp1;
                        if (spriteNum == 2) image = attackUp2;
                    }
                }
                case "down" -> {
                    if(attacking == false){
                        if (spriteNum == 1) image = down1;
                        if (spriteNum == 2) image = down2;
                    }
                    if(attacking == true){
                        if (spriteNum == 1) image = attackDown1;
                        if (spriteNum == 2) image = attackDown2;
                    }
                }
                case "left" -> {
                    if(attacking == false){
                        if (spriteNum == 1) image = left1;
                        if (spriteNum == 2) image = left2;
                    }
                    if(attacking == true){
                        tempScreenX = screenX - gp.tileSize;
                        if (spriteNum == 1) image = attackLeft1;
                        if (spriteNum == 2) image = attackLeft2;
                    }
                }
                case "right" -> {
                    if(attacking == false){
                        if (spriteNum == 1) image = right1;
                        if (spriteNum == 2) image = right2;
                    }
                    if(attacking == true) {
                        if (spriteNum == 1) image = attackRight1;
                        if (spriteNum == 2) image = attackRight2;
                    }
                }
            }

            if(invincible == true){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            }

            g2.drawImage(image, tempScreenX, tempScreenY,null);

            //reset alpha
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.f));

            //g2.setFont(new Font("Arial", Font.PLAIN, 26) );
            //g2.setColor(Color.white);
            //g2.drawString("Invincible: " + invincibleCounter, 10, 400);
    }


}
