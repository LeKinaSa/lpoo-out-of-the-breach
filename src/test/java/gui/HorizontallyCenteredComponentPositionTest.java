package gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import out_of_the_breach.GUI.componentPosition.CenteredComponentPosition;
import out_of_the_breach.GUI.componentPosition.HorizontallyCenteredComponentPosition;
import out_of_the_breach.GUI.componentPosition.iGUIcomponentPosition;

import static org.junit.Assert.assertEquals;

public class HorizontallyCenteredComponentPositionTest {
    private TextGraphics buffer;

    @Before
    public void initBuffer() {
        new Mockito();
        buffer = Mockito.mock(TextGraphics.class);
        Mockito.when(buffer.getSize()).thenReturn(new TerminalSize(10, 10));
    }

    @Test
    public void testCenter() {
        iGUIcomponentPosition c = new HorizontallyCenteredComponentPosition(0);
        TerminalSize boxSize = new TerminalSize(4, 4);

        TerminalPosition res = new TerminalPosition(3, 0);

        assertEquals(c.getActualOffset(buffer, boxSize), res);
    }
}