package org.wasila.ntree_example;

import org.wasila.ntree_example.uiexample.IconType;

/**
 * Created by adam on 02.11.15.
 */
public class Container extends Element {

    public Container(String id, String name, IconType icon) {
        super(id, name, icon);
    }

    public Container(String name, IconType icon) {
        super(name, icon);
    }

    @Override
    public void accept(int level, ElementVisitor visitor) {
        visitor.visit(level, this);
    }

    @Override
    public String toString() {
        return "[" + getName() + "]";
    }
}
