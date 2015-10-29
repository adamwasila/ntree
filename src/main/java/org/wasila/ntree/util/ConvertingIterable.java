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
package org.wasila.ntree.util;

import java.util.Iterator;
import java.util.function.Function;

public class ConvertingIterable<U,T> implements Iterable<T> {

    private final Function<U, T> function;
    private final Iterable<U> nestedIterable;

    public ConvertingIterable(Iterable<U> nestedIterable, Function<U, T> function) {
        this.function = function;
        this.nestedIterable = nestedIterable;
    }

    @Override
    public Iterator<T> iterator() {
        final Iterator<U> iterator = nestedIterable.iterator();

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public T next() {
                return function.apply(iterator.next());
            }
        };
    }

}
