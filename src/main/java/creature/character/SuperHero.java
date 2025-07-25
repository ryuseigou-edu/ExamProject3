package creature.character;

import creature.Creature;
import weapon.Weapon;

public class SuperHero extends Hero {
    public SuperHero(final Hero hero) {
        super(hero.getName(), hero.getHp(), hero.getWeapon());

        this.setHp(this.getHp() - 30);
    }

    @Override
    public String attack(final Creature target) {
        target.setHp(target.getHp() - (int) (this.getWeapon().getDamage() * 2.5));
        return (getName() + "は" + this.getWeapon().getName() +this.getWeapon().attackMessage() + target.getName() + "に" + (int) (this.getWeapon().getDamage() * 2.5) + "のダメージを与えた！");
    }
}
