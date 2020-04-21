public abstract class Enemy extends GridElement {
    private char symbol;
    private AttackStrategy strategy;

    public Enemy(Position pos, char symbol) {
        super(pos);
        this.symbol = symbol;
        this.strategy = createAttackStrategy();
    }

    protected abstract AttackStrategy createAttackStrategy();

    @Override
}
