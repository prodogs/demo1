package com.investrics.Capability.BusinessObjects;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Date;

public class DimensionType extends DataObject{

    private String name;
    private String description;

    public DimensionType() {
        super("DimensionType");
    }

    @Override
    public DataObject inflateObject(Document theDocument) {

        var theDimension = new DimensionType();
        theDimension.setName((String) theDocument.get("name"));
        theDimension.setDescription((String) theDocument.get("description"));
        theDimension.setLastUpdate((Date) theDocument.get("lastUpdate"));
        theDimension.setCreationTime((Date) theDocument.get("creationTime"));

        theDimension.oid = (ObjectId) theDocument.getObjectId("_id");
        theDimension.theDocument = theDocument;
        return theDimension;
    }

    @Override
    public Document deflateObject() {
        Document newDoc = new Document().append("name",this.name);
        newDoc.append("description",this.description);
        newDoc.append("updateTime",new Date());
        return newDoc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
