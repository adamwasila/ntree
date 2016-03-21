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

import org.wasila.ntree.impl.NTreePathImpl;
import org.wasila.ntree.iterator.PathTreeIterator;
import org.wasila.ntree.op.Predicate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class IndexedNTreeImpl<D> implements IndexedNTree<D> {

    NodeNTree<D> baseTree;

    public IndexedNTreeImpl() {
        this.baseTree = new DataNTree<>();
    }

    @Override
    public boolean isLeaf(int... path) {
        return baseTree.isLeaf(getNode(path.length-1, path));
    }

    @Override
    public D get(int... path) {
        return getNode(path.length-1, path).getData();
    }

    @Override
    public int getChildrenCount(int... path) {
        return getNode(path.length-1, path).getChildrenCount();
    }

    @Override
    public Collection<D> getChildren(int... path) {
        Collection<D> children = new ArrayList<D>();
        for (NTreeNode<D> node : getNode(path.length-1, path).getChildren()) {
            children.add(node.getData());
        }
        return children;
    }

    @Override
    public void addChild(D childToAdd, int... path) {
        if (path.length == 0) {
            baseTree.setRoot(childToAdd);
        } else {
            getNode(path.length-1, path).addChild(path[path.length-1], childToAdd);
        }
    }

    @Override
    public void insertChild(D childToAdd, int... path) {
        // TODO
    }

    @Override
    public void remove(int... path) {
        NTreeNode<D> node = getNode(path.length-1, path);
        baseTree.remove(node);
    }

    @Override
    public int[] findFirst(Predicate<D> predicate) {
        return find(predicate).next();
    }

    @Override
    public Iterator<int[]> find(Predicate<D> predicate) {
        final PathTreeIterator<NTreeNode<D>> iterator = baseTree.find(new Predicate<NTreeNode<D>>() {
            @Override
            public boolean apply(NTreeNode<D> arg) {
                return predicate.apply(arg.getData());
            }
        });

        return new Iterator<int[]>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public int[] next() {
                return toPath(iterator.next());
            }
        };
    }

    private NTreeNode<D> getNode(int limit, int... path) {
        NTreePath<NTreeNode<D>> basePath = new NTreePathImpl<NTreeNode<D>>(baseTree);
        for (int i = 0; i <= limit; i++) {
            basePath.enter(path[i]);
        }
        return basePath.getLast();
    }

    private int[] toPath(NTreePath<NTreeNode<D>> treePath) {
        int[] path = new int[treePath.size()];
        for (int i=0; i<treePath.size(); i++) {
            path[i] = baseTree.indexOfNode(treePath.getNode(i));
        }
        return path;
    }

}
