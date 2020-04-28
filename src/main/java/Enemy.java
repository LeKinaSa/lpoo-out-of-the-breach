public abstract class Enemy extends GridElement {
    private Position position;
    private char symbol;
    private int hp;
    private AttackStrategy strategy;

    public Enemy(Position pos, char symbol, int hp, int damage) {
        super(pos);
        this.symbol = symbol;
        this.hp = hp;
        this.strategy = createAttackStrategy(damage);
    }

    protected abstract AttackStrategy createAttackStrategy(int damage);

    @Override
    public void draw() {}

    public void attack() {
        this.strategy.attack(this.position);
    }
}
