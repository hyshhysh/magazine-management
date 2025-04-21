package magazine;

import java.io.Serializable;

/**
 * The Magazine class represents a magazine with a name and a weekly subscription cost.
 * This class allows for the creation, modification, and retrieval of magazine details.
 */
public class Magazine implements Serializable {

    /** The name of the magazine. */
    private String name;

    /** The weekly subscription cost of the magazine. */
    private double weeklyCost;

    /**
     * Constructs a Magazine object with the specified name and weekly cost.
     *
     * @param name The name of the magazine.
     * @param weeklyCost The weekly subscription cost of the magazine.
     */
    public Magazine(String name, double weeklyCost) {
        this.name = name;
        this.weeklyCost = weeklyCost;
    }

    /**
     * Retrieves the name of the magazine.
     *
     * @return The name of the magazine.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the magazine.
     *
     * @param name The new name of the magazine.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the weekly subscription cost of the magazine.
     *
     * @return The weekly cost of the magazine.
     */
    public double getWeeklyCost() {
        return weeklyCost;
    }

    /**
     * Sets the weekly subscription cost of the magazine.
     *
     * @param weeklyCost The new weekly subscription cost of the magazine.
     */
    public void setWeeklyCost(double weeklyCost) {
        this.weeklyCost = weeklyCost;
    }
}
