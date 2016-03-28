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

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class NTreeTest {

    @Test
    public void testCreateWithNoRoot() {
        DataNTree<String> tree = new DataNTree<>();
        assertNull(tree.getRoot());
    }

    @Test
    public void testCreateWithRoot() {
        DataNTree<String> tree = new DataNTree<>("root");
        assertEquals("root", tree.getRoot().getData());
    }

    @Test
    public void testCreateAndAddChildren() {
        DataNTree<String> tree = new DataNTree<>("root");
        tree.addChild(tree.getRoot(), "firstChild");
        tree.addChild(tree.getRoot(), "secondChild");
        tree.addChild(tree.getRoot(), "thirdChild");

        assertEquals("root", tree.getRoot().getData());
        assertEquals("firstChild", tree.getRoot().getChildNodeOf(0).getData());
        assertEquals("secondChild", tree.getRoot().getChildNodeOf(1).getData());
        assertEquals("thirdChild", tree.getRoot().getChildNodeOf(2).getData());
    }

    @Test
    public void testCreateAndInsertChildren() {
        DataNTree<String> tree = new DataNTree<>("root");
        tree.addChild(tree.getRoot(), 0, "secondChild");
        tree.addChild(tree.getRoot(), 1, "thirdChild");
        tree.addChild(tree.getRoot(), 0, "firstChild");

        assertEquals("root", tree.getRoot().getData());
        assertEquals("firstChild", tree.getRoot().getChildNodeOf(0).getData());
        assertEquals("secondChild", tree.getRoot().getChildNodeOf(1).getData());
        assertEquals("thirdChild", tree.getRoot().getChildNodeOf(2).getData());
    }

    @Test
    public void testFluentCreate() {
        //TODO
    }

}