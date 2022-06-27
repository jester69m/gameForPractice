package main;

import entity.NPC_Oldman;
import monster.MON_GreenSlime;
import object.*;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp=gp;
    }
    public void setObject(){
        int i = 0;
        //up left way key
        gp.obj[i] = new OBJ_Key(gp);
        gp.obj[i].worldX = 3*gp.tileSize;
        gp.obj[i].worldY = 12*gp.tileSize;
        i++;
        //down key
        gp.obj[i] = new OBJ_Key(gp);
        gp.obj[i].worldX = 25*gp.tileSize;
        gp.obj[i].worldY = 47*gp.tileSize;
        i++;
        //up right way key
        gp.obj[i] = new OBJ_Key(gp);
        gp.obj[i].worldX = 41*gp.tileSize;
        gp.obj[i].worldY = 3*gp.tileSize;
        i++;
        gp.obj[i] = new OBJ_Axe(gp);
        gp.obj[i].worldX = 45*gp.tileSize;
        gp.obj[i].worldY = 23*gp.tileSize;
        i++;
        gp.obj[i] = new OBJ_Shield_Blue(gp);
        gp.obj[i].worldX = 10*gp.tileSize;
        gp.obj[i].worldY = 5*gp.tileSize;
        i++;
        gp.obj[i] = new OBJ_Potion_Red(gp);
        gp.obj[i].worldX = 23*gp.tileSize;
        gp.obj[i].worldY = 8*gp.tileSize;


        /*//left way door
        gp.obj[3] = new OBJ_Door(gp);
        gp.obj[3].worldX = 14*gp.tileSize;
        gp.obj[3].worldY = 23*gp.tileSize;
        //castle door
        gp.obj[4] = new OBJ_Door(gp);
        gp.obj[4].worldX = 9*gp.tileSize;
        gp.obj[4].worldY = 40*gp.tileSize;
        //up left way

        gp.obj[5] = new OBJ_Door(gp);
        gp.obj[5].worldX = 21*gp.tileSize;
        gp.obj[5].worldY = 13*gp.tileSize;
        //chest
        gp.obj[6] = new OBJ_Chest(gp);
        gp.obj[6].worldX = 6*gp.tileSize;
        gp.obj[6].worldY = 45*gp.tileSize;
        //boots
        gp.obj[7] = new OBJ_Boots(gp);
        gp.obj[7].worldX = 42*gp.tileSize;
        gp.obj[7].worldY = 36*gp.tileSize;*/

    }
    public void setNPC(){
        gp.npc[0] = new NPC_Oldman(gp);
        gp.npc[0].worldX = gp.tileSize*23;
        gp.npc[0].worldY = gp.tileSize*25;

    }
    public void setMonster(){
        int i = 0;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize*23;
        gp.monster[i].worldY = gp.tileSize*22;
        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize*23;
        gp.monster[i].worldY = gp.tileSize*27;
    }
}
