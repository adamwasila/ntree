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

import org.wasila.ntree.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NTreePathImpl<N> implements NTreePathExt<N> {

    private NTree<?, N> tree;
    private List<N> path;

    public NTreePathImpl(NTree<?, N> ntree) {
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
            if (index == 0 && tree.getRoot() != null) {
                path.add(tree.getRoot());
            } else {
                throw new IndexOutOfBoundsException("TODO give me a meaningfull text");
            }
        } else {
            N lastNode = getLast();
            path.add(tree.getChild(lastNode, index));
        }
    }

    @Override
    public boolean hasNextSibling() {
        if (size()<2) {
            return false;
        }

        return lastNodeIndex() < tree.getChildrenCount(getNode(-2)) - 1;
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
    public N getLast() {
        if (size() > 0) {
            return path.get(path.size() - 1);
        } else {
            return null;
        }
    }

    @Override
    public Collection<N> getChildrenOfLast() {
        if (size() == 0) {
            List<N> nodes = new ArrayList<>();
            nodes.add(tree.getRoot());
            return nodes;
        } else {
            return tree.getChildren(getLast());
        }
    }

    @Override
    public N getNode(int index) {
        if (index>0) {
            return path.get(index);
        } else {
            return path.get(path.size()-(-index));
        }
    }

    @Override
    public int lastNodeIndex() {
        if (size()>=2) {
            return tree.indexOfNode(getNode(-1));
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
            return tree.getRoot() != null;
        } else {
            return tree.getChildrenCount(getLast()) != 0;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (N cpi : path) {
            sb.append(cpi + " ");
        }

        return "NTreePath{" + sb.toString();
    }
}
