package com.investrics.Capability;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Database {
    private MongoClient mongoClient;
    private MongoCredential mongoCredential;
    private MongoDatabase mongoDatabase;


    public Database() {
        mongoClient =  new MongoClient( "localhost" , 27017 );
        mongoCredential = MongoCredential.createCredential("capuser", "JavaCap",
                "password".toCharArray());
        System.out.println("Connected to the database successfully");

        this.mongoDatabase = this.mongoClient.getDatabase("JavaCap");
        System.out.println("Credentials ::"+ this.mongoCredential);


    }

    public MongoDatabase GetMongoDatabase() {
        return mongoDatabase;
    }

    public MongoCollection getCollection(String collectionName) {

        MongoCollection theCollection = mongoDatabase.getCollection(collectionName);
        return theCollection;
    }
}
