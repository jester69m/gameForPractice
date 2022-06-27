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
        if(code == KeyEvent.VK_W){
            if(gp.ui.slotRow != 0)
                gp.ui.slotRow--;
        }
        if(code == KeyEvent.VK_A){
            if(gp.ui.slotCol != 0)
                gp.ui.slotCol--;
        }
        if(code == KeyEvent.VK_S){
            if(gp.ui.slotRow != 3)
                gp.ui.slotRow++;
        }
        if(code == KeyEvent.VK_D){
            if(gp.ui.slotCol != 4)
                gp.ui.slotCol++;
        }
        if(code == KeyEvent.VK_ENTER){
            gp.player.selectItem();
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
