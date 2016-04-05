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

import org.wasila.ntree.NTree;
import org.wasila.ntree.NTreeNode;
import org.wasila.ntree.NTreePath;
import org.wasila.ntree.NTreePathExt;

/*package*/ abstract class BaseFindIterator<N> extends BaseIterator<N> {

    private final PathTreeIterator<N> internalIterator;

    private NTreePathExt<N> currentResult;

    /*package*/ BaseFindIterator(NTree<?, N> tree) {
        this.internalIterator = new PreOrderIterator<>(tree);
    }

    @Override
    protected boolean hasNextImpl() {
        currentResult = null;
        NTreePathExt<N> next;
        while (internalIterator.hasNext()) {
            next = internalIterator.next();
            setLevel(internalIterator.getLevel());
            if (apply(next)) {
                currentResult = next;
                break;
            }
        }
        return currentResult != null;
    }

    @Override
    protected NTreePathExt<N> nextImpl() {
        return currentResult;
    }

    protected abstract boolean apply(NTreePathExt<N> next);

}
