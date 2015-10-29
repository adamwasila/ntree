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
import org.junit.Before;
import org.junit.Test;
import org.wasila.ntree.impl.NTreeImpl;
import org.wasila.ntree.impl.NTreeNodeImpl;

public class NTreeNodeTest {

    NTree<String> tree;

    private NTreeNode<String> baseNode;
    private NTreeNode<String> firstChild;
    private NTreeNode<String> secondChild;
    private NTreeNode<String> thirdChild;

    @Before
    public void initialization() {
        tree = new NTreeImpl<>();
        baseNode = tree.setRoot("test");
        firstChild = baseNode.addChild("first");
        secondChild = baseNode.addChild("second");
        thirdChild = baseNode.addChild("third");
    }

    @Test
    public void testCreateSimpleTree() {
        Assert.assertEquals(3, baseNode.getChildrenCount());
        baseNode.addChild("fourth");
        Assert.assertEquals(4, baseNode.getChildrenCount());
    }

    @Test
    public void remove1() {
        baseNode.removeChild(0);
        Assert.assertEquals(2, baseNode.getChildrenCount());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void remove2() {
        baseNode.removeChild(3);
    }

    @Test
    public void remove3() {
        boolean result = baseNode.removeChildNode(firstChild);
        Assert.assertTrue(result);
    }

    @Test
    public void remove4() {
        NTreeNode<String> childOfFirst = firstChild.addChild("hello world!!!");
        boolean result = baseNode.removeChildNode(childOfFirst);
        Assert.assertFalse(result);
    }

}
