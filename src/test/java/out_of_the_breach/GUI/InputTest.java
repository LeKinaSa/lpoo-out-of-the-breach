package out_of_the_breach.GUI;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.Test;
import org.mockito.Mockito;
import out_of_the_breach.GUI.componentSelectionStrategy.ParentNodeSelectionStrategy;
import out_of_the_breach.GUI.componentSelectionStrategy.RootSelectionStrategy;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class InputTest {
    @Test
    public void testInput() throws IOException {
        LanternaTerminal lterm = Mockito.mock(LanternaTerminal.class);
        GUIRoot root = new GUIRoot(lterm, new TextColor.RGB(0, 0, 0));

        Mockito.when(lterm.pollInput()).thenReturn(null);
        assertTrue(root.processInput());

        Mockito.when(lterm.pollInput()).thenReturn(new KeyStroke(KeyType.EOF));
        assertFalse(root.processInput());

        Mockito.when(lterm.pollInput()).thenReturn(new KeyStroke('K', false, false, false));
        assertTrue(root.processInput());
    }

    @Test
    public void testParentSelectionStrategy() {
        ParentNodeSelectionStrategy p = new ParentNodeSelectionStrategy();
        List<GUIcomponent> components = new ArrayList<>();
        components.add(
                new ColorfulRectangle(null, null, new TextColor.RGB(0, 0, 0), true)
        );

        components.add(
                new ColorfulRectangle(null, null, new TextColor.RGB(0, 0, 0), true)
        );

        assertEquals(p.getSelectedComponent(), 0);

        assertFalse(p.processKeystroke(new KeyStroke(KeyType.ArrowLeft), components));
        assertEquals(p.getSelectedComponent(), 0);

        assertFalse(p.processKeystroke(new KeyStroke(KeyType.ArrowLeft), components));
        assertEquals(p.getSelectedComponent(), 0);
    }

    @Test
    public void testRootSelectionStrategy() {
        RootSelectionStrategy r = new RootSelectionStrategy();
        List<GUIcomponent> components = new ArrayList<>();
        components.add(
                new ColorfulRectangle(null, null, new TextColor.RGB(0, 0, 0), true)
        );

        components.add(
                new ColorfulRectangle(null, null, new TextColor.RGB(0, 0, 0), true)
        );

        assertEquals(r.getSelectedComponent(), 0);

        assertTrue(r.processKeystroke(new KeyStroke(KeyType.ArrowLeft), components));
        assertEquals(r.getSelectedComponent(), 1);

        assertTrue(r.processKeystroke(new KeyStroke(KeyType.ArrowLeft), components));
        assertEquals(r.getSelectedComponent(), 0);
    }
}
