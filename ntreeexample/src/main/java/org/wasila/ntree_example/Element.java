package org.wasila.ntree_example;

import org.wasila.ntree_example.uiexample.IconType;

/**
 * Created by adam on 02.11.15.
 */
public abstract class Element {

    private String id;

    private final String name;

    private final IconType icon;

    public Element(String id, String name, IconType icon) {
        this.name = name;
        this.icon = icon;
    }

    public Element(String name, IconType icon) {
        this("", name, icon);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public IconType getIcon() {
        return icon;
    }

    public void setId(String id) {
        this.id = id;
    }

    public abstract void accept(int level, ElementVisitor visitor);

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
