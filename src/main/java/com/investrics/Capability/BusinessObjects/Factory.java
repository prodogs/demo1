package com.investrics.Capability.BusinessObjects;

import org.bson.Document;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Factory {

    public Factory() {

    }

    public static DataObject CreateDataObjectFromDocument(String className, Document newDocument) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName(className);
        Constructor<?> ctor = clazz.getConstructor(String.class);
        DataObject dataObject = (DataObject) ctor.newInstance(new Object[] {  });
        dataObject.inflateObject(newDocument);
        return (DataObject) dataObject;
    }
}
