package org.wasila.ntree_example;

import org.wasila.ntree.NTree;
import org.wasila.ntree.NTreeNode;
import org.wasila.ntree.NTreePath;
import org.wasila.ntree.NTrees;
import org.wasila.ntree.builder.NTreeBuilder;
import org.wasila.ntree.impl.NTreeImpl;
import org.wasila.ntree.iterator.*;
import org.wasila.ntree.op.NTreeNodeConverter;
import org.wasila.ntree.op.Predicate;

public class NTreeExample {

    public static NTree<Element> createTreeNodesWithBuilder() {

        NTreeBuilder<String> builder = new NTreeBuilder<>();

        NTree<String> stringTree = builder.add("House").withChildren()
                .add("Floor 1")
                .add("Floor 2")
                .add("Attic").withChildren()
                   .add("Bigbox").asLastChild()
                .add("Basement").withChildren()
                   .add("red box 1").withChildren()
                        .add("spoon")
                        .add("silver fork").asLastChild()
                   .add("red box 2").withChildren()
                        .add("gloves")
                        .add("blue scarf")
                        .add("white scarf")
                        .add("coat").asLastChild()
                   .add("blue box 2")
                .build();

        NTree<Element> ntree = NTrees.transform(stringTree,
                new NTreeNodeConverter<Element,String>() {
                    @Override
                    public Element transform(NTreeNode<String> node) {
                        return new Item(node.getData());
                    }
                });

        return ntree;
    }

    public static NTree<Element> createTreeNodes() {
        NTree<Element> ntree = new NTreeImpl<>();

        NTreeNode<Element> houseNode = ntree.setRoot(new Container("House"));

        NTreeNode<Element> floor1 = houseNode.addChild(new Container("Floor 1"));
        NTreeNode<Element> floor2 = houseNode.addChild(new Container("Floor 2"));
        NTreeNode<Element> attic = houseNode.addChild(new Container("Attic"));
        NTreeNode<Element> basement = houseNode.addChild(new Container("Basement"));

        NTreeNode<Element> redBox1 = basement.addChild(new Container("red box 1"));
        NTreeNode<Element> redBox2 = basement.addChild(new Container("red box 2"));
        NTreeNode<Element> blueBox2 = basement.addChild(new Container("blue box 2"));

        NTreeNode<Element> bigBox = attic.addChild(new Container("Bigbox"));

        redBox1.addChild(new Item("spoon"));
        redBox1.addChild(new Item("silver fork"));

        redBox2.addChild(new Item("gloves"));
        redBox2.addChild(new Item("blue scarf"));
        redBox2.addChild(new Item("white scarf"));
        redBox2.addChild(new Item("coat"));

        blueBox2.addChild(new Item("gold coin stack"));

        NTreeNode<Element> smallYellowBox = bigBox.addChild(new Container("Small yellow box"));
        NTreeNode<Element> smallGreenBox = bigBox.addChild(new Container("Small green box"));
        NTreeNode<Element> smallRedBox = bigBox.addChild(new Container("Small red box"));

        smallYellowBox.addChild(new Item("Gold button"));
        smallYellowBox.addChild(new Item("Blue button"));

        smallGreenBox.addChild(new Item("Old family photos"));
        smallGreenBox.addChild(new Item("Letters"));

        smallRedBox.addChild(new Item("Fuses"));
        smallRedBox.addChild(new Item("Wires"));
        smallRedBox.addChild(new Item("Solder iron"));

        return ntree;
    }

    public static NTree<Element> createTree() {
        return createTreeNodesWithBuilder();
    }

    public static void main(String[] args) {
        NTree<Element> ntree = createTree();
        NTreeNode<Element> houseNode = ntree.getRootNode();

        System.out.println("HOUSE: " + houseNode.getData().getId());

        recalcIds(ntree);
        System.out.println("HOUSE: " + houseNode.getData().getId());

        printTree(ntree);
    }

    private static void recalcIds(NTree<Element> ntree) {
        PathTreeIterator<Element> idIterator = new PostOrderIterator<>(ntree);

        while (idIterator.hasNext()) {
            NTreePath<Element> path = idIterator.next();
            NTreeNode<Element> childNode = path.getLastNode();

            if (childNode.isLeaf()) {
                childNode.getData().setId(Integer.toHexString(childNode.getData().getName().hashCode()));
            } else {
                int hash = 0;
                for (NTreeNode<Element> iNode : childNode.getChildrenNode()) {
                    hash += iNode.getData().getId().hashCode();
                }
                childNode.getData().setId(Integer.toHexString(hash));
            }
        }
    }

    private static void printTree(NTree<Element> ntree) {
        PathTreeIterator<Element> it = new PreOrderIterator<>(ntree);
        ElementVisitor visitor = new ElementVisitor() {
            @Override
            public void visit(int level, Container container) {
                for (int i=0; i<level-1; i++) System.out.print(" ");
                System.out.print("- ");
                System.out.println("Visiting: " + container);
            }

            @Override
            public void visit(int level, Item item) {
                for (int i=0; i<level-1; i++) System.out.print(" ");
                System.out.print("- ");
                System.out.println("Visiting: " + item);
            }
        };
        while (it.hasNext()) {
            it.next().getLast().accept(it.getLevel(), visitor);
        }
    }


}
