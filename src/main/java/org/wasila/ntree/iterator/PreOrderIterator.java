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
import org.wasila.ntree.impl.NTreeImpl;
import org.wasila.ntree.impl.NTreePathImpl;

public class PreOrderIterator<T>  extends LevelAwareIterator<T> implements PathTreeIterator<T> {

    private final NTreeImpl<T> tree;
    private NTreePath<T> path;

    public PreOrderIterator(NTreeImpl<T> tree) {
        this.tree = tree;
    }

    @Override
    public boolean hasNext() {
        if (path == null) {
            path = new NTreePathImpl<T>(tree);

            if (tree.getRootNode() != null) {
                path.enter(0);
            }
        } else {
            if (!path.getLastNode().isLeaf()) {
                path.enter(0);
            } else if (path.hasNextSibling()) {
                path.moveToNextSibling();
            } else {
                while (path.size() > 0 && !path.hasNextSibling()) {
                    path.leave();
                }
                if (path.hasNextSibling()) {
                    path.moveToNextSibling();
                }
            }
        }
        storeLevel();
        return path.size() > 0;
    }

    @Override
    public NTreePath<T> next() {
        return path;
    }

    private void storeLevel() {
        setLevel(path.size() - 1);
    }

}
