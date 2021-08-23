package com.investrics.Capability.BusinessObjects;

import com.investrics.Capability.CapabilityApp;
import com.mongodb.MongoException;
import com.mongodb.bulk.UpdateRequest;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Iterator;

public class Capability extends DataObject {

    private String description;
    private String name;

    public Capability() {
        super("Capability");
        name = "No Name";
        description = "";
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() { return this.description;}
    public void setDescription(String aDescription) { this.description = aDescription; }

    @Override
    public String toString() {
        return this.getName();
    }
    public void insert() {

        Document document = new Document();
        document.append("name",name);
        document.append("description",description);

        MongoCollection<Document> collection = CapabilityApp.theDatabase.GetMongoDatabase().getCollection("Capability");
        collection.insertOne(document);

        this.oid = document.getObjectId("_id");

        System.out.println("Capability Added  "+this.oid.toString());
        System.out.println(document);


    }

    public void update() {
        MongoCollection capCollection =         CapabilityApp.theDatabase.getCollection(collectionName);

        Document query = new Document().append("_id",this.oid);
        Document newDoc = new Document().append("name",this.name);
        newDoc.append("description",this.description);

        Document updates = new Document().append("$set",newDoc);

      //  updates.append("description",this.description);

        try {
            capCollection.updateOne(query,updates);

          //   System.out.println("Modified document count: " + result.getModifiedCount());
          //  System.out.println("Upserted id: " + result.getUpsertedId()); // only contains a value when an upsert is performed
        } catch (MongoException me) {
            System.err.println("Unable to update due to an error: " + me);
        }

    }


    public ArrayList<Capability> getAll() {

        MongoCollection<Document> collection = CapabilityApp.theDatabase.GetMongoDatabase().getCollection("Capability");
        FindIterable<Document> iterDoc = collection.find();

        System.out.println("Getting Capability Collection");

        var theList = new ArrayList<Capability>();
        int i = 1;
        // Getting the iterator
        Iterator it = iterDoc.iterator();
        var theDocument = new Document();

        while (it.hasNext()) {
             theDocument = (Document) it.next();
            var theCapability = new Capability();

            theCapability.setName((String) theDocument.get("name"));
            theCapability.setDescription((String) theDocument.get("description"));
            theCapability.oid = (ObjectId) theDocument.getObjectId("_id");
            theCapability.theDocument = theDocument;
            theList.add(theCapability);
            i++;
        }
        return  theList;
    }
}
