package com.test.jd;

import java.util.*;

public class Node {
    private String id;
    private String name;
    private String parentId;
    private List<Node> children;

    public Node(String id, String name, String parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    // getters and setters
    public String getParentId() {
        return parentId;
    }
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node node) {
        this.children = Optional.ofNullable(children).orElseGet(ArrayList::new);
        children.add(node);
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }


}

class TreeBuilder {
    public static List<Node> buildTree(List<Node> nodes) {
        List<Node> ret = new ArrayList<>();
        Map<String, Node> nodeMap = new HashMap<>();
        Node root = null;

        // create a map of all nodes, indexed by their id
        for (Node node : nodes) {
            nodeMap.put(node.getId(), node);
        }

        // iterate over all nodes to build the tree
        for (Node node : nodes) {
            String parentId = node.getParentId();

            // if the node has no parent, it's the root node
            // 没有父节点
            if (parentId == null) {
                ret.add(node);
            } else {
                // add the node as a child of its parent
                Node parent = nodeMap.get(parentId);
                if (parent != null) {
                    parent.addChild(node);
                }
            }
        }
        return ret;

    }


    public static void main(String[] args) {
        Node node3 = new Node("3","ccc","2");
        Node node2 = new Node("2","bbb","1");
        Node node1 = new Node("1","aaa","0");

        List<Node> nodes = Arrays.asList(node1, node2, node3);

        List<Node> treeNode = buildTree(nodes);

        System.out.println();



    }

}
