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

    enum ElementType {
        CONTAINER,
        ITEM
    }

    private ElementData data(IconType type, String value) {
        return data(type, ElementType.CONTAINER, value);
    }

    private ElementData data(IconType iconType, ElementType elementType, String value) {
        return new ElementData(iconType, elementType, value);
    }

    static class ElementData {
        public final IconType iconType;
        public final ElementType elementType;
        public final String name;

        public ElementData(IconType iconType, ElementType elementType, String name) {
            this.iconType = iconType;
            this.elementType = elementType;
            this.name = name;
        }
    }

    public NTree<Element> createTreeNodesWithBuilder() {
        NTreeBuilder<ElementData> builder = new NTreeBuilder<>();
        NTree<ElementData> stringTree = builder
                .add(data(IconType.GLOBE, "World")).withChildren()
                    .add(data(IconType.HOME, "House")).withChildren()
                        .add(data(IconType.FLOOR, "Floor 1")).withChildren()
                            .add(data(IconType.WARDROBE, "Wardrobe")).withChildren()
                                .add(data(IconType.EMPTY_BOX, "Blue box")).asLastChild()
                            .asLastChild()
                        .add(data(IconType.FLOOR, "Floor 2"))
                        .add(data(IconType.ATTIC, "Attic")).withChildren()
                            .add(data(IconType.EMPTY_BOX, "Bigbox")).asLastChild()
                        .add(data(IconType.BASEMENT, "Basement")).withChildren()
                            .add(data(IconType.BOX_FILLED, "red box 1")).withChildren()
                                .add(data(IconType.PARTICLES, ElementType.ITEM, "spoon"))
                                .add(data(IconType.PARTICLES, ElementType.ITEM, "silver fork")).asLastChild()
                            .add(data(IconType.BOX_FILLED, "red box 2")).withChildren()
                                .add(data(IconType.PARTICLES, ElementType.ITEM, "gloves"))
                                .add(data(IconType.PARTICLES, ElementType.ITEM, "blue scarf"))
                                .add(data(IconType.PARTICLES, ElementType.ITEM, "white scarf"))
                                .add(data(IconType.PARTICLES, ElementType.ITEM, "coat")).asLastChild()
                            .add(data(IconType.EMPTY_BOX, "blue box 2"))
                            .add(data(IconType.FULL_TRASH, "Trash")).withChildren()
                                .add(data(IconType.PARTICLES, ElementType.ITEM, "button"))
                                .add(data(IconType.PARTICLES, ElementType.ITEM, "needle")).asLastChild()
                            .asLastChild()
                    .asLastChild()
                    .add(data(IconType.GARAGE, "Garage"))
                    .add(data(IconType.EMPTY_TRASH, "Trash")).asLastChild()
                .build();

        NTree<Element> ntree = NTrees.transform(stringTree,
                new NTreeNodeConverter<Element, ElementData>() {
                    @Override
                    public Element transform(NTreeNode<ElementData> node) {
                        ElementData data = node.getData();
                        switch (data.elementType) {
                            case CONTAINER:
                                return new Container(data.name, data.iconType);
                            case ITEM:
                                return new Item(data.name, data.iconType);
                        }
                        return null;
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
        for (NTreeNode<Element> elementNode : treePath.getChildrenOfLast()) {
              view.addNode(elementNode.getData());
        }

    }

    public void selectExitNode() {
        treePath.leave();
        update();
    }

    public void selectNode(String nodeName) {
        for (NTreeNode<Element> elementNode : treePath.getChildrenOfLast()) {
            if (elementNode.getData() instanceof Container && elementNode.getData().toString().equals(nodeName)) {
                treePath.enter(treePath.getLastNode().indexOfNode(elementNode));
                update();
                break;
            }
        }
    }
}
