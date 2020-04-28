import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.List;

public class GUIparentNode implements iGUIcomponent {
    private List<iGUIcomponent> components;


    public void addComponent(iGUIcomponent component) {
        components.add(component);
    }

    @Override
    public void draw(TextGraphics buffer) {

    }
}
