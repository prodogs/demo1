package com.investrics.Capability.BusinessObjects;

import org.bson.Document;

import java.lang.reflect.InvocationTargetException;

public class Resource extends DataObject {
    private String name;
    private String description;

    public Resource() {
        super("Resource");
    }

    @Override
    public Document deflateObject() {
        return super.deflateObject();
    }

    @Override
    public DataObject inflateObject(Document theDocument) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return super.inflateObject(theDocument);
    }
}

