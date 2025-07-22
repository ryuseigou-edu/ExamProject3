package creature.monster;

import creature.Creature;
import creature.Monster;

public class Matango extends Monster {
    public Matango(final int hp, final char suffix) {
        super("お化けキノコ", hp, suffix);
    }

    @Override
    public void attack(Creature target) {
        System.out.println
                (this.getName() + this.getSuffix() + "は体当たり攻撃！" + target.getName() + "に6のダメージを与えた！");
        target.setHp(target.getHp() - 6);
    }
}
