public class Lizard extends Enemy {
    public Lizard(Position pos, int hp, int damage) {
        super(pos, 'L', hp, damage);
    }
    @Override
    protected AttackStrategy createAttackStrategy(int damage) {
        return new TailAttack(2);
    }
}
