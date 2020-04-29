import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
      
        /*Grid gameGrid = new Grid(8, 8);
        Enemy monster = new Insect(new Position(2, 2), 20, 5, 2);
        gameGrid.addSecondLayerElement(monster);
        System.out.println("Hello, World!");*/

        LanternaTerminal t = new LanternaTerminal(110, 40);
        GUIRoot root       = new GUIRoot(t, new TextColor.RGB(40, 40, 40));

        root.addComponent(
                new ColorfulRectangle(
                        new TerminalSize(40, 24),
                        new CenteredComponentPosition(),
                        new TextColor.RGB(118,85,43)
                )
        );

        root.addComponent(
                new PowerGridComponent()
        );

        root.addComponent(
                new EndTurnButton()
        );

        root.addComponent(
                new UndoMoveButton()
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
                new EnemyRoutedComponent()
        );

        while (true) {
            Thread.sleep(10);

            root.draw();
        }
        
    }
}
