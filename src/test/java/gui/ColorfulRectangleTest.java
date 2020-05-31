package gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.virtual.DefaultVirtualTerminal;
import org.junit.Before;
import org.junit.Test;
import out_of_the_breach.GUI.componentPosition.CenteredComponentPosition;
import out_of_the_breach.GUI.ColorfulRectangle;
import out_of_the_breach.GUI.GUIRoot;
import out_of_the_breach.GUI.LanternaTerminal;

import java.io.IOException;

import static org.junit.Assert.*;

public class ColorfulRectangleTest {
    private LanternaTerminal lterm;
    private Terminal term;

    @Before
    public void initBuffer() throws IOException {
        term = new DefaultVirtualTerminal(new TerminalSize(4, 4));
        lterm = new LanternaTerminal(term);
    }

    @Test
    public void testSelectableRectangle() throws IOException {
        ColorfulRectangle r = new ColorfulRectangle(
                new TerminalSize(2, 2),
                new CenteredComponentPosition(),
                new TextColor.RGB(0, 0, 0),
                true
        );

        assertTrue(r.isSelectable());
    }

    @Test
    public void testDraw() throws IOException {
        TextColor red = new TextColor.RGB(0, 255, 0);
        TextColor black = new TextColor.RGB(0, 0, 0);

        GUIRoot root = new GUIRoot(lterm, black);

        root.addComponent(
                new ColorfulRectangle(
                        new TerminalSize(2, 2),
                        new CenteredComponentPosition(),
                        red
                )
        );

        root.draw();

        TextGraphics buffer = lterm.getTerminalBuffer();
        assertEquals(buffer.getCharacter(1, 1).getBackgroundColor(), red);
        assertEquals(buffer.getCharacter(1, 2).getBackgroundColor(), red);
        assertEquals(buffer.getCharacter(2, 1).getBackgroundColor(), red);
        assertEquals(buffer.getCharacter(2, 2).getBackgroundColor(), red);

        assertEquals(buffer.getCharacter(0, 0).getBackgroundColor(), black);
        assertEquals(buffer.getCharacter(3, 3).getBackgroundColor(), black);
    }

    @Test
    public void testBoundary() throws IOException {
        TextColor red = new TextColor.RGB(0, 255, 0);
        TextColor black = new TextColor.RGB(0, 0, 0);

        GUIRoot root = new GUIRoot(lterm, black);

        root.addComponent(
                new ColorfulRectangle(
                        new TerminalSize(4, 4),
                        new CenteredComponentPosition(),
                        red
                ) {
                    @Override
                    public void draw(TextGraphics buffer) {
                        super.draw(buffer);
                        drawBorder(buffer, black);
                    }
                }
        );

        root.draw();

        TextGraphics buffer = lterm.getTerminalBuffer();
        assertEquals(buffer.getCharacter(1, 1).getBackgroundColor(), red);
        assertEquals(buffer.getCharacter(1, 2).getBackgroundColor(), red);
        assertEquals(buffer.getCharacter(2, 1).getBackgroundColor(), red);
        assertEquals(buffer.getCharacter(2, 2).getBackgroundColor(), red);

        assertEquals(buffer.getCharacter(0, 0).getBackgroundColor(), black);
        assertEquals(buffer.getCharacter(3, 3).getBackgroundColor(), black);
    }

    @Test
    public void testGettersAndSetters() {
        TextColor red = new TextColor.RGB(0, 255, 0);

        ColorfulRectangle r = new ColorfulRectangle(
                new TerminalSize(4, 4),
                new CenteredComponentPosition(),
                red
        );

        assertFalse(r.isSelectable());

        r.setSelectable(true);

        assertTrue(r.isEnabled());
        assertFalse(r.isSelected());

        r.setSelected(true);
        assertTrue(r.isSelected());

        r.setEnabled(false);
        assertFalse(r.isSelected());
        assertFalse(r.isSelectable());

        r.setEnabled(true);
        r.setSelectable(false);
        assertFalse(r.isSelected());

        assertEquals(r.getComponentSize(), new TerminalSize(4, 4));
        assertTrue(r.getPosition() instanceof CenteredComponentPosition);
    }
}
