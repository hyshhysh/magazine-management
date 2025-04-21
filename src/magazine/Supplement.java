package magazine;

import java.io.Serializable;

/**
 * The Supplement class represents an additional publication or feature that can be
 * added to a magazine subscription. Each supplement has a name and a weekly subscription cost.
 */
public class Supplement implements Serializable {

    /** The name of the supplement. */
    private String name;

    /** The weekly subscription cost of the supplement. */
    private double weeklyCost;

    /**
     * Constructs a Supplement object with the specified name and weekly cost.
     *
     * @param name The name of the supplement.
     * @param weeklyCost The weekly subscription cost of the supplement.
     */
    public Supplement(String name, double weeklyCost) {
        this.name = name;
        this.weeklyCost = weeklyCost;
    }

    /**
     * Retrieves the name of the supplement.
     *
     * @return The name of the supplement.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the supplement.
     *
     * @param name The new name of the supplement.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the weekly subscription cost of the supplement.
     *
     * @return The weekly cost of the supplement.
     */
    public double getWeeklyCost() {
        return weeklyCost;
    }

    /**
     * Sets the weekly subscription cost of the supplement.
     *
     * @param weeklyCost The new weekly subscription cost of the supplement.
     */
    public void setWeeklyCost(double weeklyCost) {
        this.weeklyCost = weeklyCost;
    }
}
