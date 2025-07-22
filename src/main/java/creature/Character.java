package creature;

import weapon.Weapon;

public abstract class Character implements Creature {
    private final String name;
    private int hp;
    private final Weapon weapon;
    public Character(final String name, final int hp, final Weapon weapon) {
        this.name = name;
        if(hp >= 0) {
            this.setHp(hp);
        } else {
            throw new IllegalArgumentException
                    ("初期設定に誤りがあるため、キャラクターを作成できませんでした");
        }
        this.weapon = weapon;
    }

    @Override
    public final boolean isAlive(){
        return getHp() > 0;
    }
    @Override
    public void showStatus(){
        System.out.println
                (this.getName() + "：HP " + this.getHp());
    }
    public void die(){
        System.out.println
                (this.getName() + "は死んでしまった！");
    }

    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public int getHp() {
        return this.hp;
    }
    @Override
    public void setHp(int hp) {
        this.hp = Math.max(hp, 0);
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
