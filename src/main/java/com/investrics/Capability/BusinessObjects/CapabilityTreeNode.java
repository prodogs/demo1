package com.investrics.Capability.BusinessObjects;

import org.bson.types.ObjectId;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CapabilityTreeNode extends DataObject{


    private Capability                     nodeCapability;
    private ObjectId                       capabilityID;
    private String                         name;
    private ArrayList<CapabilityTreeNode> childrenNodes;

    public CapabilityTreeNode() {
        super("CapabilityTreeNode");
    }

    public CapabilityTreeNode(Capability aCapability) {
        super("CapabilityTreeNode");
        this.nodeCapability = aCapability;
        this.capabilityID = aCapability.getObjectId();
        this.name = aCapability.getName();
    }

    public String GetName() {
        return name;
    }

    public Capability GetCapability() {
        return nodeCapability;
    }

    public ObjectId GetCapabilityID() {
        return capabilityID;
    }

    public void SetCapabilityId(ObjectId capabilityID) {
        this.capabilityID = capabilityID;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public CapabilityTreeNode addChildCapability(Capability childCapability) {
        var capabilityTreeNode = new CapabilityTreeNode(childCapability);
        childrenNodes.add(capabilityTreeNode);
        return capabilityTreeNode;
    }

    public CapabilityTreeNode addChildCapabilityNode(CapabilityTreeNode childNode) {
        childrenNodes.add(childNode);
        return childNode;
    }



    public CapabilityTreeNode removeChildNode(CapabilityTreeNode childNode) {
        for( int i = 0; i < childrenNodes.size(); i++){
            var aNode = childrenNodes.get(i);
            if (aNode.nodeCapability.getObjectId() == childNode.GetCapabilityID()) {
                childrenNodes.remove(aNode);
                return aNode;
            }
        }
        return null;
    }

    public CapabilityTreeNode removeCapabilityChild(Capability childCapability) {
        for( int i = 0; i < childrenNodes.size(); i++){
            var aNode = childrenNodes.get(i);
            if (aNode.nodeCapability.getObjectId() == childCapability.getObjectId()) {
                childrenNodes.remove(aNode);
                return aNode;
            }
        }
        return null;
    }


}

