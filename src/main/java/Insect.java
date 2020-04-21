public class Insect extends Enemy {
    public Insect(Position pos) {
        super(pos, 'I');
    }

    @Override
    protected AttackStrategy createAttackStrategy() {
        return new WindAttack();
    }
}
