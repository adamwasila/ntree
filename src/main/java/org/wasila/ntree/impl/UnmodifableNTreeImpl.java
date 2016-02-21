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
import org.wasila.ntree.iterator.FindIterator;
import org.wasila.ntree.iterator.PathTreeIterator;
import org.wasila.ntree.iterator.PredicateIterator;
import org.wasila.ntree.op.Predicate;

import javax.naming.OperationNotSupportedException;

public class UnmodifableNTreeImpl<T> implements NTree<T> {

    private final NTree<T> originalTree;

    public UnmodifableNTreeImpl(NTree<T> originalTree) {
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
    public NTreeNode<T> setRoot(T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public NTreeNode<T> getRootNode() {
        return new UnmodifableNTreeNodeImpl<>(originalTree.getRootNode());
    }

    @Override
    public NTreeNode<T> findFirst(T data) {
        return new UnmodifableNTreeNodeImpl<>(originalTree.findFirst(data));
    }

    //TODO
    @Override
    public PathTreeIterator<T> find(T data) {
        throw new UnsupportedOperationException();
    }

    //TODO
    @Override
    public PathTreeIterator<T> find(Predicate<T> predicate) {
        throw new UnsupportedOperationException();
    }
}
