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
package org.wasila.ntree.impl;

import org.wasila.ntree.NTree;
import org.wasila.ntree.NTreeNode;
import org.wasila.ntree.iterator.FindIterator;
import org.wasila.ntree.iterator.PathTreeIterator;

public class NTreeImpl<T> implements NTree<T> {

    private NTreeNode<T> root;

    public NTreeImpl() {
        root = null;
    }

    public NTreeImpl(T rootNode) {
        root = new NTreeNodeImpl<>(rootNode);
    }

    @Override
    public T getRoot() {
        return root == null ? null : root.getData();
    }

    @Override
    public NTreeNode<T> setRoot(T data) {
        return root = new NTreeNodeImpl<>(data);
    }

    @Override
    public NTreeNode getRootNode() {
        return root;
    }

    @Override
    public NTreeNode<T> findFirst(T data) {
        PathTreeIterator<T> it = find(data);
        if (it.hasNext()) {
            return it.next().getLastNode();
        } else {
            return null;
        }
    }

    @Override
    public PathTreeIterator<T> find(T data) {
        return new FindIterator<>(this, data);
    }

//    @Override
//    public T getChild(T parent, int index) {
//        T t = null;
//        NTreeNode<T> parentNode = findNode(parent);
//        if (parentNode!=null) {
//            parentNode.getChildNodeOf(index);
//        }
//        return t;
//    }
//
//    @Override
//    public Iterator<T> getChildrenNode(T parent) {
//        Iterator<T> it = null;
//        NTreeNode<T> parentNode = findNode(parent);
//        if (parentNode!=null) {
//            it = parentNode.getChildren().iterator();
//        }
//        return it;
//    }

//    private NTreeNode<T> findNode(T parent) {
//        return null;
//    }
//
//    private NTreeNode<T> findNodeRecursive(NTreeNode<T> currentNode, T data) {
//        if (data.equals(currentNode.getData())) {
//            return currentNode;
//        }
//        NTreeNode<T> result = null;
//        for (NTreeNode<T> node : currentNode.getChildrenNode()) {
//            result = findNodeRecursive(node, data);
//            if (result!=null) {
//                break;
//            }
//        }
//        return result;
//    }

}
