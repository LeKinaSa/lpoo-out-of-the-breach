public class Insect extends Enemy {
    public Insect(Position pos, int hp, int damage, int range) {
        super(pos, 'I', hp, damage, range);
    }

    @Override
    protected AttackStrategy createAttackStrategy(int damage, int range) {
        return new WindAttack(damage, range);
    }
}
