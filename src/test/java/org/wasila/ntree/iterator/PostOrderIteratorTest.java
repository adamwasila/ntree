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
import org.wasila.ntree.*;
import org.wasila.ntree.testutils.StringTreeBuilder;
import org.wasila.ntree.testutils.PathTreeIteratorToString;

public class PostOrderIteratorTest {

    static PathTreeIterator<NTreeNode<String>> IteratorUnderTest(NodeNTree<String> tree) {
        return new PostOrderIterator<>(tree);
    }

    @Test
    public void iteratorMultipleHasNextCalls() {
        NodeNTree<String> tree = new StringTreeBuilder().createTree(
                "A->B",
                "A->C",
                "A->D"
        );

        PathTreeIterator<NTreeNode<String>> it = IteratorUnderTest(tree);

        it.hasNext();
        it.hasNext();
        it.hasNext();
        it.hasNext();
        it.hasNext();

        NTreePathExt<NTreeNode<String>> val = it.next();

        String expected = "B";
        String actual = val.getLast().getData();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void iteratorNoHasNextCall() {
        NodeNTree<String> tree = new StringTreeBuilder().createTree(
                "A->B",
                "A->C",
                "A->D"
        );

        PathTreeIterator<NTreeNode<String>> it = IteratorUnderTest(tree);

        NTreePathExt<NTreeNode<String>> val = it.next();

        String expected = "B";
        String actual = val.getLast().getData();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testWalk0() {
        NodeNTreeImpl<String> tree = new NodeNTreeImpl<String>("A");

        NTreeNode<String> nodeA = tree.getRoot();

        PathTreeIterator<NTreeNode<String>> it = IteratorUnderTest(tree);

        String expected = "A(0)";
        String actual = new PathTreeIteratorToString(it).toString();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testWalk00() {
        NodeNTreeImpl<String> tree = new NodeNTreeImpl<String>();

        NTreeNode<String> nodeA = tree.getRoot();

        PathTreeIterator<NTreeNode<String>> it = IteratorUnderTest(tree);

        String expected = "";
        String actual = new PathTreeIteratorToString(it).toString();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testWalk1() {
        NodeNTree<String> tree = new StringTreeBuilder().createTree(
                "A->B",
                "A->C",
                "A->D"
                );

        PathTreeIterator<NTreeNode<String>> it = IteratorUnderTest(tree);

        Assert.assertEquals("B(1) C(1) D(1) A(0)", new PathTreeIteratorToString(it).toString());
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

        PathTreeIterator<NTreeNode<String>> it = IteratorUnderTest(tree);

        Assert.assertEquals("B1(2) B2(2) B3(2) B(1) C11(3) C12(3) C13(3) C1(2) C2(2) C3(2) C(1) D(1) A(0)",
                new PathTreeIteratorToString(it).toString());
    }

}
