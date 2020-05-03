package gui;

import GUI.AbsComponentPosition;
import GUI.ScreenCorner;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AbsComponentPositionTest {
    private TextGraphics buffer;

    @Before
    public void initBuffer() {
        new Mockito();
        buffer = Mockito.mock(TextGraphics.class);
        Mockito.when(buffer.getSize()).thenReturn(new TerminalSize(10, 10));
    }

    @Test
    public void testTopLeft() {
        AbsComponentPosition abs = new AbsComponentPosition(1, 3, ScreenCorner.TopLeft);
        TerminalSize boxSize = new TerminalSize(3, 3);

        TerminalPosition res = new TerminalPosition(1, 3);

        assertEquals(abs.getActualOffset(buffer, boxSize), res);
    }

    @Test
    public void testTopRight() {
        AbsComponentPosition abs = new AbsComponentPosition(1, 3, ScreenCorner.TopRight);
        TerminalSize boxSize = new TerminalSize(3, 3);

        TerminalPosition res = new TerminalPosition(6, 3);

        assertEquals(abs.getActualOffset(buffer, boxSize), res);
    }

    @Test
    public void testBottomLeft() {
        AbsComponentPosition abs = new AbsComponentPosition(1, 3, ScreenCorner.BottomLeft);
        TerminalSize boxSize = new TerminalSize(3, 3);

        TerminalPosition res = new TerminalPosition(1, 4);

        assertEquals(abs.getActualOffset(buffer, boxSize), res);
    }

    @Test
    public void testBottomRight() {
        AbsComponentPosition abs = new AbsComponentPosition(1, 3, ScreenCorner.BottomRight);
        TerminalSize boxSize = new TerminalSize(3, 3);

        TerminalPosition res = new TerminalPosition(6, 4);

        assertEquals(abs.getActualOffset(buffer, boxSize), res);
    }
}