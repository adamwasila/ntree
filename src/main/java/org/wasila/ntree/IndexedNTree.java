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

import org.wasila.ntree.op.Predicate;

import java.util.Collection;
import java.util.Iterator;

public interface IndexedNTree<D> {

    // read

    boolean isLeaf(int... path);

    D get(int... path);

    int getChildrenCount(int... path);

    Collection<D> getChildren(int... path);

    // modify

    void addChild(D childToAdd, int... path);

    void insertChild(D childToAdd, int... path);

    // delete

    void remove(int... path);

    // query & find

    int[] findFirst(Predicate<D> predicate);

    Iterator<int[]> find(Predicate<D> predicate);

}
