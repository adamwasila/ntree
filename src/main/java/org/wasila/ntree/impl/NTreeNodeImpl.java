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

import org.wasila.ntree.NTreeNode;
import org.wasila.ntree.util.ConvertingIterable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class NTreeNodeImpl<T> implements NTreeNode<T> {

    private List<NTreeNode<T>> children;

    private T data;

    protected NTreeNodeImpl(T data) {
        this.data = data;
        children = new ArrayList<NTreeNode<T>>();
    }

    protected NTreeNodeImpl(NTreeNodeImpl<T> parentNode, T data) {
        this(data);
        parentNode.children.add(this);
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
    public int getChildrenCount() {
        return children.size();
    }

    @Override
    public Collection<NTreeNode<T>> getChildrenNode() {
        return children;
    }

    @Override
    public Collection<T> getChildren() {
        List<T> list = new ArrayList<>();
        Iterator<T> it = new ConvertingIterable<>(children, node -> node.getData()).iterator();

        while (it.hasNext()) {
            list.add(it.next());
        }

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
        NTreeNodeImpl<T> node = new NTreeNodeImpl<T>(data);
        children.add(node);
        return node;
    }

    @Override
    public void addChild(int index, T data) {
        try {
            children.add(index, new NTreeNodeImpl<>(data));
        } catch (IndexOutOfBoundsException ex) {
            throw new IndexOutOfBoundsException("Cannot replace child with index " + index);
        }
    }

    @Override
    public boolean removeChildNode(NTreeNode<T> treeNode) {
        return children.remove(treeNode);
    }

    @Override
    public boolean removeChild(T data) {
        return children.remove(new NTreeNodeImpl<>(data));
    }

    @Override
    public T removeChild(int index) {
        NTreeNode<T> node = children.remove(index);
        return node.getData();
    }

    @Override
    public NTreeNode<T> removeChildNode(int index) {
        NTreeNode<T> node = children.remove(index);
        return node;
    }

    @Override
    public int indexOfNode(NTreeNode<T> treeNode) {
        return children.indexOf(treeNode);
    }

    @Override
    public NTreeNode<T> findFirstChildNode(T data) {
        for (NTreeNode<T> node : getChildrenNode()) {
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
