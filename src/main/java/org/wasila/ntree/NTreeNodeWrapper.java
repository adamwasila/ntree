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

public final class NTreeNodeWrapper<D> {

    private NTreeNode<D> internalNode;

    /* package */ NTreeNodeWrapper(D data, NTreeNode<D> internalNode) {
        this.internalNode = internalNode;
    }

    public D getData() {
        return internalNode.getData();
    }

    /* package */ NTreeNode<D> getTreeNode() {
        return internalNode;
    }

    public static <D> NTreeNodeWrapper<D> node(NTreeNodeImpl<D> parent, D data) {
        return new NTreeNodeWrapper<D>(data, new NTreeNodeImpl<D>(parent, data));
    }

}
