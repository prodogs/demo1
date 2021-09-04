package com.investrics.Capability.BusinessObjects;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;

public class Relationship extends DataObject {

    private ObjectId fromId;
    private ObjectId toId;
    private String fromClass;
    private String toClass;

    public  Relationship() {
        super("Relationship");
    }

    public ObjectId getFromId() {
        return fromId;
    }
    public ObjectId getToId() {
        return toId;
    }
    public String   getFromClass() {
        return fromClass;
    }
    public String   getToClass() {
        return toClass;
    }
    public void setFromId(ObjectId fromId) {
        this.fromId = fromId;
    }
    public void setToId(ObjectId toId) {
        this.toId = toId;
    }
    public void setFromClass(String fromClass) {
        this.fromClass = fromClass;
    }
    public void setToClass(String toClass) {
        this.toClass = toClass;
    }

    @Override
    public Document deflateObject() {
        var newDoc = new Document();
        newDoc.append("toId",this.toId);
        newDoc.append("fromId",this.fromId);
        newDoc.append("fromClass",this.fromClass);
        newDoc.append("toClass",this.toClass);
        newDoc.append("updateTime",new Date());

        return newDoc;
    }

    @Override
    public DataObject inflateObject(Document theDocument) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        var theRelationship = new Relationship();
        theRelationship.setFromId((ObjectId) theDocument.get("fromId"));
        theRelationship.setToId((ObjectId) theDocument.get("toId"));

        theRelationship.setFromClass((String) theDocument.get("fromClass"));
        theRelationship.setToClass((String) theDocument.get("toClass)"));
        theRelationship.setLastUpdate((Date) theDocument.get("lastUpdate"));
        theRelationship.setCreationTime((Date) theDocument.get("creationTime"));
        return super.inflateObject(theDocument);
    }


}
