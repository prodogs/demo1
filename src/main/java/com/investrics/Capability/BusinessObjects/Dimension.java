package com.investrics.Capability.BusinessObjects;

import com.investrics.Capability.CapabilityApp;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Dimension extends DataObject {

    private String description="No Descripton";
    private String name ="No Name";
    private String type="No Type";

    public Dimension() {
        super("Dimension");
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() { return this.description;}
    public void setDescription(String aDescription) { this.description = aDescription; }

    public String getType() {return this.type;}
    public void setType(String type) { this.type = type;}

    @Override
    public String toString() {return this.getName();}

    @Override
    public Document deflateObject() {
        Document newDoc = new Document().append("name",this.name);
        newDoc.append("description",this.description);
        newDoc.append("updateTime",new Date());
        newDoc.append("type",type);
        return newDoc;
    }

    @Override
    public ArrayList GetAll() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return super.GetAll();
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


}
