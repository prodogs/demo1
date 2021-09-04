package com.investrics.Capability.BusinessObjects;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;

public class TreeNode extends DataObject{

    private Dimension   nodeDimension;
    private String      name;
    private String      description;
    private Tree        tree;
    private ObjectId    treeOID;
    private ObjectId    dimensionOID;
    private ArrayList<TreeNode> childrenNodes;

    public TreeNode() {
        super("TreeNode");
    }

    @Override
    public DataObject inflateObject(Document theDocument) {

        var theNode = new TreeNode();
        theNode.setName((String) theDocument.get("name"));
        theNode.setDescription((String) theDocument.get("description"));
        theNode.setTreeOID((ObjectId) theDocument.get("treeOID"));
        theNode.setLastUpdate((Date) theDocument.get("lastUpdate"));
        theNode.setCreationTime((Date) theDocument.get("creationTime"));

        theNode.oid = (ObjectId) theDocument.getObjectId("_id");
        theNode.theDocument = theDocument;
        return theNode;
    }

    @Override
    public Document deflateObject() {
        return super.deflateObject();
    }

    public TreeNode(Tree theTree, Dimension aDimension) {
        super("CapabilityTreeNode");
        this.nodeDimension = aDimension;
        this.dimensionOID = aDimension.getObjectId();
        this.name = aDimension.getName();
    }
    
    public Dimension GetDimension() {
        return nodeDimension;
    }

    public ObjectId GetCapabilityID() {
        return dimensionOID;
    }

    public void SetDimensionId(ObjectId dimensionID) {
        this.dimensionOID = dimensionID;
    }

    public TreeNode addChild(Dimension childDimension) {
        var capabilityTreeNode = new TreeNode(this.tree, childDimension);
        childrenNodes.add(capabilityTreeNode);
        return capabilityTreeNode;
    }

    public TreeNode addChildNode(TreeNode childNode) {
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

    public TreeNode removeChild(Dimension childDimension) {
        for( int i = 0; i < childrenNodes.size(); i++){
            var aNode = childrenNodes.get(i);
            if (aNode.nodeDimension.getObjectId() == childDimension.getObjectId()) {
                childrenNodes.remove(aNode);
                return aNode;
            }
        }
        return null;
    }
    
    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setTree(Tree theTree) {
        this.tree = theTree;
        this.treeOID = theTree.getObjectId();
    }
    public ObjectId getTreeOID() {
        return treeOID;
    }
    public void setTreeOID(ObjectId treeOID) {
        this.treeOID = treeOID;
    }
    public Tree getTree() {
        return tree;
    }


}

