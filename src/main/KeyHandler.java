package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed,leftPressed,downPressed,rightPressed, enterPressed;

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
        //PLAY STATE
        if(gp.gameState == gp.PLAY_STATE){
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
                if(gp.gameState == gp.PLAY_STATE){
                    gp.gameState = gp.PAUSE_STATE;
                } else if(gp.gameState == gp.PAUSE_STATE){
                    gp.gameState = gp.PLAY_STATE;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                enterPressed = true;
            }
        }
        else if(gp.gameState == gp.PAUSE_STATE){
            if(code == KeyEvent.VK_P){
                gp.gameState = gp.PLAY_STATE;
            }
        }
        else if(gp.gameState == gp.DIALOGUE_STATE){
            if(code == KeyEvent.VK_ENTER){
                gp.gameState = gp.PLAY_STATE;
            }
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
    }
}
