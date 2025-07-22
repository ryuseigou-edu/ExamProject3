package creature;

public abstract class Monster implements Creature {
    private String name;
    private int hp;
    private final char suffix;

    public Monster(final String name, final int hp, final char suffix) {
        setName(name);
        if(hp >= 0) {
            setHp(hp);
        } else {
            throw new IllegalArgumentException
                    ("初期設定に誤りがあるため、キャラクターを作成できませんでした");
        }
        this.suffix = suffix;
    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }
    @Override
    public void showStatus() {
        System.out.println
                (this.getName() + this.getSuffix() + "：HP " + this.getHp());
    }
    public void run() {
        System.out.println(this.getName() + this.getSuffix() + "は逃げ出した");
    }
    public void die() {
        System.out.println(this.getName() + this.getSuffix() + "を倒した！");
    }

    @Override
    public int getHp() {
        return this.hp;
    }
    @Override
    public String getName() {
        return this.name;
    }
    public char getSuffix() {
        return this.suffix;
    }
    public void setName(final String name) {
        this.name = name;
    }
    @Override
    public void setHp(final int hp) {
        this.hp = Math.max(hp, 0);
    }
}
