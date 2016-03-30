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

import org.wasila.ntree.NTreeNode;
import org.wasila.ntree.NodeNTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class LevelIterator<T> implements TreeIterator<T> {

    private List<NTreeNode<T>> currentLevel;
    private Iterator<NTreeNode<T>> currentLevelIterator;

    private int level;

    public LevelIterator(NodeNTree<T> tree) {
        currentLevel = new ArrayList<>();
        level = 0;
        if (tree.getRoot() != null) {
            currentLevel.add(tree.getRoot());
        }
        currentLevelIterator = currentLevel.iterator();
    }

    @Override
    public boolean hasNext() {
        if (!currentLevelIterator.hasNext()) {
            List<NTreeNode<T>> tempList = new ArrayList<>();
            for (NTreeNode<T> node : currentLevel) {
                tempList.addAll(node.getChildren());
            }
            currentLevel = tempList;
            currentLevelIterator = currentLevel.iterator();
            level++;
        }
        return currentLevelIterator.hasNext();
    }

    @Override
    public NTreeNode<T> next() {
        return currentLevelIterator.next();
    }

    public int getLevel() {
        return level;
    }

}
