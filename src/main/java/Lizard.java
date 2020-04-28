public class Lizard extends Enemy {
    public Lizard(Position pos, int hp, int damage, int range) {
        super(pos, 'L', hp, damage, range);
    }

    @Override
    protected AttackStrategy createAttackStrategy(int damage, int range) {
        return new TailAttack(damage, range);
    }
}
