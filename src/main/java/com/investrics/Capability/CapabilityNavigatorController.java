package com.investrics.Capability;

import com.investrics.Capability.BusinessObjects.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CapabilityNavigatorController {

    public FXMLLoader capabilityFormScene = null;
    @FXML
    BorderPane mainView;

    @FXML
    Button capabilityButton;

    public void CapabilityNavigatorController() {
    }

    @FXML
    public void initialize() {

    }

    @FXML
    public void onCapabilityButton(ActionEvent event) throws IOException {
        Logger.Log("Capability Button");
        createCapabilityView();;
    }


    public void createCapabilityView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CapabilityApp.class.getResource("CapabilityForm.fxml"));

        /*
        Capability aCapability = new Capability();

        var theList = aCapability.getAll();

        for (int i=0;i < theList.size();i++) {
            var theCapability = theList.get(i);
            System.out.println("Capability Name "+theCapability.getName());

        }
*/
        Stage newStage = new Stage();
        Scene newScene = new Scene(fxmlLoader.load(), 800, 600);

        newScene.getRoot().setStyle("-fx-font-family: 'serif'");
        AnchorPane rootPane = (AnchorPane) newScene.getRoot();
        mainView.setCenter(rootPane);

        /*
        newStage .setTitle("Capability Form");
        newStage .setScene(newScene);
        newStage .show(); */
    }

}
