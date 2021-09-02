package com.investrics.Capability.BusinessObjects;

import org.bson.Document;

public class Logger extends DataObject {

    private String logMessage;

    public Logger(String collectionName) {
        super(collectionName);
    }

    public Logger() {
        super("Logger");
    }

    static public void Log(String logString) {
        System.out.println(logString);
    }

    @Override
    public DataObject inflateObject(Document theDocument) {

        var newObject = new Logger();
        newObject.logMessage = (String) theDocument.get("logMessage");
        return newObject;
    }

    @Override
    public Document deflateObject() {
        var newDocument = new Document();
        newDocument.append("description",this.logMessage);
        return newDocument;
    }
}
