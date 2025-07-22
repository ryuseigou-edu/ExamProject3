package creature.character;

import creature.Character;
import creature.Creature;
import weapon.Sword;
import weapon.Weapon;

public class Hero extends Character {
    public Hero(final String name, final int hp) {
        super(name, hp, new Sword());
    }
    public Hero(final String name, final int hp, final Weapon weapon){
        super(name, hp, weapon);
    }

    @Override
    public void attack(final Creature target) {
        System.out.println(getName() + "は" + this.getWeapon().getName() +this.getWeapon().attackMessage() + target.getName() + "に" + this.getWeapon().getDamage() + "のダメージを与えた！");
        target.setHp(target.getHp() - this.getWeapon().getDamage());
    }
}
