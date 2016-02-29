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

import org.wasila.ntree.iterator.PathTreeIterator;
import org.wasila.ntree.op.Predicate;

import java.util.Collection;

public interface NTree<D, N> {

    N getRoot();

    N setRoot(D data);

    PathTreeIterator<N> find(Predicate<N> predicate);

    // read

    int getChildrenCount(N data);

    N getChild(N parent, int index);

    boolean isLeaf(N data);

    Collection<N> getChildren(N parent);

    int indexOfNode(N child);

    // modify

    N addChild(N parent, D childToAdd);

    N addChild(N parent, int index, D childToAdd);

    // delete

    void remove(N nodeToRemove);

    void removeChild(N parent, int index);

}
