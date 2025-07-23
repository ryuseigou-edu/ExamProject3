package creature.monster;

import creature.Creature;
import creature.Monster;

public class Goblin extends Monster {
    public Goblin(final int hp, final char suffix) {
        super("ゴブリン", hp, suffix);
    }

    @Override
    public String attack(Creature target) {
        target.setHp(target.getHp() - 8);
        return (this.getName() + this.getSuffix() + "はナイフで切り付けた！" + target.getName() + "に8のダメージを与えた！");
    }
}
