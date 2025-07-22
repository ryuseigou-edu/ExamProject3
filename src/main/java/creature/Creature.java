package creature;

public interface Creature {
    public boolean isAlive();
    public void showStatus();
    public void attack(final Creature target);
    public String getName();
    public int getHp();
    public void setHp(final int hp);
}
