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
    public void testTransform() {
        NTreeImpl<String> sourceTree = new NTreeImpl<>();

        sourceTree.setRoot("hello");
        sourceTree.addChild("hello", "one");
        sourceTree.addChild("hello", "two");
        sourceTree.addChild("hello", "three");

        sourceTree.addChild("two", "two-one");
        sourceTree.addChild("two", "two-two");

        NTreeImpl<String> destTree = NTrees.transform(sourceTree, new NTreeNodeConverter<String,String>() {
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

}
