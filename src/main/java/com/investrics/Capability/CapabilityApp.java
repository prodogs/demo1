package com.investrics.Capability;
import com.investrics.Capability.BusinessObjects.Dimension;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.DrbgParameters;

public class CapabilityApp extends Application {
    public static Database theDatabase;
    public static Stage mainStage;

    private Scene capabilityFormScene = null;
    private Scene navigationScene = null;

    @Override
    public void start(Stage stage) throws IOException {

        theDatabase = new Database();
        CapabilityApp.mainStage = stage;

       // capabilityFormScene = createCapabilityForm();
        navigationScene = createNavigationScene();

        CapabilityApp.mainStage .setTitle("Navigation");
        CapabilityApp.mainStage .setScene(navigationScene);
        CapabilityApp.mainStage .show();

    }

    public Scene createCapabilityForm() throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        FXMLLoader fxmlLoader = new FXMLLoader(CapabilityApp.class.getResource("CapabilityForm.fxml"));

        theDatabase = new Database();

        Dimension aDimension = new Dimension();

        var theList = aDimension.GetAll();

        for (int i=0;i < theList.size();i++) {
            var theCapability = (Dimension) theList.get(i);
            System.out.println("Object String"+theCapability.getName());

        }

        Scene newScene = new Scene(fxmlLoader.load(), 800, 600);

        newScene.getRoot().setStyle("-fx-font-family: 'serif'");

        return newScene;


    }

    public Scene createNavigationScene()  throws IOException  {

        FXMLLoader fxmlLoader = new FXMLLoader(CapabilityApp.class.getResource("CapabilityNavigator.fxml"));

        Stage aStage = new Stage();
        aStage.setTitle("Stage 2");
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        return scene;
    }

    public static void main(String[] args) {
        launch();
    }
}