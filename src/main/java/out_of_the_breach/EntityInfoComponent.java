package out_of_the_breach;

import out_of_the_breach.GUI.componentPosition.AbsComponentPosition;
import out_of_the_breach.GUI.ColorfulRectangle;
import out_of_the_breach.GUI.ScreenCorner;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import out_of_the_breach.model.*;

public class EntityInfoComponent extends ColorfulRectangle {
    private Entity selectedEntity;

    public EntityInfoComponent() {
        super(
                new TerminalSize(20, 4),
                new AbsComponentPosition(0, 4, ScreenCorner.BottomRight),
                new TextColor.RGB(0,59,92)
        );
        setEnabled(false);
    }

    @Override
    public void draw(TextGraphics buffer) {
        super.draw(buffer);

        buffer.setBackgroundColor(new TextColor.RGB(0,59,92));
        buffer.enableModifiers(SGR.BOLD);

        String name = selectedEntity.getName();

        buffer.putString(3, 1, name);
        buffer.putString(3, 2, "HP: " + new String(String.valueOf(selectedEntity.getHp())));
    }

    public void setSelectedEntity(Entity selectedEntity) {
        this.selectedEntity = selectedEntity;
    }
}
