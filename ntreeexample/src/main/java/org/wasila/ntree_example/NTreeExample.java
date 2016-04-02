package org.wasila.ntree_example;

import org.wasila.ntree.*;
import org.wasila.ntree.builder.NodeNTreeBuilder;
import org.wasila.ntree.iterator.*;
import org.wasila.ntree.op.NTreeNodeConverter;
import org.wasila.ntree.op.Predicate;
import org.wasila.ntree_example.uiexample.IconType;

public class NTreeExample {

    public static NodeNTree<Element> createTreeNodesWithBuilder() {

        NodeNTreeBuilder<String> builder = new NodeNTreeBuilder<>();

        NodeNTree<String> stringTree = builder.add("House").withChildren()
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

        NodeNTree<Element> ntree = NTrees.transform(stringTree,
                new NTreeNodeConverter<Element,String>() {
                    @Override
                    public Element transform(NTreeNode<String> node) {
                        if (node.getChildrenCount()==0) {
                            return new Item(node.getData(), IconType.EMPTY_BOX);
                        } else {
                            return new Container(node.getData(), IconType.EMPTY_BOX);
                        }
                    }
                });

        return ntree;
    }

/*    public static NTree<Element> createTreeNodes() {
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
    }*/

    public static NodeNTree<Element> createTree() {
        return createTreeNodesWithBuilder();
    }

    public static void main(String[] args) {
        NodeNTree<Element> ntree = createTree();
        NTreeNode<Element> houseNode = ntree.getRoot();

        PathTreeIterator<NTreeNode<Element>> containersOnlyIterator = ntree.find(new Predicate<NTreeNode<Element>>() {
            private boolean result = false;

            ElementVisitor visitor = new ElementVisitor() {
                @Override
                public void visit(int level, Container container) {
                    result = true;
                }

                @Override
                public void visit(int level, Item item) {
                    result = false;
                }
            };

            @Override
            public boolean apply(NTreeNode<Element> node) {
                node.getData().accept(0, visitor);
                return result;
            }
        });

        while (containersOnlyIterator.hasNext()) {
            System.out.println(">>> " + containersOnlyIterator.next());
        }

        System.out.println("HOUSE: " + houseNode.getData().getId());

        recalcIds(ntree);
        System.out.println("HOUSE: " + houseNode.getData().getId());

        printTree(ntree);
    }

    private static void recalcIds(NodeNTree<Element> ntree) {
        PathTreeIterator<NTreeNode<Element>> idIterator = new PostOrderIterator<>(ntree);

        while (idIterator.hasNext()) {
            NTreePath<NTreeNode<Element>> path = idIterator.next();
            NTreeNode<Element> childNode = path.getLast();

            if (childNode.isLeaf()) {
                childNode.getData().setId(Integer.toHexString(childNode.getData().getName().hashCode()));
            } else {
                int hash = 0;
                for (NTreeNode<Element> iNode : childNode.getChildren()) {
                    hash += iNode.getData().getId().hashCode();
                }
                childNode.getData().setId(Integer.toHexString(hash));
            }
        }
    }

    private static void printTree(NodeNTree<Element> ntree) {
        PathTreeIterator<NTreeNode<Element>> it = new PreOrderIterator<>(ntree);
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
            it.next().getLast().getData().accept(it.getLevel(), visitor);
        }
    }


}
