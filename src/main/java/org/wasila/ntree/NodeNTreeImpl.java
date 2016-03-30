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

import org.wasila.ntree.iterator.PathTreeIterator;
import org.wasila.ntree.iterator.PredicateIterator;
import org.wasila.ntree.op.Predicate;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class NodeNTreeImpl<T> implements NodeNTree<T> {

    private NTreeNode<T> root;

    public NodeNTreeImpl() {
        root = null;
    }

    public NodeNTreeImpl(T data) {
        setRoot(data);
    }

    @Override
    public NTreeNode<T> getRoot() {
        return root == null ? null : root;
    }

    @Override
    public NTreeNode<T> setRoot(T data) {
        root = new NTreeNodeImpl<>(null, data);
        return root;
    }

    @Override
    public PathTreeIterator<NTreeNode<T>> find(Predicate<NTreeNode<T>> predicate) {
        return new PredicateIterator<NTreeNode<T>>(this, predicate);
    }

    @Override
    public int getChildrenCount(NTreeNode<T> node) {
        return node.getChildrenCount();
    }

    @Override
    public NTreeNode<T> getChild(NTreeNode<T> parent, int index) {
        return parent.getChildNodeOf(index);
    }

    @Override
    public boolean isLeaf(NTreeNode<T> node) {
        return node.isLeaf();
    }

    @Override
    public Collection<NTreeNode<T>> getChildren(NTreeNode<T> node) {
        return node.getChildren();
    }

    @Override
    public int indexOfNode(NTreeNode<T> node) {
        if (node.getParent() == null) {
            return 0;
        } else {
            return node.getParent().indexOfNode(node);
        }
    }

    @Override
    public NTreeNode<T> addChild(NTreeNode<T> parent, T childToAdd) {
        return parent.addChild(childToAdd);
    }

    @Override
    public NTreeNode<T> addChild(NTreeNode<T> parent, int index, T childToAdd) {
        return parent.addChild(index, childToAdd);
    }

    @Override
    public void remove(NTreeNode<T> node) {
        node.getParent().removeChildNode(node);
    }

    @Override
    public void removeChild(NTreeNode<T> node, int index) {
        node.removeChild(index);
    }

}
