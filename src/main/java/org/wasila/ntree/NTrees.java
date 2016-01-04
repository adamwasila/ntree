package org.wasila.ntree;


import org.wasila.ntree.impl.NTreeImpl;
import org.wasila.ntree.iterator.PreOrderIterator;
import org.wasila.ntree.op.NTreeNodeConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * NTrees
 */
public class NTrees {

    public static <D,S> NTree<D> transform(NTree<S> sourceTree, NTreeNodeConverter<D,S> converter) {
        if (sourceTree.getRootNode() == null) {
            return new NTreeImpl<>();
        } else {
            NTree<D> destTree = new NTreeImpl<D>(converter.transform(sourceTree.getRootNode()));
            PreOrderIterator<S> it = new PreOrderIterator<>(sourceTree);
            Map<NTreeNode<S>,NTreeNode<D>> nodeMap = new HashMap<>();
            nodeMap.put(sourceTree.getRootNode(), destTree.getRootNode());
            while (it.hasNext()) {
                NTreeNode<S> nextNode = it.next().getLastNode();
                NTreeNode<D> nextDestNode = nodeMap.get(nextNode);

                for (NTreeNode<S> childNode : nextNode.getChildrenNode()) {
                    NTreeNode<D> destChildNode = nextDestNode.addChild(converter.transform(childNode));
                    if (childNode.getChildrenCount() > 0) {
                        nodeMap.put(childNode, destChildNode);
                    }
                }

            }
            return destTree;
        }

//        while (it.hasNext()) {
//            NTreePath<S> sourcePath = it.next();
//            D data = converter.transform(sourcePath.getLastNode());
//
//            if (path.size() == 0) {
//                NTreeNode<D> node = tree.setRoot(data);
//                path.enter(0);
//            } else {
//                NTreeNode<D> node = path.getLastNode();
//                node.addChild(data);
//            }
//
//        }
//        return null;
    }

}