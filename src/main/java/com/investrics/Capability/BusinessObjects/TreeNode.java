package com.investrics.Capability.BusinessObjects;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;

public class TreeNode extends DataObject{


    private Dimension   nodeDimension;
    private Tree        theTree;
    private ObjectId    treeOID;
    private ObjectId    dimensionOID;
    private String      name;
    private ArrayList<TreeNode> childrenNodes;

    public TreeNode() {
        super("TreeNode");
    }

    @Override
    public DataObject inflateObject(Document theDocument) {

        var theDimension = new Dimension();
        theDimension.setName((String) theDocument.get("name"));
        theDimension.setDescription((String) theDocument.get("description"));
        theDimension.setLastUpdate((Date) theDocument.get("lastUpdate"));
        theDimension.setCreationTime((Date) theDocument.get("creationTime"));

        theDimension.oid = (ObjectId) theDocument.getObjectId("_id");
        theDimension.theDocument = theDocument;
        return theDimension;
    }

    public TreeNode(Tree theTree, Dimension aDimension) {
        super("CapabilityTreeNode");
        this.nodeDimension = aDimension;
        this.dimensionOID = aDimension.getObjectId();
        this.name = aDimension.getName();
    }

    public String GetName() {
        return name;
    }

    public Dimension GetCapability() {
        return nodeDimension;
    }

    public ObjectId GetCapabilityID() {
        return dimensionOID;
    }

    public void SetCapabilityId(ObjectId capabilityID) {
        this.dimensionOID = capabilityID;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public TreeNode addChildCapability(Dimension childDimension) {
        var capabilityTreeNode = new TreeNode(this.theTree, childDimension);
        childrenNodes.add(capabilityTreeNode);
        return capabilityTreeNode;
    }

    public TreeNode addChildCapabilityNode(TreeNode childNode) {
        childrenNodes.add(childNode);
        return childNode;
    }



    public TreeNode removeChildNode(TreeNode childNode) {
        for( int i = 0; i < childrenNodes.size(); i++){
            var aNode = childrenNodes.get(i);
            if (aNode.nodeDimension.getObjectId() == childNode.GetCapabilityID()) {
                childrenNodes.remove(aNode);
                return aNode;
            }
        }
        return null;
    }

    public TreeNode removeCapabilityChild(Dimension childDimension) {
        for( int i = 0; i < childrenNodes.size(); i++){
            var aNode = childrenNodes.get(i);
            if (aNode.nodeDimension.getObjectId() == childDimension.getObjectId()) {
                childrenNodes.remove(aNode);
                return aNode;
            }
        }
        return null;
    }


}

