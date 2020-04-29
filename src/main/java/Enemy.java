public abstract class Enemy extends GridElement {
    private Position position;
    private char symbol;
    private int hp;
    private AttackStrategy strategy;

    public Enemy(Position pos, char symbol, int hp, int damage, int range) {
        super(pos);
        this.symbol = symbol;
        this.hp = hp;
        this.strategy = createAttackStrategy(damage, range);
    }

    protected abstract AttackStrategy createAttackStrategy(int damage, int range);

    @Override
    public void draw() {}

    public void attack(Grid grid) {
        this.strategy.attack(grid, this.position);
    }

    @Override
    public void takeDamage(int damage) {
        this.hp = this.hp - damage;
        if (this.hp < 0) {
            this.hp = 0;
        }
    }

    @Override
    public boolean isDead() {
        return this.hp == 0;
    }
}
