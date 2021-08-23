package com.investrics.Capability.BusinessObjects;

import org.bson.types.ObjectId;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class CapabilityTree extends  DataObject {

    public CapabilityTreeNode rootNode;
    public String name;
    public ArrayList<CapabilityTreeNode> children;

    public CapabilityTree() {
        super("CapabilityTree");
        rootNode = null;
        name =null;
    }

    public void SetRoot(Capability rootCapability) {
        rootNode = new CapabilityTreeNode(rootCapability);
    }

    public CapabilityTreeNode GetRoot() {
        return rootNode;
    }
}
