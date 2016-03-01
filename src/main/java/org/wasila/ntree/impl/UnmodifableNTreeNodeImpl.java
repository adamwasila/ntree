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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class UnmodifableNTreeNodeImpl<T> implements NTreeNode<T> {

    private final NTreeNode<T> originalTreeNode;

    protected UnmodifableNTreeNodeImpl(NTreeNode<T> originalTreeNode) {
        if (originalTreeNode == null) {
            throw new NullPointerException();
        }
        this.originalTreeNode = originalTreeNode;
    }

    @Override
    public T getData() {
        return originalTreeNode.getData();
    }

    @Override
    public NTreeNode<T> getParent() {
        NTreeNode<T> originalParent = originalTreeNode.getParent();
        if (originalParent!=null) {
            return new UnmodifableNTreeNodeImpl<>(originalTreeNode.getParent());
        } else {
            return null;
        }
    }

    @Override
    public int getChildrenCount() {
        return originalTreeNode.getChildrenCount();
    }

    @Override
    public boolean isLeaf() {
        return originalTreeNode.isLeaf();
    }

    @Override
    public Collection<NTreeNode<T>> getChildrenNode() {
        Collection<NTreeNode<T>> copyOf = new ArrayList<>();
        for (NTreeNode<T> node : originalTreeNode.getChildrenNode()) {
            copyOf.add(new UnmodifableNTreeNodeImpl<T>(node));
        }
        return Collections.unmodifiableCollection(copyOf);
    }

    @Override
    public Collection<NTreeNode<T>> getChildren() {
        return originalTreeNode.getChildren();
    }

    @Override
    public NTreeNode<T> getChildNodeOf(int index) {
        return new UnmodifableNTreeNodeImpl<>(originalTreeNode.getChildNodeOf(index));
    }

    @Override
    public int indexOfNode(NTreeNode<T> treeNode) {
        return originalTreeNode.indexOfNode(treeNode);
    }

    @Override
    public NTreeNode<T> findFirstChildNode(T data) {
        return new UnmodifableNTreeNodeImpl<>(originalTreeNode.findFirstChildNode(data));
    }

    @Override
    public NTreeNode<T> addChild(T data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public NTreeNode<T> addChild(int index, T data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeChildNode(NTreeNode<T> treeNode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T removeChild(int index) {
        throw new UnsupportedOperationException();
    }

}
