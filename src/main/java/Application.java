import com.googlecode.lanterna.TextColor;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello, World!");

        LanternaTerminal t = new LanternaTerminal(80, 40);

        while (true) {
            Thread.sleep(1000);
            System.out.println(t.terminalWasResized());
            System.out.println(t.getTerminalBuffer().getSize());
        }
    }
}
