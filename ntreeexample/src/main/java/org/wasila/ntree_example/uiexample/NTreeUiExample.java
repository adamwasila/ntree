package org.wasila.ntree_example.uiexample;

import org.wasila.ntree.NTree;
import org.wasila.ntree.NTreeNode;
import org.wasila.ntree.NTreePath;
import org.wasila.ntree.NTrees;
import org.wasila.ntree.builder.NTreeBuilder;
import org.wasila.ntree.impl.NTreePathImpl;
import org.wasila.ntree.op.NTreeNodeConverter;
import org.wasila.ntree_example.Container;
import org.wasila.ntree_example.Element;
import org.wasila.ntree_example.Item;

/**
 * TODO 25.01.16: description
 */
public class NTreeUiExample {

    NTreeUiExampleView view;

    private NTree<Element> ntree;

    private NTreePath<Element> treePath;

    public NTree<Element> createTreeNodesWithBuilder() {
        NTreeBuilder<String> builder = new NTreeBuilder<>();
        NTree<String> stringTree = builder
                .add("[World]").withChildren()
                    .add("[House]").withChildren()
                        .add("[Floor 1]")
                        .add("[Floor 2]")
                        .add("[Attic]").withChildren()
                            .add("[Bigbox]").asLastChild()
                        .add("[Basement]").withChildren()
                            .add("[red box 1]").withChildren()
                                .add("spoon")
                                .add("silver fork").asLastChild()
                            .add("[red box 2]").withChildren()
                                .add("gloves")
                                .add("blue scarf")
                                .add("white scarf")
                                .add("coat").asLastChild()
                            .add("[blue box 2]").asLastChild()
                    .asLastChild()
                    .add("[Garage]").asLastChild()
                .build();

        NTree<Element> ntree = NTrees.transform(stringTree,
                new NTreeNodeConverter<Element,String>() {
                    @Override
                    public Element transform(NTreeNode<String> node) {
                        String name = node.getData();
                        if (node.getChildrenCount() > 0 || (name.startsWith("[") && name.endsWith("]"))) {
                            IconType type = IconType.EMPTY_BOX;
                            if (name.equals("[House]")) {
                                type = IconType.HOME;
                            } else if (name.startsWith("[Floor")) {
                                type = IconType.FLOOR;
                            } else if (name.equals("[Attic]")) {
                                type = IconType.ATTIC;
                            } else if (name.equals("[Garage]")) {
                                type = IconType.GARAGE;
                            } else if (node.getChildrenCount() > 0) {
                                type = IconType.BOX_FILLED;
                            }
                            return new Container(node.getData().substring(1, node.getData().length()-1), type);
                        } else {
                            return new Item(node.getData(), IconType.EMPTY_BOX);
                        }
                    }
                });

        return ntree;
    }

    public NTreeUiExample(NTreeUiExampleView view) {
        this.view = view;
        this.view.setModel(this);
        ntree = createTreeNodesWithBuilder();
        treePath = new NTreePathImpl<>(ntree);
        treePath.enter(0);
    }

    public void start() {
        view.start();
        update();
    }

    public void update() {
        view.clearList();
        if (treePath.canLeave()) {
            view.addNode("..");
        }
        for (NTreeNode<Element> elementNode : treePath.getChildrenOfLastNode()) {
              view.addNode(elementNode.getData());
        }

    }

    public void selectExitNode() {
        treePath.leave();
        update();
    }

    public void selectNode(String nodeName) {
        for (NTreeNode<Element> elementNode : treePath.getChildrenOfLastNode()) {
            if (elementNode.getData() instanceof Container && elementNode.getData().toString().equals(nodeName)) {
                treePath.enter(treePath.getLastNode().indexOfNode(elementNode));
                update();
                break;
            }
        }
    }
}
