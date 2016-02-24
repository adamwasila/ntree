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
import org.wasila.ntree.builder.Child;
import org.wasila.ntree.builder.NTreeBuilder;
import org.wasila.ntree.impl.NTreeImpl;
import org.wasila.ntree.iterator.LevelIterator;
import org.wasila.ntree.iterator.PathTreeIterator;
import org.wasila.ntree.iterator.TreeIterator;
import org.wasila.ntree.testutils.Iterators;
import org.wasila.ntree.testutils.StringTreeBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class NTreeTest {

    @Test
    public void testCreateWithNoRoot() {
        NTreeImpl<String> tree = new NTreeImpl<>();
        assertNull(tree.getRoot());
    }

    @Test
    public void testCreateWithRoot() {
        NTreeImpl<String> tree = new NTreeImpl<String>("root");
        assertEquals("root", tree.getRoot());
    }

    @Test
    public void testCreateAndAddChildren() {
        NTreeImpl<String> tree = new NTreeImpl<String>("root");
        assertEquals("root", tree.getRoot());
    }

    @Test
    public void testFluentCreate() {
        //TODO
    }

}