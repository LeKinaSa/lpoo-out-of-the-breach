package out_of_the_breach.model;

/*
    Entity can be placed on the grid
    Can't be overlapped.
 */

public abstract class Entity {
    private Position pos;
    private int hp;

    public Entity(Position pos, int hp) {
        this.pos = pos;
        this.hp = 0;
        if (hp > 0) {
            this.hp = hp;
        }
    }

    public void setPosition(Position pos) {
        this.pos = pos;
    }

    public Position getPosition() {
        return this.pos;
    }

    public void setHp(int hp) {
        if (hp > 0) {
            this.hp = hp;
        }
    }

    public int getHp() {
        return this.hp;
    }

    /*
        Make the entity take damage losing hp.
     */
    public void takeDamage(int damage) {
        if (damage > 0){
            this.hp -= damage;
            if (this.hp <= 0) {
                this.hp = 0;
            }
        }
    }

    /*
        Verify if the entity is dead.
     */
    public boolean isDead() {
        return this.hp == 0;
    }

    /*
        Obtain the name in order to display the entity.
     */
    public abstract String getName();

    /*
        Obtain a shorter name, all in capital letters, in order to quickly distinguish the entities on screen.
     */
    public abstract String getInitials();
}
