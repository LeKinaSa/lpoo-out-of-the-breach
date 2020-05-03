package gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.virtual.DefaultVirtualTerminal;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


import static org.junit.Assert.assertEquals;

public class LanternaTerminalTest {
    private Terminal term;

    @Before
    public void initBuffer() {
        new Mockito();
        term = new DefaultVirtualTerminal();
    }

    @Test
    public void test() {
        assertEquals(1, 1);
    }
}
