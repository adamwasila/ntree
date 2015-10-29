package org.wasila.ntree_example;

/**
 * Created by adam on 02.11.15.
 */
public class Container extends Element {

    public Container(String id, String name) {
        super(id, name);
    }

    public Container(String name) {
        super(name);
    }

    @Override
    public void accept(int level, ElementVisitor visitor) {
        visitor.visit(level, this);
    }

}
