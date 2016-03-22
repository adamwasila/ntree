package org.wasila.ntree;

import org.junit.Assert;
import org.junit.Test;

public class IndexedNTreeTest {

    @Test
    public void testCreateSimpleTree() {
        IndexedNTree<String> tree = new IndexedNTreeImpl<>();
        tree.addChild("root");
        tree.addChild("firstChild", 0);
        tree.addChild("secondChild", 0);
        tree.addChild("thirdChild", 0);

        Assert.assertEquals("root", tree.get());
        Assert.assertEquals(3, tree.getChildrenCount(0));
        Assert.assertEquals("firstChild", tree.get(0, 0));
        Assert.assertEquals("secondChild", tree.get(0, 1));
        Assert.assertEquals("thirdChild", tree.get(0, 2));
    }

    @Test
    public void testCreateSimpleTree2() {
        IndexedNTree<String> tree = new IndexedNTreeImpl<>();
        tree.addChild("A");
        tree.addChild("A1", 0);
        tree.addChild("A2", 0, 0);

        Assert.assertEquals("A", tree.get());
        Assert.assertEquals(1, tree.getChildrenCount());
        Assert.assertEquals("A1", tree.get(0));
        Assert.assertEquals(1, tree.getChildrenCount(0));
        Assert.assertEquals("A2", tree.get(0, 0));
        Assert.assertEquals(1, tree.getChildrenCount(0, 0));
    }

    @Test
    public void addToIndexedTree() {
        IndexedNTree<String> tree = new IndexedNTreeImpl<>();
        tree.addChild("root");
        tree.addChild("firstChild", 0);
        tree.addChild("secondChild", 0);
        tree.addChild("thirdChild", 0);

        Assert.assertEquals("root", tree.get());
        Assert.assertEquals(3, tree.getChildrenCount(0));
        Assert.assertEquals("firstChild", tree.get(0, 0));
        Assert.assertEquals("secondChild", tree.get(0, 1));
        Assert.assertEquals("thirdChild", tree.get(0, 2));
    }

    @Test
    public void insertToIndexedTree() {
        IndexedNTree<String> tree = new IndexedNTreeImpl<>();
        tree.addChild("root");
        tree.insertChild("firstChild", 0, 0);
        tree.insertChild("secondChild", 0, 1);
        tree.insertChild("thirdChild", 0, 2);

        Assert.assertEquals("root", tree.get());
        Assert.assertEquals(3, tree.getChildrenCount(0));
        Assert.assertEquals("firstChild", tree.get(0, 0));
        Assert.assertEquals("secondChild", tree.get(0, 1));
        Assert.assertEquals("thirdChild", tree.get(0, 2));
    }

    @Test
    public void insertToIndexedTreeInReversedOrder() {
        IndexedNTree<String> tree = new IndexedNTreeImpl<>();
        tree.addChild("root");
        tree.insertChild("thirdChild", 0, 0);
        tree.insertChild("secondChild", 0, 0);
        tree.insertChild("firstChild", 0, 0);

        Assert.assertEquals("root", tree.get());
        Assert.assertEquals(3, tree.getChildrenCount(0));
        Assert.assertEquals("firstChild", tree.get(0, 0));
        Assert.assertEquals("secondChild", tree.get(0, 1));
        Assert.assertEquals("thirdChild", tree.get(0, 2));
    }

    @Test
    public void insertToIndexedTreeInMixedOrder() {
        IndexedNTree<String> tree = new IndexedNTreeImpl<>();
        tree.addChild("root");
        tree.insertChild("thirdChild", 0, 0);
        tree.insertChild("firstChild", 0, 1);
        tree.insertChild("secondChild", 0, 1);

        Assert.assertEquals("root", tree.get());
        Assert.assertEquals(3, tree.getChildrenCount(0));
        Assert.assertEquals("firstChild", tree.get(0, 0));
        Assert.assertEquals("secondChild", tree.get(0, 1));
        Assert.assertEquals("thirdChild", tree.get(0, 2));
    }

}
