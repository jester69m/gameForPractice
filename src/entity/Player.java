package entity;

import main.GamePanel;
import main.KeyHandler;
import object.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity{

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    int standCounter = 0;
    public boolean attackCanceled = false;


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



        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
        setDefaultPositions();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY= gp.tileSize * 23;
        speed = 3;
        direction = "down";

        //PLAYER STATUS
        level = 1;
        maxLife = 6;
        life = maxLife;
        maxMana = 4;
        mana = maxMana;
        ammo = 10;
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 100;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        projectile = new OBJ_Fireball(gp);

        //projectile = new OBJ_Rock(gp);
        attack = getAttack();
        defense = getDefense();
    }
    public void setDefaultPositions(){
        worldX = gp.tileSize * 23;
        worldY= gp.tileSize * 23;
//        worldX= gp.tileSize * 12;
//        worldY= gp.tileSize * 13;
//        gp.currentMap = 1;
        direction = "down";
    }
    public void restoreLifeAndMana(){

        life = maxLife;
        mana = maxMana;
        invincible = false;
    }
    public void setItems(){
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Key(gp));
    }
    public int getAttack(){
        attackArea = currentWeapon.attackArea;
        return attack = strength * currentWeapon.attackValue;
    }
    public int getDefense(){
        return defense = dexterity * currentShield.defenseValue;
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
        if(currentWeapon.type == type_sword){

            attackUp1 = setup("/player/attack-up1",gp.tileSize,gp.tileSize*2);
            attackUp2  = setup("/player/attack-up2",gp.tileSize,gp.tileSize*2);
            attackDown1  = setup("/player/attack-down1",gp.tileSize,gp.tileSize*2);
            attackDown2  = setup("/player/attack-down2",gp.tileSize,gp.tileSize*2);
            attackLeft1  = setup("/player/attack-left1",gp.tileSize*2,gp.tileSize);
            attackLeft2  = setup("/player/attack-left2",gp.tileSize*2,gp.tileSize);
            attackRight1  = setup("/player/attack-right1",gp.tileSize*2,gp.tileSize);
            attackRight2  = setup("/player/attack-right2",gp.tileSize*2,gp.tileSize);
        }
        if(currentWeapon.type == type_axe){
            attackUp1 = setup("/player/boy_axe_up_1",gp.tileSize,gp.tileSize*2);
            attackUp2  = setup("/player/boy_axe_up_2",gp.tileSize,gp.tileSize*2);
            attackDown1  = setup("/player/boy_axe_down_1",gp.tileSize,gp.tileSize*2);
            attackDown2  = setup("/player/boy_axe_down_2",gp.tileSize,gp.tileSize*2);
            attackLeft1  = setup("/player/boy_axe_left_1",gp.tileSize*2,gp.tileSize);
            attackLeft2  = setup("/player/boy_axe_left_2",gp.tileSize*2,gp.tileSize);
            attackRight1  = setup("/player/boy_axe_right_1",gp.tileSize*2,gp.tileSize);
            attackRight2  = setup("/player/boy_axe_right_2",gp.tileSize*2,gp.tileSize);
        }
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

            //check interactive tile collision
            int iTileIndex = gp.cChecker.checkEntity(this,gp.iTile);

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

            if(keyH.enterPressed == true && attackCanceled == false){
                attacking = true;
                spriteCounter = 0;
            }

            attackCanceled = false;
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
        else{
            standCounter++;
            if(standCounter == 20){
                spriteNum = 1;
                standCounter = 0;
            }
        }
        if(gp.keyH.shotKeyPressed == true && projectile.alive == false
                && shotAvailableCounter == 30 && projectile.haveResource(this) == true ){
            projectile.set(worldX, worldY, direction, true, this);

            projectile.subtractResource(this);

            gp.projectileList.add(projectile);

            shotAvailableCounter = 0;
            gp.playSE(7);
        }

        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if(shotAvailableCounter < 30){
            shotAvailableCounter++;
        }
        if(life > maxLife){
            life = maxLife;
        }
        if(mana > maxMana){
            mana = maxMana;
        }
        if(life <= 0){
            gp.gameState = gp.GAMEOVER_STATE;
            gp.stopMusic();
            gp.ui.commandNum = -1;
            gp.ui.titleScreenState = 0;
            gp.playSE(9);
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
                case "up"->{ worldY -= (attackArea.height*1.2);}
                case "down"->{ worldY += attackArea.height;}
                case "left"->{worldX -= attackArea.width;}
                case "right"->{worldX += attackArea.width;}
            }
            //atackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            //check monster collision with the updated worldX,worldY and solidArea
            int monsterIndex = gp.cChecker.checkEntity(this,gp.monster);
            damageMonster(monsterIndex, attack);

            int iTileIndex = gp.cChecker.checkEntity(this,gp.iTile);
            damageInteractiveTile(iTileIndex);

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

            if(gp.obj[gp.currentMap][i].type == type_pickupOnly){

                gp.obj[gp.currentMap][i].use(this);
                gp.obj[gp.currentMap][i] = null;
            }
            else{
                String text;

                if(inventory.size() != maxInventorySize){
                    inventory.add(gp.obj[gp.currentMap][i]);
                    gp.playSE(1);
                    text = "Got a "+gp.obj[gp.currentMap][i].name+"!";
                }
                else{
                    text = "You cannot carry any more";
                }
                gp.ui.addMessage(text);
                gp.obj[gp.currentMap][i] = null;
            }



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
                attackCanceled = true;
                gp.gameState = gp.DIALOGUE_STATE;
                gp.npc[gp.currentMap][i].speak();
            }
        }

    }
    public void contactMonster(int i){
        if(i != 999){
            if(invincible == false && gp.monster[gp.currentMap][i].dying == false){
                gp.playSE(6);

                int damage = gp.monster[gp.currentMap][i].attack - defense;
                if(damage < 0)
                    damage = 0;
                life -= damage;
                invincible= true;
            }

        }
    }
    public void damageMonster(int i, int attack){
        if(i != 999){
            if(gp.monster[gp.currentMap][i].invincible == false){

                gp.playSE(5);

                int damage = attack - gp.monster[gp.currentMap][i].defense;
                if(damage < 0) damage = 0;
                gp.monster[gp.currentMap][i].life -= damage;
                gp.ui.addMessage(damage + "damage!");
                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();

                if(gp.monster[gp.currentMap][i].life <= 0){
                    gp.monster[gp.currentMap][i].dying = true;
                    gp.monster[gp.currentMap][i].alive = false;
                    gp.ui.addMessage("Killed the "+gp.monster[gp.currentMap][i].name + "!");
                    gp.ui.addMessage("Exp + " + gp.monster[gp.currentMap][i].exp);
                    exp += gp.monster[gp.currentMap][i].exp;
                    checkLevelUp();
                }
            }
        }
    }
    public void damageInteractiveTile(int i){
        if(i != 999 && gp.iTile[gp.currentMap][i].destructible == true && gp.iTile[gp.currentMap][i].isCorrectItem(this) && gp.iTile[gp.currentMap][i].invincible == false){
            gp.iTile[gp.currentMap][i].playSE();
            gp.iTile[gp.currentMap][i].life--;
            gp.iTile[gp.currentMap][i].invincible = true;

            //Generate particle
            generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);

            if(gp.iTile[gp.currentMap][i].life == 0){
                gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
            }

        }
    }
    public void checkLevelUp(){

        if(exp >= nextLevelExp){

            level++;
            nextLevelExp = nextLevelExp*2;
            maxLife += 2;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();

            gp.playSE(1);
            gp.gameState = gp.DIALOGUE_STATE;

            gp.ui.currentDialogue = "You are level "+level+" now\n"
                    + "You feel stronger";
        }
    }
    public void selectItem(){

        int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol,gp.ui.playerSlotRow);

        if(itemIndex < inventory.size()){

            Entity selectedItem = inventory.get(itemIndex);

            if(selectedItem.type == type_sword || selectedItem.type == type_axe){

                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }

            if(selectedItem.type == type_shield){

                currentShield = selectedItem;
                defense = getDefense();
            }

            if(selectedItem.type == type_consumable){

                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
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
