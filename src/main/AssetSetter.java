package main;

import entity.NPC_Oldman;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp=gp;
    }
    public void setObject(){
 /*       //up left way key
        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = 3*gp.tileSize;
        gp.obj[0].worldY = 12*gp.tileSize;
        //down key
        gp.obj[1] = new OBJ_Key(gp);
        gp.obj[1].worldX = 25*gp.tileSize;
        gp.obj[1].worldY = 47*gp.tileSize;
        //up right way key
        gp.obj[2] = new OBJ_Key(gp);
        gp.obj[2].worldX = 41*gp.tileSize;
        gp.obj[2].worldY = 3*gp.tileSize;
        //left way door
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
        gp.npc[0].worldX = gp.tileSize*21;
        gp.npc[0].worldY = gp.tileSize*23;
    }
}
