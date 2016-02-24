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
import org.wasila.ntree.NTreePath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NTreePathImpl<T> implements NTreePath<T> {

    private NTreeImpl<T> tree;
    private List<NTreeNode<T>> path;

    public NTreePathImpl(NTreeImpl<T> ntree) {
        this.tree = ntree;
        this.path = new ArrayList<>();
    }

    /**
     * Go up in path
     */
    @Override
    public void leave() {
        if (path.size()>0) {
            path.remove(path.size() - 1);
        } else {
            throw new IndexOutOfBoundsException("Trying to leave on empty path");
        }
    }

    /**
     * Go down in path (entering child with specified index)
     *
     * @param index
     */
    @Override
    public void enter(int index) {
        if (size() == 0) {
            if (index == 0 && tree.getRootNode() != null) {
                path.add(tree.getRootNode());
            } else {
                throw new IndexOutOfBoundsException("TODO give me a meaningfull text");
            }
        } else {
            NTreeNode<T> lastNode = getLastNode();
            path.add(lastNode.getChildNodeOf(index));
        }
    }

    @Override
    public boolean hasNextSibling() {
        if (size()<2) {
            return false;
        }

        return lastNodeIndex() < getNode(-2).getChildrenCount() - 1;
    }

    @Override
    public void moveToNextSibling() {
        if (size()<2) {
            throw new IndexOutOfBoundsException("TODO");
        }
        int newIdx = lastNodeIndex() + 1;
        leave();
        enter(newIdx);
    }

    @Override
    public int size() {
        return path.size();
    }

    @Override
    public T getLast() {
        NTreeNode<T> lastNode = getLastNode();
        if (lastNode!=null) {
            return lastNode.getData();
        } else {
            return null;
        }
    }

    @Override
    public NTreeNode<T> getLastNode() {
        if (size() > 0) {
            return path.get(path.size() - 1);
        } else {
            return null;
        }
    }

    @Override
    public Collection<NTreeNode<T>> getChildrenOfLastNode() {
        if (size() == 0) {
            List<NTreeNode<T>> nodes = new ArrayList<>();
            nodes.add(tree.getRootNode());
            return nodes;
        } else {
            return getLastNode().getChildrenNode();
        }
    }

    @Override
    public NTreeNode<T> getLastButOneNode() {
        if (size() > 1) {
            return path.get(path.size() - 2);
        } else {
            return null;
        }
    }

    @Override
    public NTreeNode<T> getNode(int index) {
        if (index>0) {
            return path.get(index);
        } else {
            return path.get(path.size()-(-index));
        }
    }

    @Override
    public int lastNodeIndex() {
        if (size()>=2) {
            return getNode(-2).indexOfNode(getNode(-1));
        } else {
            return 0;
        }
    }

    @Override
    public boolean canLeave() {
        return size()>0;
    }

    @Override
    public boolean canEnter() {
        if (size() == 0) {
            return tree.getRootNode() != null;
        } else {
            return getLastNode().getChildrenCount() != 0;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (NTreeNode<T> cpi : path) {
            sb.append(cpi.getData() + " ");
        }

        return "NTreePath{" + sb.toString();
    }
}
