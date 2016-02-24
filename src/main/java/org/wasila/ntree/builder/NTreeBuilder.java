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

import org.wasila.ntree.NTree;
import org.wasila.ntree.NTreePath;
import org.wasila.ntree.impl.NTreeImpl;
import org.wasila.ntree.impl.NTreePathImpl;

public class NTreeBuilder<T> {
    NTreeImpl<T> ntree;
    NTreePath<T> path;

    public NTreeBuilder() {
        ntree = new NTreeImpl<>();
        path = new NTreePathImpl<>(ntree);
    }

    public NTreeBuilder<T> add(T data) {
        if (ntree.getRootNode()==null) {
            ntree.setRoot(data);
        } else {
            path.getLastNode().addChild(data);
        }
        return this;
    }

    public NTreeBuilder<T> withChildren() {
        int childNo = path.size() != 0 ? path.getLastNode().getChildrenCount()-1 : 0;
        path.enter(childNo);
        return this;
    }

    public NTreeBuilder<T> asLastChild() {
        path.leave();
        return this;
    }

    public NTree<T> build() {
        return ntree;
    }
}
