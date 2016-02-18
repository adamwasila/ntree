package org.wasila.ntree_example.uiexample;

/**
 * TODO 17.02.16: description
 */
public enum IconType {
    BASEMENT("Basement"),
    BOX_FILLED("Box Filled"),
    EMPTY_BOX("Empty Box"),
    FLOOR("Floor Plan"),
    GARAGE("Garage"),
    HOME("Home"),
    ATTIC("Roofing"),
    WARDROBE("Wardrobe"),
    EMPTY_TRASH("Empty Trash"),
    FULL_TRASH("Full Trash"),
    GLOBE("Globe Filled"),
    PARTICLES("Particles");

    private final String iconName;

    IconType(String iconName) {
        this.iconName = iconName + "-50.png";
    }

    public String getIconName() {
        return iconName;
    }
}
