package com.investrics.Capability.BusinessObjects;

import com.investrics.Capability.CapabilityApp;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class DataObject extends  Object {

    protected ObjectId oid;
    protected Document theDocument;
    protected static String collectionName;
    protected Date lastUpdate;
    protected Date creationTime;

    public DataObject(String collectionName) {
        this.collectionName = collectionName;
    }

    public ObjectId getObjectId() {
        return oid;
    }

    public Document GetDocument() {
        return theDocument;
    }

    // Generic Insert
    public void insert() {
        var insertDocument = this.deflateObject();
        insertDocument.append("creationTime", new Date());
        insertDocument.append("lastUpdate", new Date());

        MongoCollection<Document> collection = GetCollection();

        collection.insertOne(insertDocument);

        this.oid = insertDocument.getObjectId("_id");

        System.out.println("Capability Added  "+this.oid.toString());
        System.out.println(insertDocument);

    }
    public void update() {
        MongoCollection capCollection =  DataObject.GetCollection();

        Document query = new Document().append("_id",this.oid);
        var newDoc = this.deflateObject();

        Document updates = new Document().append("$set",newDoc);

        //  updates.append("description",this.description);

        try {
            capCollection.updateOne(query,updates);

            //   System.out.println("Modified document count: " + result.getModifiedCount());
            //  System.out.println("Upserted id: " + result.getUpsertedId()); // only contains a value when an upsert is performed
        } catch (MongoException me) {
            Logger.Log("Unable to update due to an error: " + me);
        }
    }
    public void delete() {
    }

    public Document deflateObject() {
        return new Document();
    }
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getLastUpate() {
        return this.lastUpdate;
    }

    public Date getCreationTime() {
        return this.creationTime;
    }

    public static String GetCollectionName() {
        return collectionName;

    }
    public static MongoCollection GetCollection() {
        return CapabilityApp.theDatabase.getCollection(collectionName);

    }

    public  ArrayList GetAll() {

        MongoCollection<Document> collection = CapabilityApp.theDatabase.GetMongoDatabase().getCollection(collectionName);
        FindIterable<Document> iterDoc = collection.find();

        Logger.Log("Getting"+DataObject.GetCollection()+" Collection");

        var theList = new ArrayList<>();
        int i = 1;
        // Getting the iterator
        Iterator it = iterDoc.iterator();

        var theDocument = new Document();

        while (it.hasNext()) {
            theDocument = (Document) it.next();
            var theDataObject = this.inflateObject(theDocument);
            theList.add(theDataObject);
            i++;
        }
        return  theList;
    }

    public DataObject inflateObject(Document theDocument) {
        return null;
    }

    public  void Delete(DataObject deleteThis) {
        Document query = new Document().append("_id",deleteThis.oid);
        MongoCollection capCollection =  Dimension.GetCollection();
        try {
            capCollection.deleteOne(query);

        } catch (MongoException me) {
            Logger.Log("Unable to delete due to an error: " + me);
        }

    }
}
