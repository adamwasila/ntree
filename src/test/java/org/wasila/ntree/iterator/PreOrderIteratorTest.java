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
import org.junit.Test;
import org.wasila.ntree.DataNTree;
import org.wasila.ntree.NTree;
import org.wasila.ntree.NTreeNode;
import org.wasila.ntree.NodeNTree;
import org.wasila.ntree.testutils.StringTreeBuilder;
import org.wasila.ntree.testutils.PathTreeIteratorToString;

public class PreOrderIteratorTest {

    @Test
    public void testWalk1() {
        DataNTree<String> tree = new DataNTree<String>("A");
        tree.getRoot().addChild("B");
        tree.getRoot().addChild("C");
        tree.getRoot().addChild("D");

        PathTreeIterator<NTreeNode<String>> it = new PreOrderIterator<>(tree);

        String expected = "A(0) B(1) C(1) D(1)";
        String actual = new PathTreeIteratorToString(it).toString();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testWalk2() {
        NodeNTree<String> tree = new StringTreeBuilder().createTree(
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

        PathTreeIterator<NTreeNode<String>> it = new PreOrderIterator<>(tree);

        String expected = "A(0) B(1) B1(2) B2(2) B3(2) C(1) C1(2) C11(3) C12(3) C13(3) C2(2) C3(2) D(1)";
        String actual = new PathTreeIteratorToString(it).toString();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testWalk3() {
        NodeNTree<String> tree = new StringTreeBuilder().createTree(
                "A->B",
                "A->C",
                "A->D",
                "A->E"
        );

        PathTreeIterator<NTreeNode<String>> it = new PreOrderIterator<>(tree);

        String expected = "A(0) B(1) C(1) D(1) E(1)";
        String actual = new PathTreeIteratorToString(it).toString();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testWalk0() {
        NodeNTree<String> tree = new DataNTree<String>("A");

        NTreeNode<String> nodeA = tree.getRoot();

        PathTreeIterator<NTreeNode<String>> it = new PreOrderIterator<>(tree);

        String expected = "A(0)";
        String actual = new PathTreeIteratorToString(it).toString();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testWalk00() {
        NTree<String, NTreeNode<String>> tree = new DataNTree<>();

        NTreeNode<String> nodeA = tree.getRoot();

        PathTreeIterator<NTreeNode<String>> it = new PreOrderIterator<>(tree);

        String expected = "";
        String actual = new PathTreeIteratorToString(it).toString();

        Assert.assertEquals(expected, actual);
    }

}
