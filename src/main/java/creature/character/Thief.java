package creature.character;

import creature.Character;
import creature.Creature;
import weapon.Dagger;

public class Thief extends Character {
    boolean guard;

    public Thief(final String name, final int hp) {
        super(name,hp,new Dagger());
    }

    @Override
    public void attack(final Creature target) {
        System.out.println
                (this.getName() + "は素早く2回攻撃した！" + this.getWeapon().getName() +this.getWeapon().attackMessage() + target.getName() + "に" + this.getWeapon().getDamage() + "のダメージを与えた！");
        target.setHp(target.getHp() - this.getWeapon().getDamage() * 2);
    }
    public void guard() {
        guard = true;
    }

    @Override
    public void setHp(int hp) {
        if(guard) {
            guard = false;
            System.out.println("しかし、"+this.getName()+"は攻撃を回避し、ダメージが入らなかった！");
        } else {
            super.setHp(hp);
        }
    }
    public boolean isGuard() {
        return guard;
    }
    public void setGuard(boolean guard) {
        this.guard = guard;
    }
}
