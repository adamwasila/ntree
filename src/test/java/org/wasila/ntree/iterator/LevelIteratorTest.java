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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wasila.ntree.DataNTree;
import org.wasila.ntree.NTreeNode;
import org.wasila.ntree.NTreePath;
import org.wasila.ntree.NodeNTree;
import org.wasila.ntree.testutils.StringTreeBuilder;
import org.wasila.ntree.testutils.TreeIteratorToString;

public class LevelIteratorTest {

    NodeNTree<String> tree;

    static TreeIterator<String> IteratorUnderTest(NodeNTree<String> tree) {
        return new LevelIterator<>(tree);
    }

    @Before
    public void initialize() {
    }

    @Test
    public void iteratorMultipleHasNextCalls() {
        NodeNTree<String> tree = new StringTreeBuilder().createTree(
                "A->B",
                "A->C",
                "A->D"
        );

        TreeIterator<String> it = IteratorUnderTest(tree);

        it.hasNext();
        it.hasNext();
        it.hasNext();
        it.hasNext();
        it.hasNext();

        String expected = "A";
        String actual = it.next().getData();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void iteratorNoHasNextCall() {
        NodeNTree<String> tree = new StringTreeBuilder().createTree(
                "A->B",
                "A->C",
                "A->D"
        );

        TreeIterator<String> it = IteratorUnderTest(tree);

        String expected = "A";
        String actual = it.next().getData();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testWalkNull() {
        tree = new DataNTree<>();

        TreeIterator<String> it = new LevelIterator<>(tree);

        Assert.assertEquals("", new TreeIteratorToString(it).toString());
    }

    @Test
    public void testWalk1a() {
        tree = new DataNTree<>("A");

        TreeIterator<String> it = new LevelIterator<>(tree);

        Assert.assertEquals("A(0)", new TreeIteratorToString(it).toString());
    }

    @Test
    public void testWalk1() {
        tree = new StringTreeBuilder().createTree(
                "A->B",
                "A->C",
                "A->D"
        );

        TreeIterator<String> it = new LevelIterator<>(tree);

        Assert.assertEquals("A(0) B(1) C(1) D(1)", new TreeIteratorToString(it).toString());
    }

    @Test
    public void testWalk2() {
        tree = new StringTreeBuilder().createTree(
                "A->B",
                   "B->B1",
                   "B->B2",
                   "B->B3",
                "A->C",
                   "C->C1",
                      "C1->C11",
                      "C1->C12",
                      "C1->C13",
                   "C->C2",
                   "C->C3",
                "A->D"
        );

        TreeIterator<String> it = new LevelIterator<>(tree);

        Assert.assertEquals("A(0) B(1) C(1) D(1) B1(2) B2(2) B3(2) C1(2) C2(2) C3(2) C11(3) C12(3) C13(3)", new TreeIteratorToString(it).toString());
    }

}
