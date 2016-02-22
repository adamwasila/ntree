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
package org.wasila.ntree;

import org.junit.Assert;
import org.junit.Test;
import org.wasila.ntree.impl.NTreeImpl;
import org.wasila.ntree.iterator.LevelIterator;
import org.wasila.ntree.iterator.TreeIterator;
import org.wasila.ntree.op.NTreeNodeConverter;
import org.wasila.ntree.testutils.TreeIteratorToString;

public class NTreesTest {

    @Test
    public void test1() {
        NTree<String> sourceTree = new NTreeImpl<>();

        NTreeNode<String> root = sourceTree.setRoot("hello");
        root.addChild("one");
        NTreeNode<String> two = root.addChild("two");
        root.addChild("three");

        two.addChild("two-one");
        two.addChild("two-two");

        NTree<String> destTree = NTrees.transform(sourceTree, new NTreeNodeConverter<String,String>() {
            @Override
            public String transform(NTreeNode<String> node) {
                return node.getData().toUpperCase();
            }
        });

        TreeIterator<String> itSrc = new LevelIterator<>(sourceTree);
        TreeIterator<String> itDst = new LevelIterator<>(destTree);

        String source = new TreeIteratorToString(itSrc).toString();
        String destination = new TreeIteratorToString(itDst).toString();

        Assert.assertEquals(source.toUpperCase(), destination);
    }

    @Test
    public void testUnmodifableTree() {
        NTree<String> sourceTree = new NTreeImpl<>();

        Assert.assertNull(sourceTree.getRoot());
        Assert.assertNull(sourceTree.getRootNode());

    }


}
