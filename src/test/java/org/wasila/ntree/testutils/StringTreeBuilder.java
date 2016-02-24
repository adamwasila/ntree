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
package org.wasila.ntree.testutils;

import org.wasila.ntree.NTree;
import org.wasila.ntree.impl.NTreeImpl;
import org.wasila.ntree.NTreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTreeBuilder {

    Map<String, NTreeNode<String>> nodesCache = new HashMap<>();

    public NTreeImpl<String> createTree(String description, String... descriptions) {
        StringBuilder sb = new StringBuilder();
        sb.append(description);
        for (String desc : descriptions) {
            sb.append(" ");
            sb.append(desc);
        }

        String matchingString = sb.toString();
        Pattern pattern = Pattern.compile("(\\S+)\\s*->\\s*(\\S+)");
        Matcher matcher = pattern.matcher(matchingString);

        NTreeImpl<String> tree = new NTreeImpl<>();

        while (matcher.find()) {
            String valueFrom = matcher.group(1);
            String valueTo = matcher.group(2);

            if (tree.getRootNode() == null) {
                tree.setRoot(valueFrom);
                tree.addChild(valueFrom, valueTo);
                continue;
            }

            tree.addChild(valueFrom, valueTo);

        }

        return tree;
    }

}
