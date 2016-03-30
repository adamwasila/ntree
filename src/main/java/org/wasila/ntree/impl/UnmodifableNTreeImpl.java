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
import org.wasila.ntree.NTreePath;
import org.wasila.ntree.iterator.PathTreeIterator;
import org.wasila.ntree.op.Predicate;

import java.util.Collection;

public class UnmodifableNTreeImpl<D, N> implements NTree<D, N> {

    private final NTree<D, N> originalTree;

    public UnmodifableNTreeImpl(NTree<D, N> originalTree) {
        if (originalTree==null) {
            throw new NullPointerException();
        }
        this.originalTree = originalTree;
    }


    @Override
    public N getRoot() {
        return originalTree.getRoot();
    }

    @Override
    public N setRoot(D data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getChildrenCount(N node) {
        return originalTree.getChildrenCount(node);
    }

    @Override
    public N getChild(N parent, int index) {
        return originalTree.getChild(parent, index);
    }

    @Override
    public boolean isLeaf(N data) {
        return originalTree.isLeaf(data);
    }

    @Override
    public Collection<N> getChildren(N parent) {
        return originalTree.getChildren(parent);
    }

    @Override
    public int indexOfNode(N child) {
        return originalTree.indexOfNode(child);
    }

    @Override
    public N addChild(N parent, D childToAdd) {
        throw new UnsupportedOperationException();
    }

    @Override
    public N addChild(N parent, int index, D childToAdd) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(N childToRemove) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeChild(N parent, int index) {
        throw new UnsupportedOperationException();
    }

    //TODO
    @Override
    public PathTreeIterator<N> find(Predicate<N> predicate) {
        final PathTreeIterator<N> it = originalTree.find(predicate);
        return new PathTreeIterator<N>() {

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public NTreePath<N> next() {
                //TODO return read only nodes!
                return it.next();
            }

            @Override
            public int getLevel() {
                return it.getLevel();
            }
        };
    }

}
