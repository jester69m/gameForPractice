package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_Oldman extends Entity{

    GamePanel gp;

    public NPC_Oldman(GamePanel gp){
        super(gp);

        direction="down";
        speed=1;

        getImage();
        setDialogue();
    }
    public void getImage(){
        up1 = setup("/npc/oldman");
        up2 = setup("/npc/oldman");
        down1 = setup("/npc/oldman");
        down2 = setup("/npc/oldman");
        left1 = setup("/npc/oldman");
        left2 = setup("/npc/oldman");
        right1 = setup("/npc/oldman");
        right2 = setup("/npc/oldman");
    }
    public void setDialogue(){
        dialogues[0] = "Hello, traveller.";
        dialogues[1] = "So you've come to find your love?";
        dialogues[2] = "I used to be a great wizard but now...\nI'm a bit too old for taking adventure";
        dialogues[3] = "Well, good luck on you";
    }
    public void setAction(){

        actionLockCounter++;

        if(actionLockCounter == 120){

            Random random = new Random();
            int i = random.nextInt(100)+1;
            if(i<25){
                direction = "up";
            }
            if(i>25 && i <=50){
                direction = "down";
            }
            if(i>50 && i <= 75){
                direction = "left";
            }
            if(i > 75 && i <=100){
                direction = "right";
            }
            actionLockCounter=0;
        }
    }
    public void speak(){

        super.speak();
    }
}
