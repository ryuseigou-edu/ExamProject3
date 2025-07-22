package creature.character;

import creature.Creature;
import weapon.Weapon;

public class SuperHero extends Hero {
    public SuperHero(final Hero hero) {
        super(hero.getName(), hero.getHp(), hero.getWeapon());

        this.setHp(this.getHp() - 30);
        System.out.println("なんか勇者が突然光だした！");
        System.out.println("勇者は30ダメージを受けてスーパーヒーローに進化した！");
    }

    @Override
    public void attack(final Creature target) {
        System.out.println(getName() + "は" + this.getWeapon().getName() +this.getWeapon().attackMessage() + target.getName() + "に" + (int) (this.getWeapon().getDamage() * 2.5) + "のダメージを与えた！");
        target.setHp(target.getHp() - (int) (this.getWeapon().getDamage() * 2.5));
    }
}
