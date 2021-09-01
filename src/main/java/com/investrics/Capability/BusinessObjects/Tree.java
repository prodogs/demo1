package com.investrics.Capability.BusinessObjects;

import java.util.ArrayList;


public class Tree extends  DataObject {

    public TreeNode rootNode;
    public String name;

    public Tree() {
        super("CapabilityTree");
        rootNode = null;
        name =null;
    }

    public void SetRoot(Dimension rootDimension) {
        rootNode = new TreeNode(this,rootDimension);
    }

    public TreeNode GetRoot() {
        return rootNode;
    }
}
