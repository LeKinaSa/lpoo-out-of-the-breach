package GUITests;

import GUI.ScreenCorner;
import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.StyleSet;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.screen.TabBehaviour;
import org.junit.Before;
import org.junit.Test;
import GUI.AbsComponentPosition;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.EnumSet;

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
        //assertEquals();
    }

}
