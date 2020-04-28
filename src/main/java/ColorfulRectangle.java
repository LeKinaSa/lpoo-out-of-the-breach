import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class ColorfulRectangle extends GUIcomponent {

    public ColorfulRectangle() {
        super(new TerminalSize(10, 10), new CenteredComponentPosition());
    }

    @Override
    public void draw(TextGraphics buffer) {
        //System.out.println("Colorful");
        //System.out.println(buffer.getSize());
        //buffer.fillRectangle(new TerminalPosition(0, 0), buffer.getSize(), 'X');
        buffer.drawRectangle(new TerminalPosition(0, 0), buffer.getSize(), 'X');
        buffer.fillRectangle(new TerminalPosition(0, 0), buffer.getSize(), new TextCharacter('A', new TextColor.RGB(128, 128, 128), new TextColor.RGB(128, 128, 128)));
    }
}
