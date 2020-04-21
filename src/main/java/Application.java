public class Application {
    public static void main(String[] args) {
        Grid gameGrid = new Grid(8, 8);
        Enemy monster = new Insect(new Position(2, 2), 20, 5);
        gameGrid.addSecondLayerElement(monster);
        System.out.println("Hello, World!");
    }
}
