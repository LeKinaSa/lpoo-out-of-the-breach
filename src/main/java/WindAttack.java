public class WindAttack implements AttackStrategy {
    private int damage;
    private int range;

    public WindAttack(int damage) {
        this.damage = damage;
        this.range = 8;
    }

    @Override
    public void attack(Position pos) {}
}
