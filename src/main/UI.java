package main;

import entity.Entity;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font robotoL, purisaB;

    BufferedImage heart_full,heart_half,heart_blank, crystal_full, crystal_blank;
    //BufferedImage keyImage;

    public boolean messageOn = false;
//    public String message =  "";
//    int messageCounter = 0;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false;
    public String currentDialogue="";
    public int commandNum = 0;
    public int titleScreenState = 0;
    public int slotCol = 0;
    public int slotRow = 0;

    public UI(GamePanel gp){
        this.gp = gp;

        try{
            InputStream is =  getClass().getResourceAsStream("/font/Purisa Bold.ttf");
            purisaB = Font.createFont(Font.TRUETYPE_FONT,is);
            is = getClass().getResourceAsStream("/font/Roboto-Light.ttf");
            robotoL = Font.createFont(Font.TRUETYPE_FONT,is);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }

        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
        Entity crystal = new OBJ_ManaCrystal(gp);
        crystal_full = crystal.image;
        crystal_blank = crystal.image2;


      //  OBJ_Key key = new OBJ_Key(gp);
      //  keyImage = key.image;
    }

    public void addMessage(String text){
        message.add(text);
        messageCounter.add(0);


    }
    public void draw(Graphics2D g2){

        this.g2=g2;

        g2.setFont(robotoL);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);

        if(gp.gameState == gp.TITLE_STATE){
            drawTitleScreen();
        }
        //PLAY STATE
        else if(gp.gameState == gp.PLAY_STATE){
            drawPlayerLife();
            drawMessage();
        }
        //PAUSE STATE
        else if(gp.gameState == gp.PAUSE_STATE){
            drawPlayerLife();
            drawPauseScreen();
        }
        //DIALOG STATE
        else if(gp.gameState == gp.DIALOGUE_STATE){
            drawPlayerLife();
            drawDialogueScreen();
        }
        //CHARACTER STATE
        else if(gp.gameState == gp.CHARACTER_STATE){
            drawCharacterScreen();
            drawInventory();
        }
    }
    public void drawPlayerLife(){

        //gp.player.life = 3;

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        while(i < gp.player.maxLife/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        while(i < gp.player.life){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_full,x,y,null);
            }
            i++;
            x += gp.tileSize;
        }

        //DRAW MAX MANA
        x = (gp.tileSize/2)-5;
        y = (int)(gp.tileSize*1.5);
        i = 0;
        while(i < gp.player.maxMana){
            g2.drawImage(crystal_blank, x, y, null);
            i++;
            x += 35;
        }

        //DRAW MANA
        x = (gp.tileSize/2)-5;
        y = (int)(gp.tileSize*1.5);
        i = 0;
        while (i < gp.player.mana){
            g2.drawImage(crystal_full, x,y, null);
            i++;
            x += 35;

        }

    }
    public void drawMessage(){
        int messageX = gp.tileSize;
        int messageY=gp.tileSize*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,32F));

        for(int i = 0; i<message.size();i++){

            if(message.get(i) != null){
                g2.setColor(Color.black);
                g2.drawString(message.get(i),messageX+2,messageY+2);

                g2.setColor(Color.white);
                g2.drawString(message.get(i),messageX,messageY);

                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i,counter);
                messageY += 50;

                if(messageCounter.get(i) > 180){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }

        }
    }
    public void drawTitleScreen(){

        if(titleScreenState == 0){

            g2.setColor(new Color(70,120,80));
            g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

            //TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
            String text = "Adventure Time";
            int x = getXForCenteredText(text);
            int y = gp.tileSize * 3;

            //SHADOW
            g2.setColor(Color.gray);
            g2.drawString(text,x+5,y+5);
            //MAIN COLOR
            g2.setColor(Color.white);
            g2.drawString(text,x,y);
            //MAIN IMAGE
            x = gp.screenWidth/2 - (gp.tileSize*2)/2;
            y += gp.tileSize*2;
            g2.drawImage(gp.player.down1,x,y,gp.tileSize*2,gp.tileSize*2,null);

            //MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,42F));
            text = "NEW GAME";
            x = getXForCenteredText(text);
            y+=gp.tileSize*3.5;
            g2.drawString(text,x,y);
            if(commandNum == 0){
                g2.drawString(">",x-gp.tileSize,y);
            }

            text = "LOAD GAME";
            x = getXForCenteredText(text);
            y+=gp.tileSize;
            g2.drawString(text,x,y);
            if(commandNum == 1){
                g2.drawString(">",x-gp.tileSize,y);
            }

            text = "EXIT";
            x = getXForCenteredText(text);
            y+=gp.tileSize;
            g2.drawString(text,x,y);
            if(commandNum == 2){
                g2.drawString(">",x-gp.tileSize,y);
            }
        } else if(titleScreenState == 1){

            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));

            String text = "Select your class";
            int x = getXForCenteredText(text);
            int y = gp.tileSize*3;
            g2.drawString(text,x,y);

            text = "Knight";
            x = getXForCenteredText(text);
            y += gp.tileSize*3;
            g2.drawString(text,x,y);
            if(commandNum == 0){
                g2.drawString(">",x-gp.tileSize,y);
            }

            text = "Thief";
            x = getXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text,x,y);
            if(commandNum == 1){
                g2.drawString(">",x-gp.tileSize,y);
            }

            text = "Wizard";
            x = getXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text,x,y);
            if(commandNum == 2){
                g2.drawString(">",x-gp.tileSize,y);
            }

            text = "Back";
            x = getXForCenteredText(text);
            y += gp.tileSize*2;
            g2.drawString(text,x,y);
            if(commandNum == 3){
                g2.drawString(">",x-gp.tileSize,y);
            }
        }

    }
    public void drawPauseScreen(){

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text,x,y);
    }
    public void drawDialogueScreen(){

        //WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;
        drawSubWindow(x,y,width,height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));
        x+= gp.tileSize;
        y+=gp.tileSize;

        for(String line: currentDialogue.split("\n")){
            g2.drawString(line,x,y);
            y+=40;
        }
    }
    public void drawCharacterScreen(){

        //CREATE A FRAME
        final int frameX = gp.tileSize;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize*5;
        final int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        //TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(36F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int  lineHeight = 35;

        //NAMES
        g2.drawString("Level" , textX,textY);
        textY += lineHeight;
        g2.drawString("Life" , textX,textY);
        textY += lineHeight;
        g2.drawString("Mana" , textX,textY);
        textY += lineHeight;
        g2.drawString("Strength" , textX,textY);
        textY += lineHeight;
        g2.drawString("Dexterity" , textX,textY);
        textY += lineHeight;
        g2.drawString("Attack" , textX,textY);
        textY += lineHeight;
        g2.drawString("Defense" , textX,textY);
        textY += lineHeight;
        g2.drawString("Exp" , textX,textY);
        textY += lineHeight;
        g2.drawString("Next Level" , textX,textY);
        textY += lineHeight;
        g2.drawString("Coin" , textX,textY);
        textY += lineHeight + 10;
        g2.drawString("Weapon" , textX,textY);
        textY += lineHeight + 15;
        g2.drawString("Shield" , textX,textY);
        textY += lineHeight;

        //VALUES
        int tailX = (frameX + frameWidth) - 30;
        //reset textY
        textY = frameY + gp.tileSize ;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXForAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.life+"/"+gp.player.maxLife);
        textX = getXForAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.mana+"/"+gp.player.maxMana);
        textX = getXForAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXForAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXForAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXForAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXForAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXForAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXForAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = getXForAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight-10;

        g2.drawImage(gp.player.currentWeapon.down1,tailX - gp.tileSize, textY - 24, null);
        textY += gp.tileSize+2;

        g2.drawImage(gp.player.currentShield.down1,tailX - gp.tileSize, textY - 24, null);
    }
    public void drawInventory(){

        //FRAME
        int frameX = gp.tileSize*9;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*6;
        int frameHeight = gp.tileSize*5;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        //SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize+3;

        //DRAW  PLAYER'S ITEMS
        for(int i=0;i<gp.player.inventory.size();i++){

            //EQUIP CURSOR
            if(gp.player.inventory.get(i) == gp.player.currentWeapon||
                gp.player.inventory.get(i) == gp.player.currentShield){
                g2.setColor(new Color(240,190,90));
                g2.fillRoundRect(slotX,slotY,gp.tileSize,gp.tileSize,10,10);
            }

            g2.drawImage(gp.player.inventory.get(i).down1,slotX,slotY,null);

            slotX += slotSize;

            if(i == 4 || i == 9 || i == 14){
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        //CURSOR
        int cursorX = slotXstart + (gp.tileSize * slotCol);
        int cursorY = slotYstart + (gp.tileSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        //DRAW CURSOR
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX,cursorY,cursorWidth,cursorHeight,10,10);

        //DESCRIPTION FRAME
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.tileSize*3;

        //DRAW DESCRIPTION TEXT
        int textX = dFrameX + 20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(28F));
        int itemIndex = getItemIndexOnSlot();

        if(itemIndex < gp.player.inventory.size()){

            drawSubWindow(dFrameX,dFrameY,dFrameWidth,dFrameHeight);

            for(String line: gp.player.inventory.get(itemIndex).description.split("\n")) {
                g2.drawString(line, textX, textY);
                textY += 32;
            }
        }
    }
    public int getItemIndexOnSlot(){
        int itemIndex = slotCol + (slotRow*5);
        return itemIndex;
    }
    public void drawSubWindow(int x,int y,int width,int height){

        Color c = new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,35,35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width - 10,height - 10,25,25);
    }
    public int getXForCenteredText(String text){
        int length = (int) g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
    public int getXForAlignRightText(String text,int tailX){
        int length = (int) g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = tailX - length/2;
        return x;
    }

}
