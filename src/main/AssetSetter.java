package main;

import entity.NPC_Merchant;
import entity.NPC_Oldman;
import monster.MON_GreenSlime;
import object.*;
import tile_interactive.IT_DryTree;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp=gp;
    }
    public void setObject(){
        int numMap = 0;
        int i = 0;
        //up left way key
        gp.obj[numMap][i] = new OBJ_Coin_Bronze(gp);
        gp.obj[numMap][i].worldX = 3*gp.tileSize;
        gp.obj[numMap][i].worldY = 12*gp.tileSize;
        i++;
        //down key
        gp.obj[numMap][i] = new OBJ_Coin_Bronze(gp);
        gp.obj[numMap][i].worldX = 25*gp.tileSize;
        gp.obj[numMap][i].worldY = 47*gp.tileSize;
        i++;
        //up right way key
        gp.obj[numMap][i] = new OBJ_Coin_Bronze(gp);
        gp.obj[numMap][i].worldX = 41*gp.tileSize;
        gp.obj[numMap][i].worldY = 3*gp.tileSize;
        i++;
        gp.obj[numMap][i] = new OBJ_Axe(gp);
        gp.obj[numMap][i].worldX = 45*gp.tileSize;
        gp.obj[numMap][i].worldY = 23*gp.tileSize;
        i++;
        gp.obj[numMap][i] = new OBJ_Shield_Blue(gp);
        gp.obj[numMap][i].worldX = 10*gp.tileSize;
        gp.obj[numMap][i].worldY = 5*gp.tileSize;
        i++;
        gp.obj[numMap][i] = new OBJ_Potion_Red(gp);
        gp.obj[numMap][i].worldX = 23*gp.tileSize;
        gp.obj[numMap][i].worldY = 8*gp.tileSize;
        i++;
        gp.obj[numMap][i] = new OBJ_Potion_Red(gp);
        gp.obj[numMap][i].worldX = 40*gp.tileSize;
        gp.obj[numMap][i].worldY = 3*gp.tileSize;
        i++;
        gp.obj[numMap][i] = new OBJ_Heart(gp);
        gp.obj[numMap][i].worldX = 41*gp.tileSize;
        gp.obj[numMap][i].worldY = 7*gp.tileSize;
        i++;
        gp.obj[numMap][i] = new OBJ_Heart(gp);
        gp.obj[numMap][i].worldX = 41*gp.tileSize;
        gp.obj[numMap][i].worldY = 7*gp.tileSize;
        i++;
        gp.obj[numMap][i] = new OBJ_Heart(gp);
        gp.obj[numMap][i].worldX = 5*gp.tileSize;
        gp.obj[numMap][i].worldY = 7*gp.tileSize;
        i++;
        gp.obj[numMap][i] = new OBJ_Heart(gp);
        gp.obj[numMap][i].worldX = 2*gp.tileSize;
        gp.obj[numMap][i].worldY = 30*gp.tileSize;
        i++;
        gp.obj[numMap][i] = new OBJ_Heart(gp);
        gp.obj[numMap][i].worldX = 14*gp.tileSize;
        gp.obj[numMap][i].worldY = 4*gp.tileSize;
        i++;
        gp.obj[numMap][i] = new OBJ_ManaCrystal(gp);
        gp.obj[numMap][i].worldX = 42*gp.tileSize;
        gp.obj[numMap][i].worldY = 3*gp.tileSize;
        i++;
        gp.obj[numMap][i] = new OBJ_ManaCrystal(gp);
        gp.obj[numMap][i].worldX = 47*gp.tileSize;
        gp.obj[numMap][i].worldY = 15*gp.tileSize;
        i++;
        gp.obj[numMap][i] = new OBJ_ManaCrystal(gp);
        gp.obj[numMap][i].worldX = 5*gp.tileSize;
        gp.obj[numMap][i].worldY = 16*gp.tileSize;

        //left way door
        i++;
        gp.obj[numMap][i] = new OBJ_Door(gp);
        gp.obj[numMap][i].worldX = 14*gp.tileSize;
        gp.obj[numMap][i].worldY = 23*gp.tileSize;

        //castle door
        i++;
        gp.obj[numMap][i] = new OBJ_Door(gp);
        gp.obj[numMap][i].worldX = 9*gp.tileSize;
        gp.obj[numMap][i].worldY = 40*gp.tileSize;

        //up left way
        i++;
        gp.obj[numMap][i] = new OBJ_Door(gp);
        gp.obj[numMap][i].worldX = 21*gp.tileSize;
        gp.obj[numMap][i].worldY = 13*gp.tileSize;

        //chest
        i++;
        gp.obj[numMap][i] = new OBJ_Chest(gp);
        gp.obj[numMap][i].worldX = 6*gp.tileSize;
        gp.obj[numMap][i].worldY = 45*gp.tileSize;

        //boots
        i++;
        gp.obj[numMap][i] = new OBJ_Boots(gp);
        gp.obj[numMap][i].worldX = 44*gp.tileSize;
        gp.obj[numMap][i].worldY = 38*gp.tileSize;

    }
    public void setNPC(){
        int numMap = 0;
        int i = 0;

        //MAP 0
        /*gp.npc[numMap][i] = new NPC_Oldman(gp);
        gp.npc[numMap][i].worldX = gp.tileSize*23;
        gp.npc[numMap][i].worldY = gp.tileSize*25;*/

        //MAP 1
        numMap = 1;
        i = 0;
        gp.npc[numMap][i] = new NPC_Merchant(gp);
        gp.npc[numMap][i].worldX = gp.tileSize*13;
        gp.npc[numMap][i].worldY = gp.tileSize*10;

    }
    public void setMonster(){
        int numMap = 0;
        //to left top corner
        int i = 0;
        gp.monster[numMap][i] = new MON_GreenSlime(gp);
        gp.monster[numMap][i].worldX = gp.tileSize*15;
        gp.monster[numMap][i].worldY = gp.tileSize*2;
        i++;
        gp.monster[numMap][i] = new MON_GreenSlime(gp);
        gp.monster[numMap][i].worldX = gp.tileSize*10;
        gp.monster[numMap][i].worldY = gp.tileSize*4;
        i++;
        //to right top
        gp.monster[numMap][i] = new MON_GreenSlime(gp);
        gp.monster[numMap][i].worldX = gp.tileSize*34;
        gp.monster[numMap][i].worldY = gp.tileSize*5;
        i++;
        gp.monster[numMap][i] = new MON_GreenSlime(gp);
        gp.monster[numMap][i].worldX = gp.tileSize*47;
        gp.monster[numMap][i].worldY = gp.tileSize*10;
        //to right
        i++;
        gp.monster[numMap][i] = new MON_GreenSlime(gp);
        gp.monster[numMap][i].worldX = gp.tileSize*41;
        gp.monster[numMap][i].worldY = gp.tileSize*23;
        i++;
        gp.monster[numMap][i] = new MON_GreenSlime(gp);
        gp.monster[numMap][i].worldX = gp.tileSize*42;
        gp.monster[numMap][i].worldY = gp.tileSize*24;
        i++;
        gp.monster[numMap][i] = new MON_GreenSlime(gp);
        gp.monster[numMap][i].worldX = gp.tileSize*24;
        gp.monster[numMap][i].worldY = gp.tileSize*30;
        i++;
        //to right down
        gp.monster[numMap][i] = new MON_GreenSlime(gp);
        gp.monster[numMap][i].worldX = gp.tileSize*25;
        gp.monster[numMap][i].worldY = gp.tileSize*45;
        i++;
        //to left bottom
        gp.monster[numMap][i] = new MON_GreenSlime(gp);
        gp.monster[numMap][i].worldX = gp.tileSize*9;
        gp.monster[numMap][i].worldY = gp.tileSize*37;
        i++;
        gp.monster[numMap][i] = new MON_GreenSlime(gp);
        gp.monster[numMap][i].worldX = gp.tileSize*9;
        gp.monster[numMap][i].worldY = gp.tileSize*34;
        i++;
        gp.monster[numMap][i] = new MON_GreenSlime(gp);
        gp.monster[numMap][i].worldX = gp.tileSize*7;
        gp.monster[numMap][i].worldY = gp.tileSize*22;
    }

    public void setInteractiveTile(){
        int numMap = 0;
        int i = 0;
        gp.iTile[numMap][i] = new IT_DryTree(gp, 10,23);
        i++;
        gp.iTile[numMap][i] = new IT_DryTree(gp, 11,23);
        i++;
        gp.iTile[numMap][i] = new IT_DryTree(gp, 12,23);
        i++;
        gp.iTile[numMap][i] = new IT_DryTree(gp, 13,23);
        i++;
        gp.iTile[numMap][i] = new IT_DryTree(gp, 14,23);
        i++;
        gp.iTile[numMap][i] = new IT_DryTree(gp, 15,23);

        //to top
        i++;
        gp.iTile[numMap][i] = new IT_DryTree(gp, 23,11);
        i++;
        gp.iTile[numMap][i] = new IT_DryTree(gp, 23,10);

        //to right
        i++;
        gp.iTile[numMap][i] = new IT_DryTree(gp, 42,31);
        i++;
        gp.iTile[numMap][i] = new IT_DryTree(gp, 42,32);
        i++;
        gp.iTile[numMap][i] = new IT_DryTree(gp, 42,33);
        i++;
        gp.iTile[numMap][i] = new IT_DryTree(gp, 42,34);



        gp.iTile[numMap][i] = new IT_DryTree(gp, 20,48);
        i++;
        gp.iTile[numMap][i] = new IT_DryTree(gp, 21,48);
        i++;
        gp.iTile[numMap][i] = new IT_DryTree(gp, 22,48);
        i++;
        gp.iTile[numMap][i] = new IT_DryTree(gp, 23,48);
        i++;
        gp.iTile[numMap][i] = new IT_DryTree(gp, 20,47);
        i++;
        gp.iTile[numMap][i] = new IT_DryTree(gp, 20,46);
        i++;
        gp.iTile[numMap][i] = new IT_DryTree(gp, 20,45);
        i++;
        gp.iTile[numMap][i] = new IT_DryTree(gp, 19,45);
        i++;
        gp.iTile[numMap][i] = new IT_DryTree(gp, 19,44);
    }
}
