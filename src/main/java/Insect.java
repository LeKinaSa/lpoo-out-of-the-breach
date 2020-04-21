public class Insect extends Enemy {
    public Insect(Position pos, int hp, int damage) {
        super(pos, 'I', hp, damage);
    }

    @Override
    protected AttackStrategy createAttackStrategy(int damage) {
        return new WindAttack(damage);
    }
}
