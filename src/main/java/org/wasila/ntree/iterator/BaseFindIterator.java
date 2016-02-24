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
import org.wasila.ntree.NTreePath;
import org.wasila.ntree.impl.NTreeImpl;

/*package*/ abstract class BaseFindIterator<D> extends LevelAwareIterator<D> {

    private final PathTreeIterator<D> internalIterator;
    private NTreePath<D> currentResult;

    /*package*/ BaseFindIterator(NTreeImpl<D> tree) {
        this.internalIterator = new PreOrderIterator<>(tree);
    }

    @Override
    public boolean hasNext() {
        currentResult = null;
        NTreePath<D> next;
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
    public NTreePath<D> next() {
        return currentResult;
    }

    protected abstract boolean apply(NTreePath<D> next);

}
