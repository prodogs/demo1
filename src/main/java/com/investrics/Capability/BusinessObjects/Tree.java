package com.investrics.Capability.BusinessObjects;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;


public class Tree extends  DataObject {

    public TreeNode rootNode;
    public String name;

    public Tree() {
        super("Tree");
        rootNode = null;
        name =null;
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

    public void SetRoot(Dimension rootDimension) {
        rootNode = new TreeNode(this,rootDimension);
    }

    public TreeNode GetRoot() {
        return rootNode;
    }

    public void addChild(TreeNode treeNode) {
        ;
    }
    public void removeChild(TreeNode treeNode) {
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void delete() {
        super.delete();
    }

    public void insert() {
    }
}
