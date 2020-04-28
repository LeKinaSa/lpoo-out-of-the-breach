import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello, World!");

        LanternaTerminal t = new LanternaTerminal(80, 40);
        ColorfulRectangle r = new ColorfulRectangle();
        System.out.println(t.getTerminalBuffer());

        while (true) {
            Thread.sleep(100);
            System.out.println(t.terminalWasResized());
            System.out.println(t.getTerminalBuffer().getSize());

            t.clear();
            r.bondedDraw(t.getTerminalBuffer());
            t.refresh();
        }
    }
}
