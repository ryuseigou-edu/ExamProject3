package creature.monster;

import creature.Creature;
import creature.Monster;

public class Goblin extends Monster {
    public Goblin(final int hp, final char suffix) {
        super("ゴブリン", hp, suffix);
    }

    @Override
    public void attack(Creature target) {
        System.out.println
                (this.getName() + this.getSuffix() + "はナイフで切り付けた！" + target.getName() + "に8のダメージを与えた！");
        target.setHp(target.getHp() - 8);
    }
}
