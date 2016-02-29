package org.wasila.ntree;

public final class NTreeNodeWrapper<D> {

    private NTreeNode<D> internalNode;

    /* package */ NTreeNodeWrapper(D data, NTreeNode<D> internalNode) {
        this.internalNode = internalNode;
    }

    public D getData() {
        return internalNode.getData();
    }

    /* package */ NTreeNode<D> getTreeNode() {
        return internalNode;
    }

    public static <D> NTreeNodeWrapper<D> node(NTreeNodeImpl<D> parent, D data) {
        return new NTreeNodeWrapper<D>(data, new NTreeNodeImpl<D>(parent, data));
    }

}
