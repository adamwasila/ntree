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

import java.util.Collection;

public interface NTreeNode<D> {

    // read

    D getData();

    int getChildrenCount();

    boolean isLeaf();

    Collection<NTreeNode<D>> getChildrenNode();

    Collection<D> getChildren();

    NTreeNode<D> getChildNodeOf(int index);

    int indexOfNode(NTreeNode<D> treeNode);

    NTreeNode<D> findFirstChildNode(D data);

    // modify

    NTreeNode<D> addChild(D data);

    void addChild(int index, D data);

    void addAll(NTreeNode<D> node);

    // delete

    boolean removeChildNode(NTreeNode<D> treeNode);

    boolean removeChild(D data);

    D removeChild(int index);

    NTreeNode<D> removeChildNode(int index);

}
