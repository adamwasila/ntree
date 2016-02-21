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
import org.wasila.ntree.testutils.StringTreeBuilder;

public class NTreesUnmodifableTreeTest {

    public NTree<String> createTree() {
        return new StringTreeBuilder().createTree(
                "0->1",
                "0->2",
                "0->3",
                "1->4",
                "1->5",
                "2->6",
                "2->7",
                "3->8",
                "3->9",
                "4->10",
                "4->11",
                "5->12"
        );
    }

    @Test(expected = NullPointerException.class)
    public void testNullSource() {
        NTree<?> emptyTree = NTrees.unmodifableNTree(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSetRoot() {
        NTree<String> tree = createTree();
        NTree<String> uTree = NTrees.unmodifableNTree(tree);

        uTree.setRoot("testSetRoot");

    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddChild() {
        NTree<String> tree = createTree();
        NTree<String> uTree = NTrees.unmodifableNTree(tree);

        uTree.getRootNode().addChild("testAddChild");

    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddChildWithIndex() {
        NTree<String> tree = createTree();
        NTree<String> uTree = NTrees.unmodifableNTree(tree);

        uTree.getRootNode().addChild(0, "testAddChildWithIndex");

    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddChildToChild() {
        NTree<String> tree = createTree();
        NTree<String> uTree = NTrees.unmodifableNTree(tree);

        uTree.getRootNode().getChildrenNode().iterator().next().addChild("testAddChildToChild");

    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveChild() {
        NTree<String> tree = createTree();
        NTree<String> uTree = NTrees.unmodifableNTree(tree);

        uTree.getRootNode().removeChild(0);

    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveChildNode() {
        NTree<String> tree = createTree();
        NTree<String> uTree = NTrees.unmodifableNTree(tree);

        uTree.getRootNode().removeChildNode(0);

    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveChildByName() {
        NTree<String> tree = createTree();
        NTree<String> uTree = NTrees.unmodifableNTree(tree);

        uTree.getRootNode().removeChild("1");

    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveChildByNode() {
        NTree<String> tree = createTree();
        NTree<String> uTree = NTrees.unmodifableNTree(tree);

        uTree.getRootNode().removeChildNode(uTree.getRootNode().getChildrenNode().iterator().next());

    }

    @Test
    public void testReadOnlyOperations() {
        NTree<String> tree = createTree();
        NTree<String> uTree = NTrees.unmodifableNTree(tree);

        NTreeNode<String> node = uTree.getRootNode();

        // none of these should throw
        node.getChildrenCount();
        node.getData();
        node.indexOfNode(node.getChildrenNode().iterator().next());
        node.getChildNodeOf(0);
        node.getChildren();
    }


}
