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

import org.wasila.ntree.util.ConvertingIterable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class NTreeNodeImpl<T> implements NTreeNode<T> {

    private final NTreeNode<T> parent;

    private final List<NTreeNode<T>> children;

    private final T data;

    protected NTreeNodeImpl(NTreeNodeImpl<T> parentNode, T data) {
        this(parentNode, null, data);
    }

    protected NTreeNodeImpl(NTreeNodeImpl<T> parentNode, int index, T data) {
        this(parentNode, new Integer(index), data);
    }

    private NTreeNodeImpl(NTreeNodeImpl<T> parentNode, Integer index, T data) {
        this.data = data;
        children = new ArrayList<>();
        if (parentNode != null) {
            if (index != null) {
                parentNode.children.add(index, this);
            } else {
                parentNode.children.add(this);
            }
        }
        parent = parentNode;
    }

    @Override
    public boolean isLeaf() {
        return getChildrenCount()==0;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public NTreeNode<T> getParent() {
        return parent;
    }

    @Override
    public int getChildrenCount() {
        return children.size();
    }

    @Override
    public Collection<NTreeNode<T>> getChildren() {
        List<NTreeNode<T>> list = new ArrayList<>();
        list.addAll(children);
        return list;
    }

    @Override
    public NTreeNode<T> getChildNodeOf(int index) {
        try {
            return children.get(index);
        } catch (IndexOutOfBoundsException ex) {
            throw new IndexOutOfBoundsException("Child with index " + index + " does not exist");
        }
    }

    @Override
    public NTreeNodeImpl<T> addChild(T data) {
        NTreeNodeImpl<T> node = new NTreeNodeImpl<T>(this, data);
        return node;
    }

    @Override
    public NTreeNodeImpl<T> addChild(int index, T data) {
        try {
            NTreeNodeImpl<T> node = new NTreeNodeImpl<T>(this, index, data);
            return node;
        } catch (IndexOutOfBoundsException ex) {
            throw new IndexOutOfBoundsException("Cannot insert to tree with given index " + index);
        }
    }

    @Override
    public boolean removeChildNode(NTreeNode<T> treeNode) {
        return children.remove(treeNode);
    }

    @Override
    public T removeChild(int index) {
        NTreeNode<T> node = children.remove(index);
        return node.getData();
    }

    @Override
    public int indexOfNode(NTreeNode<T> treeNode) {
        return children.indexOf(treeNode);
    }

    @Override
    public NTreeNode<T> findFirstChildNode(T data) {
        for (NTreeNode<T> node : getChildren()) {
            if (node.getData().equals(data)) {
                return node;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NTreeNode<?> nTreeNode = (NTreeNode<?>) o;
        return !(data != null ? !data.equals(nTreeNode.getData()) : nTreeNode.getData() != null);
    }

    @Override
    public int hashCode() {
        int result = (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NTreeNodeImpl{" +
                "data=" + data +
                '}';
    }
}
