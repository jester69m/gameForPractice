package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed,leftPressed,downPressed,rightPressed, enterPressed, shotKeyPressed;

    public KeyHandler(GamePanel gp){
        this.gp=gp;

    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        //TITLE STATE
        if(gp.gameState == gp.TITLE_STATE){
            titleState(code);
        }
        //PLAY STATE
         else if(gp.gameState == gp.PLAY_STATE){
            playState(code);
        }
         //PAUSE STATE
        else if(gp.gameState == gp.PAUSE_STATE){
            pauseState(code);
        }
        //DIALOG STATE
        else if(gp.gameState == gp.DIALOGUE_STATE){
            dialogState(code);
        }
        //CHARACTER STATE
        else if(gp.gameState == gp.CHARACTER_STATE){
            characterState(code);
        }
        //OPTIONS STATE
        else if(gp.gameState == gp.OPTIONS_STATE){
            optionsState(code);
        }
        //GAMEOVER STATE
        else if(gp.gameState == gp.GAMEOVER_STATE){
            gameOverState(code);
        }
        else if(gp.gameState == gp.TRADE_STATE){
            tradeState(code);
        }
    }
    public void titleState(int code){

        if(gp.ui.titleScreenState == 0){

            if(code == KeyEvent.VK_W){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    gp.ui.titleScreenState = 1;
                    gp.playMusic(0);
                }
                if(gp.ui.commandNum == 1){

                }
                if(gp.ui.commandNum == 2){
                    System.exit(0);
                }
            }
        } else if(gp.ui.titleScreenState == 1){
            if(code == KeyEvent.VK_W){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 3;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 3){
                    gp.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    System.out.println("Choose swordsman");
                    gp.gameState = gp.PLAY_STATE;
                    gp.playMusic(0);
                }
                if(gp.ui.commandNum == 1){
                    System.out.println("Choose thief");
                    gp.gameState = gp.PLAY_STATE;
                    gp.playMusic(0);
                }
                if(gp.ui.commandNum == 2){
                    System.out.println("Choose wizard");
                    gp.gameState = gp.PLAY_STATE;
                    gp.playMusic(0);
                }
                if(gp.ui.commandNum == 3){
                    gp.ui.titleScreenState = 0;
                }
            }

        }
    }
    public void playState(int code){
        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.PAUSE_STATE;
        }
        if(code == KeyEvent.VK_C){
            gp.gameState = gp.CHARACTER_STATE;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        if(code == KeyEvent.VK_F){
            shotKeyPressed = true;
        }
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.OPTIONS_STATE;
        }
        if(code == KeyEvent.VK_R){
            switch(gp.currentMap){
                case 0:gp.tileM.loadMap("maps/world01.txt",0);break;
                case 1:gp.tileM.loadMap("maps/interior01.txt",1);break;
            }
        }
    }
    public void pauseState(int code){
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.PLAY_STATE;
        }
    }
    public void dialogState(int code){
        if(code == KeyEvent.VK_ENTER){
            gp.gameState = gp.PLAY_STATE;
        }
    }
    public void characterState(int code){
        if(code == KeyEvent.VK_C){
            gp.gameState = gp.PLAY_STATE;
        }
        if(code == KeyEvent.VK_ENTER){
            gp.player.selectItem();
        }
        playerInventory(code);

    }
    public void optionsState(int code){
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.PLAY_STATE;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }

        int maxCommandNum = 0;
        switch (gp.ui.subState){
            case 0: maxCommandNum = 4;break;
            case 3: maxCommandNum = 1;break;
            }
        if(code == KeyEvent.VK_W){
            gp.ui.commandNum--;
//            gp.playSE(9);
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if(code == KeyEvent.VK_S){
            gp.ui.commandNum++;
//            gp.playSE(9);
            if(gp.ui.commandNum > maxCommandNum){
                gp.ui.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_A){
            if(gp.ui.subState == 0){
                if(gp.ui.commandNum == 0 && gp.music.volumeScale > 0){
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
//                    gp.playSE(9);
                }
                if(gp.ui.commandNum == 1 && gp.se.volumeScale > 0){
                    gp.se.volumeScale--;
//                    gp.playSE(9);
                }

            }
        }
        if(code == KeyEvent.VK_D){
            if(gp.ui.subState == 0){
                if(gp.ui.commandNum == 0 && gp.music.volumeScale < 5){
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                }
                if(gp.ui.commandNum == 1 && gp.se.volumeScale <5){
                    gp.se.volumeScale++;
                }
            }
        }
    }
    public void gameOverState(int code){
        if(code == KeyEvent.VK_W){
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = 1;
            }
        }
        if(code == KeyEvent.VK_S){
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 1){
                gp.ui.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.commandNum == 0){
                gp.gameState = gp.PLAY_STATE;
                gp.retry();
                gp.playMusic(0);
            }
            else if(gp.ui.commandNum == 1){
                gp.gameState = gp.TITLE_STATE;
                gp.ui.titleScreenState = 0;
                gp.restart();
            }
        }
    }
    public void tradeState(int code){

        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        if(gp.ui.subState == 0){
            if(code == KeyEvent.VK_W){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
            }
        }
        if(gp.ui.subState == 1){
            npcInventory(code);
            if(code == KeyEvent.VK_ESCAPE){
                gp.ui.subState = 0;
            }
        }
    }
    public void playerInventory(int code){

        if(code == KeyEvent.VK_W){
            if(gp.ui.playerSlotRow != 0)
                gp.ui.playerSlotRow--;
        }
        if(code == KeyEvent.VK_A){
            if(gp.ui.playerSlotCol != 0)
                gp.ui.playerSlotCol--;
        }
        if(code == KeyEvent.VK_S){
            if(gp.ui.playerSlotRow != 3)
                gp.ui.playerSlotRow++;
        }
        if(code == KeyEvent.VK_D){
            if(gp.ui.playerSlotCol != 4)
                gp.ui.playerSlotCol++;
        }


    }
    public void npcInventory(int code){

        if(code == KeyEvent.VK_W){
            if(gp.ui.npcSlotRow != 0)
                gp.ui.npcSlotRow--;
        }
        if(code == KeyEvent.VK_A){
            if(gp.ui.npcSlotCol != 0)
                gp.ui.npcSlotCol--;
        }
        if(code == KeyEvent.VK_S){
            if(gp.ui.npcSlotRow != 3)
                gp.ui.npcSlotRow++;
        }
        if(code == KeyEvent.VK_D){
            if(gp.ui.npcSlotCol != 4)
                gp.ui.npcSlotCol++;
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_F){
            shotKeyPressed = false;
        }
    }
}
