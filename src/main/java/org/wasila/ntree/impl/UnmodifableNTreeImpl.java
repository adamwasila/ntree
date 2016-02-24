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
import org.wasila.ntree.NTreePath;
import org.wasila.ntree.iterator.FindIterator;
import org.wasila.ntree.iterator.PathTreeIterator;
import org.wasila.ntree.iterator.PredicateIterator;
import org.wasila.ntree.op.Predicate;

import javax.naming.OperationNotSupportedException;
import java.util.Collection;

public class UnmodifableNTreeImpl<T> implements NTree<T> {

    private final NTreeImpl<T> originalTree;

    public UnmodifableNTreeImpl(NTreeImpl<T> originalTree) {
        if (originalTree==null) {
            throw new NullPointerException();
        }
        this.originalTree = originalTree;
    }


    @Override
    public T getRoot() {
        return originalTree.getRoot();
    }

    @Override
    public void setRoot(T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getChildrenCount(T data) {
        return originalTree.getChildrenCount(data);
    }

    @Override
    public boolean isLeaf(T data) {
        return originalTree.isLeaf(data);
    }

    @Override
    public Collection<T> getChildren(T parent) {
        return originalTree.getChildren(parent);
    }

    @Override
    public int indexOfNode(T parent, T child) {
        return originalTree.indexOfNode(parent, child);
    }

    @Override
    public void addChild(T parent, T childToAdd) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addChild(T parent, int index, T childToAdd) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeChild(T parent, T childToRemove) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeChild(T parent, int index) {
        throw new UnsupportedOperationException();
    }

    //TODO
    @Override
    public PathTreeIterator<T> find(Predicate<T> predicate) {
        final PathTreeIterator<T> it = originalTree.find(predicate);
        return new PathTreeIterator<T>() {

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public NTreePath<T> next() {
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
