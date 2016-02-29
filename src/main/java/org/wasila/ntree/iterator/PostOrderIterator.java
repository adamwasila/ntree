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
package org.wasila.ntree.iterator;

import org.wasila.ntree.*;
import org.wasila.ntree.impl.NTreePathImpl;

public final class PostOrderIterator<N> extends BaseIterator<N> implements PathTreeIterator<N> {

    private final NTree<?, N> tree;

    private NTreePath<N> path;

    public PostOrderIterator(NTree<?, N> tree) {
        this.tree = tree;
    }

    @Override
    protected boolean hasNextImpl() {
        if (path == null) {
            path = new NTreePathImpl<>(tree);
            while (path.canEnter()) {
                path.enter(0);
            }
        } else {
            if (path.hasNextSibling()) {
                path.moveToNextSibling();
                while (path.canEnter()) {
                    path.enter(0);
                }
            } else {
                path.leave();
            }
        }
        storeLevel();
        return path.size() > 0;
    }

    @Override
    protected NTreePath<N> nextImpl() {
        return path;
    }

    private void storeLevel() {
        setLevel(path.size() - 1);
    }

}
