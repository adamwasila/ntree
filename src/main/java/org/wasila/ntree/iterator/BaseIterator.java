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

import org.wasila.ntree.NTreePath;
import org.wasila.ntree.NTreePathExt;

import java.util.NoSuchElementException;

abstract class BaseIterator<T> extends LevelAwareIterator<T> {

    private Boolean cachedHasNext;

    /* package */ BaseIterator() {
        this.cachedHasNext = null;
    }

    final public boolean hasNext() {
        if (cachedHasNext == null) {
            cachedHasNext = hasNextImpl();
        }
        return cachedHasNext;
    }

    final public NTreePathExt<T> next() {
        boolean hasNext = hasNext();
        if (!hasNext) {
            throw new NoSuchElementException();
        }
        cachedHasNext = null;
        return nextImpl();
    }

    protected abstract boolean hasNextImpl();

    protected abstract NTreePathExt<T> nextImpl();

}