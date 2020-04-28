import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {

        LanternaTerminal t = new LanternaTerminal(110, 40);
        GUIRoot root       = new GUIRoot(t);

        root.addComponent(
                new ColorfulRectangle(
                        new TerminalSize(40, 24),
                        new CenteredComponentPosition(),
                        new TextColor.RGB(118,85,43)
                )
        );

        root.addComponent(
                new ColorfulRectangle(
                        new TerminalSize(40, 2),
                        new AbsComponentPosition(0, 0, ScreenCorner.TopLeft),
                        new TextColor.RGB(0,6,177)
                )
        );

        root.addComponent(
                new ColorfulRectangle(
                        new TerminalSize(15, 3),
                        new AbsComponentPosition(0, 3, ScreenCorner.TopLeft),
                        new TextColor.RGB(0,6,177)
                )
        );

        root.addComponent(
                new ColorfulRectangle(
                        new TerminalSize(8, 3),
                        new AbsComponentPosition(17, 3, ScreenCorner.TopLeft),
                        new TextColor.RGB(0,6,177)
                )
        );

        root.addComponent(
                new ColorfulRectangle(
                        new TerminalSize(15, 15),
                        new AbsComponentPosition(0, 7, ScreenCorner.TopLeft),
                        new TextColor.RGB(0,6,177)
                )
        );

        root.addComponent(
                new ColorfulRectangle(
                        new TerminalSize(50, 7),
                        new AbsComponentPosition(0, 0, ScreenCorner.BottomLeft),
                        new TextColor.RGB(0,6,177)
                )
        );

        root.addComponent(
                new ColorfulRectangle(
                        new TerminalSize(40, 7),
                        new AbsComponentPosition(0, 0, ScreenCorner.BottomRight),
                        new TextColor.RGB(0,6,177)
                )
        );

        root.addComponent(
                new ColorfulRectangle(
                        new TerminalSize(30, 7),
                        new AbsComponentPosition(0, 4, ScreenCorner.TopRight),
                        new TextColor.RGB(0,6,177)
                )
        );

        root.addComponent(
                new ColorfulRectangle(
                        new TerminalSize(30, 3),
                        new AbsComponentPosition(0, 0, ScreenCorner.TopRight),
                        new TextColor.RGB(0,6,177)
                )
        );

        while (true) {
            Thread.sleep(10);

            root.draw();
        }
    }
}
