package org.wasila.ntree_example;

import org.wasila.ntree_example.uiexample.IconType;

/**
 * Created by adam on 02.11.15.
 */
public class Item extends Element {

    public Item(String id, String name, IconType icon) {
        super(id, name, icon);
    }

    public Item(String name, IconType icon) {
        super(name, icon);
    }

    @Override
    public void accept(int level, ElementVisitor visitor) {
        visitor.visit(level, this);
    }

    @Override
    public String toString() {
        return getName();
    }

}
