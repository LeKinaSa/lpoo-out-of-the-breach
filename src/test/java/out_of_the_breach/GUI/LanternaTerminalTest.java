package out_of_the_breach.GUI;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.virtual.DefaultVirtualTerminal;
import org.junit.Before;
import org.junit.Test;


import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LanternaTerminalTest {
    private LanternaTerminal lterm;
    private Terminal term;

    @Before
    public void initBuffer() throws IOException {
        term = new DefaultVirtualTerminal();
        lterm = new LanternaTerminal(term);
    }

    @Test
    public void testNullKeystroke() throws IOException {
        assertNull(lterm.pollInput()); // Would be great if we could inject keystrokes:/
    }

    @Test
    public void testBuffer() throws IOException {
        lterm.clear();
        TextGraphics buffer = lterm.getTerminalBuffer();
        buffer.setCharacter(0, 0, 'c');
        lterm.refresh();

        buffer = lterm.getTerminalBuffer();
        assertEquals(buffer.getCharacter(0, 0).getCharacter(), 'c');
    }
}
