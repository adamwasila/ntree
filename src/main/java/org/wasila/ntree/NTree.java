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

public interface NTree<D> {

    D getRoot();

    void setRoot(D data);

    PathTreeIterator<D> find(Predicate<D> predicate);

    // read

    int getChildrenCount(D data);

    boolean isLeaf(D data);

    Collection<D> getChildren(D parent);

    int indexOfNode(D parent, D child);

    // modify

    void addChild(D parent, D childToAdd);

    void addChild(D parent, int index, D childToAdd);

    // delete

    void removeChild(D parent, D childToRemove);

    void removeChild(D parent, int index);

}
