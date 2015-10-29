package org.wasila.ntree_example;

/**
 * Created by adam on 02.11.15.
 */
public interface ElementVisitor {

    void visit(int level, Container container);

    void visit(int level, Item item);

}
