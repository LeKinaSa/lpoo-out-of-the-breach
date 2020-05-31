package out_of_the_breach;

import out_of_the_breach.GUI.*;
import out_of_the_breach.levels.*;
import out_of_the_breach.model.*;

import com.googlecode.lanterna.TextColor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
        LanternaTerminal t  = new LanternaTerminal(110, 40);
        GUIRoot root        = new GUIRoot(t, new TextColor.RGB(40, 40, 40));

        List<Level> levelList = new ArrayList<>();
        levelList.add(new Level0());
        levelList.add(new Level1());
        levelList.add(new Level2());
        levelList.add(new Level3());
        levelList.add(new Level4());
        levelList.add(new Level5());
        levelList.add(new Level6());
        levelList.add(new Level7());
        levelList.add(new Level8());
        levelList.add(new Level9());
        levelList.add(new Level10());
        levelList.add(new Level11());
        View view = new View(new Model(levelList));

        root.addComponent(
                view
        );

        while (true) {
            Thread.sleep(10);

            if (!root.processInput()) {
                break;
            }
            root.draw();
        }
        
    }
}
