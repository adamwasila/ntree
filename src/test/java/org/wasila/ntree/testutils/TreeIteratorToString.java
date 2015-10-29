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
package org.wasila.ntree.testutils;

import org.wasila.ntree.iterator.TreeIterator;

public class TreeIteratorToString {

    StringBuilder sb;

    public TreeIteratorToString(TreeIterator<String> it) {
        sb = new StringBuilder();
        while (it.hasNext()) {
            sb.append(it.next().getData());
            sb.append("(");
            sb.append(it.getLevel());
            sb.append(") ");

        }
        if (sb.length()>0) {
            sb.setLength(sb.length()-1);
        }
    }


    @Override
    public String toString() {
        return sb.toString();
    }

}
