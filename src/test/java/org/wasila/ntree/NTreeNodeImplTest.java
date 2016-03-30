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

public class NTreeNodeImplTest {

    NTree<String, NTreeNode<String>> tree;

    @Before
    public void initialization() {
        tree = new NodeNTreeImpl<>();
        tree.setRoot("test");
        tree.addChild(tree.getRoot(), "first");
        tree.addChild(tree.getRoot(), "second");
        tree.addChild(tree.getRoot(), "third");
    }

    @Test
    public void testCreateSimpleTree() {
        Assert.assertEquals(3, tree.getChildrenCount(tree.getRoot()));
        tree.addChild(tree.getRoot(), "fourth");
        Assert.assertEquals(4, tree.getChildrenCount(tree.getRoot()));
    }

    @Test
    public void testRemoveChild() {
        tree.removeChild(tree.getRoot(), 0);
        Assert.assertEquals(2, tree.getChildrenCount(tree.getRoot()));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveChildOutOfBounds() {
        tree.removeChild(tree.getRoot(), 3);
    }

}
