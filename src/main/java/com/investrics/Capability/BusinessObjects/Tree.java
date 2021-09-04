package com.investrics.Capability.BusinessObjects;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;


public class Tree extends  DataObject {

    private TreeNode rootNode;
    private ObjectId rootNodeID;
    private String name;

    public Tree() {
        super("Tree");
        rootNode = null;
        name =null;
    }

    @Override
    public DataObject inflateObject(Document theDocument) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        var theDimension = new Dimension();
        theDimension.setName((String) theDocument.get("name"));
        theDimension.setDescription((String) theDocument.get("description"));
        theDimension.setLastUpdate((Date) theDocument.get("lastUpdate"));
        theDimension.setCreationTime((Date) theDocument.get("creationTime"));
        this.rootNodeID = (ObjectId) theDocument.get("rootNodeId");
        this.rootNode  = (TreeNode) new TreeNode().getOne(this.rootNodeID);

        theDimension.oid = (ObjectId) theDocument.getObjectId("_id");
        theDimension.theDocument = theDocument;
        return theDimension;
    }


    @Override
    public Document deflateObject() {

       var newDoc = new Document();
       newDoc.append("oid",this.oid);
       newDoc.append("name",this.name);
       newDoc.append("rootNodeID",this.rootNodeID);
       newDoc.append("lastUpdate",this.getLastUpate());
       newDoc.append("creationTime",this.getCreationTime());

       return newDoc;
    }

    public void SetRoot(Dimension rootDimension) {
        rootNode = new TreeNode(this,rootDimension);
    }

    public TreeNode getRoot() {
        return rootNode;
    }
    public void setRoot(TreeNode rootNode) { this.rootNode=rootNode;}

    public void addChild(TreeNode treeNode) {

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
