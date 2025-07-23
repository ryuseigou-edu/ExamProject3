package creature;

public interface Creature {
    public boolean isAlive();
    public String showStatus();
    public String attack(final Creature target);
    public String getName();
    public int getHp();
    public void setHp(final int hp);
}
