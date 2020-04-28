public class TailAttack implements AttackStrategy {
    private int damage;
    private int range;

    public TailAttack(int range) {
        this.damage = 3;
        this.range = range;
    }

    @Override
    public void attack(Position pos) {}
}
