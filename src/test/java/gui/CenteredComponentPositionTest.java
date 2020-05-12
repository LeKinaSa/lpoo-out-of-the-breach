package gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import out_of_the_breach.GUI.AbsComponentPosition;
import out_of_the_breach.GUI.CenteredComponentPosition;
import out_of_the_breach.GUI.ScreenCorner;

import static org.junit.Assert.assertEquals;

public class CenteredComponentPositionTest {
    private TextGraphics buffer;

    @Before
    public void initBuffer() {
        new Mockito();
        buffer = Mockito.mock(TextGraphics.class);
        Mockito.when(buffer.getSize()).thenReturn(new TerminalSize(10, 10));
    }

    @Test
    public void testCenter() {
        CenteredComponentPosition c = new CenteredComponentPosition();
        TerminalSize boxSize = new TerminalSize(4, 4);

        TerminalPosition res = new TerminalPosition(3, 3);

        assertEquals(c.getActualOffset(buffer, boxSize), res);
    }
}