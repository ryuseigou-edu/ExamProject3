package weapon;

public abstract class Weapon {
    String name;
    int damage;
    int cost = 0;

    Weapon(final String name, final int damage) {
        this.setName(name);
        this.setDamage(damage);
    }
    Weapon(final String name, final int damage, int cost) {
        this.setName(name);
        this.setDamage(damage);
        this.setCost(cost);
    }

    abstract public String attackMessage();

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
}
