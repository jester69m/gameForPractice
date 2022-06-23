package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends Entity {

    public OBJ_Heart(GamePanel gp){
        super(gp);
        name="Heart";
        image = setup("/objects/full-heart");
        image2 = setup("/objects/half-heart");
        image3 = setup("/objects/blank-heart");


    }
}
