package entity;

import main.GamePanel;
import object.*;

import java.awt.*;

public class NPC_Merchant extends Entity {

    public NPC_Merchant(GamePanel gp){
        super(gp);

        direction="up";
        speed=1;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        getImage();
        setDialogue();
        setItems();
    }
    public void getImage(){
        up1 = setup("/npc/merchant_down_1",gp.tileSize,gp.tileSize);
        up2 = setup("/npc/merchant_down_2",gp.tileSize,gp.tileSize);
        down1 = setup("/npc/merchant_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/npc/merchant_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("/npc/merchant_down_1",gp.tileSize,gp.tileSize);
        left2 = setup("/npc/merchant_down_2",gp.tileSize,gp.tileSize);
        right1 = setup("/npc/merchant_down_1",gp.tileSize,gp.tileSize);
        right2 = setup("/npc/merchant_down_2",gp.tileSize,gp.tileSize);
    }

    public void setDialogue(){
        dialogues[0] = "He he, so you found me.\nI have some good stuff.\nDo you want to trade?";

    }
    public void setItems(){
        inventory.add(new OBJ_Potion_Red(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Sword_Normal(gp));
        inventory.add(new OBJ_Axe(gp));
        inventory.add(new OBJ_Shield_Wood(gp));
        inventory.add(new OBJ_Shield_Blue(gp));


    }

    public void speak(){
        super.speak();
        gp.gameState = gp.TRADE_STATE;
        gp.ui.npc = this;
    }
}
