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
import org.wasila.ntree.iterator.PathTreeIterator;
import org.wasila.ntree.iterator.PredicateIterator;
import org.wasila.ntree.op.Predicate;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class NTreeImpl<T> implements NTree<T> {

    private NTreeNode<T> root;

    private Map<T, NTreeNode<T>> lookupMap;

    public NTreeImpl() {
        root = null;
        lookupMap = new HashMap<>();
    }

    public NTreeImpl(T data) {
        setRoot(data);
    }

    @Override
    public T getRoot() {
        return root == null ? null : root.getData();
    }

    @Override
    public void setRoot(T data) {
        root = new NTreeNodeImpl<>(data);
        lookupMap.put(data, root);
    }


    @Override
    public PathTreeIterator<T> find(Predicate<T> predicate) {
        return new PredicateIterator<>(this, predicate);
    }

    @Override
    public int getChildrenCount(T data) {
        return lookupMap.get(data).getChildrenCount();
    }

    @Override
    public boolean isLeaf(T data) {
        return lookupMap.get(data).isLeaf();
    }

    @Override
    public Collection<T> getChildren(T data) {
        return lookupMap.get(data).getChildren();
    }

    @Override
    public int indexOfNode(T parent, T child) {
        NTreeNode<T> parentNode = lookupMap.get(parent);
        NTreeNode<T> childNode = lookupMap.get(child);
        return parentNode.indexOfNode(childNode);
    }

    @Override
    public void addChild(T parent, T childToAdd) {
        NTreeNode<T> node = lookupMap.get(parent).addChild(childToAdd);
        lookupMap.put(childToAdd, node);
    }

    @Override
    public void addChild(T parent, int index, T childToAdd) {
        NTreeNode<T> node = lookupMap.get(parent).addChild(index, childToAdd);
        lookupMap.put(childToAdd, node);
    }

    @Override
    public void removeChild(T parent, T childToRemove) {
        lookupMap.get(parent).removeChild(childToRemove);
        //TODO remove children of removed node from lookupMap
    }

    @Override
    public void removeChild(T parent, int index) {
        T childData = lookupMap.get(parent).removeChild(index);
        lookupMap.remove(childData);
        //TODO remove children of removed node from lookupMap
    }

    public NTreeNode<T> getRootNode() {
        return root;
    }

}
