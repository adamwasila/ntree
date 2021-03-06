package org.wasila.ntree;


import org.wasila.ntree.impl.UnmodifableNTreeImpl;
import org.wasila.ntree.iterator.PreOrderIterator;
import org.wasila.ntree.op.NTreeNodeConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * NTrees
 */
public class NTrees {

    public static <D,S> NodeNTree<D> transform(NodeNTree<S> sourceTree, NTreeNodeConverter<D,S> converter) {
        if (sourceTree.getRoot() == null) {
            return new DataNTree<>();
        } else {
            DataNTree<D> destTree = new DataNTree<D>(converter.transform(sourceTree.getRoot()));
            PreOrderIterator<NTreeNode<S>> it = new PreOrderIterator<>(sourceTree);
            Map<NTreeNode<S>,NTreeNode<D>> nodeMap = new HashMap<>();
            nodeMap.put(sourceTree.getRoot(), destTree.getRoot());
            while (it.hasNext()) {
                NTreeNode<S> nextNode = it.next().getLast();
                NTreeNode<D> nextDestNode = nodeMap.get(nextNode);

                for (NTreeNode<S> childNode : nextNode.getChildren()) {
                    NTreeNode<D> destChildNode = nextDestNode.addChild(converter.transform(childNode));
                    if (childNode.getChildrenCount() > 0) {
                        nodeMap.put(childNode, destChildNode);
                    }
                }

            }
            return destTree;
        }
    }

    public static <D, N> NTree<D, N> unmodifableNTree(NTree<D, N> originalTree) {
        return new UnmodifableNTreeImpl<>(originalTree);
    }

//    private static NTree<?, ?> EMPTY_TREE = new UnmodifableNTreeImpl(new NTree());
//
//    public static <D> NTree<D, N> emptyNTree() {
//        return (NTree<D, N>)EMPTY_TREE;
//    }

}
