package creature.character;

import creature.Character;
import creature.Creature;
import weapon.Wand;

public class Wizard extends Character {
    private int mp;

    public Wizard(final String name, final int hp, final int mp) {
        super(name,hp,new Wand());
        setMp(mp);
    }

    @Override
    public String attack(final Creature target) {
        target.setHp(target.getHp() - 3);
        return (this.getName() + "は石を投げた！" + target.getName() + "に3のダメージを与えた！");
    }
    public String magic(final Creature target) {
        if(this.getMp() >= this.getWeapon().getCost()) {
            this.setMp(this.getMp() - this.getWeapon().getCost());
            target.setHp(target.getHp() - this.getWeapon().getDamage());
            return (getName() + "は" + this.getWeapon().getName() +this.getWeapon().attackMessage() + target.getName() + "に" + this.getWeapon().getDamage() + "のダメージを与えた！");
        } else {
            return ("MPが足りない！");
        }
    }

    @Override
    public String showStatus(){
        return this.getName() + "：HP " + this.getHp() + " / MP " + this.getMp();
    }
    public int getMp() {
        return this.mp;
    }
    public void setMp(final int mp) {
        this.mp = mp;
    }
}
