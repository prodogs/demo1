package com.investrics.Capability.BusinessObjects;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class DataObject extends  Object {

    protected ObjectId oid;
    protected Document theDocument;
    protected String collectionName;

    public DataObject(String collectionName) {
        this.collectionName = collectionName;

    }

    public ObjectId getObjectId() {
        return oid;
    }

    public Document GetDocument() {
        return theDocument;
    }
    public void insert() {

    }
    public void update() {

    }
    public void delete() {

    }
}
