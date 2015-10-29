package org.wasila.ntree_example;

/**
 * Created by adam on 02.11.15.
 */
public abstract class Element {

    private String id;

    private final String name;

    public Element(String id, String name) {
        this.name = name;
    }

    public Element(String name) {
        this("", name);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public abstract void accept(int level, ElementVisitor visitor);

    @Override
    public String toString() {
        return "Element{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
