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
package org.wasila.ntree.builder;

import org.wasila.ntree.NTreeNode;
import org.wasila.ntree.NTreePathExt;
import org.wasila.ntree.NodeNTree;
import org.wasila.ntree.NodeNTreeImpl;
import org.wasila.ntree.impl.NTreePathImpl;

public class IndexedNTreeBuilder<T> {
    NodeNTree<T> ntree;
    NTreePathExt<NTreeNode<T>> path;

    public IndexedNTreeBuilder() {
        ntree = new NodeNTreeImpl<>();
        path = new NTreePathImpl<>(ntree);
    }

    public IndexedNTreeBuilder<T> add(T data) {
        if (ntree.getRoot()==null) {
            ntree.setRoot(data);
        } else {
            ntree.addChild(path.getLast(), data);
        }
        return this;
    }

    public IndexedNTreeBuilder<T> withChildren() {
        int childNo = path.size() != 0 ? path.getLast().getChildrenCount()-1 : 0;
        path.enter(childNo);
        return this;
    }

    public IndexedNTreeBuilder<T> asLastChild() {
        path.leave();
        return this;
    }

    public NodeNTree<T> build() {
        return ntree;
    }
}
