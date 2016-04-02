/**
 * (C) Copyright 2015 Adam Wasila and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 */
package org.wasila.ntree;

import org.wasila.ntree.impl.UnmodifableNTreeImpl;
import org.wasila.ntree.iterator.PreOrderIterator;
import org.wasila.ntree.op.IndexedNTreeNodeConverter;
import org.wasila.ntree.op.NTreeNodeConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * NTrees
 */
public class NTrees {

    public static <D,S> NodeNTree<D> transform(NodeNTree<S> sourceTree, NTreeNodeConverter<D,S> converter) {
        if (sourceTree.getRoot() == null) {
            return new NodeNTreeImpl<>();
        } else {
            NodeNTreeImpl<D> destTree = new NodeNTreeImpl<D>(converter.transform(sourceTree.getRoot()));
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
