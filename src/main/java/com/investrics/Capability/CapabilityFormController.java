package com.investrics.Capability;

import com.investrics.Capability.BusinessObjects.Capability;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class CapabilityFormController {

    @FXML
    Button saveButton;

    @FXML
    Button onClearButton;

    @FXML
    TextField objectIDField;

    @FXML
    TextField nameField;

    @FXML
    Label noticeMessage;

    @FXML
    TextArea descriptionField;

    @FXML
    ListView capabilityListView;

    private ArrayList<Capability> capabilityList;
    private ObservableList<Capability>  observeList;
    private Capability currentSelection;

    public CapabilityFormController() {
        setCurrentSelection(null);

    }

    public void nameFieldChange(String oldValue, String newValue) {
        System.out.println("Name Changed to "+newValue);
    }

    public void descriptionFIeldChange(String oldValue, String newValue) {
        System.out.println("Description Changed to "+newValue);
    }

    @FXML
    public void initialize() {
        var aCapability = new Capability();

        var capabilityList = aCapability.getAll();

        observeList = FXCollections.observableArrayList(capabilityList);

        capabilityListView.setItems(observeList);

        nameField.textProperty().addListener((obs,oldText,newText)-> {
            nameFieldChange(oldText,newText);
        });

        descriptionField.textProperty().addListener((obs,oldText,newText)-> {
            descriptionFIeldChange(oldText,newText);
        });


        capabilityListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Capability>() {
                    public void changed(ObservableValue<? extends Capability> ov,
                                        Capability old_val, Capability new_val) {
                        handleSelectionChange(old_val,new_val);


                    }
                });
    }

    @FXML
    private void onSaveButton(ActionEvent e) {
        System.out.println("Save Button");

        var name = nameField.getText();
        var description = descriptionField.getText();

        if (name.isBlank()) {
            noticeMessage.setText("Name Can Not Be Blank");
            return;
        }
        if (description.isBlank()) {
            noticeMessage.setText("Description Can Not Be Blank");
            return;
        }

        if (currentSelection==null) {
            var newCapability = new Capability();
            newCapability.setName(nameField.getText());
            newCapability.setDescription(descriptionField.getText());

            newCapability.insert();

            noticeMessage.setText("Capability Saved");
            objectIDField.setText(newCapability.getObjectId().toString());

            observeList.add(newCapability);

            saveButton.setDisable(true);
            return;
        }

        currentSelection.setName(nameField.getText());

        var nonStringDescription = new String();
        nonStringDescription = descriptionField.getText();

        currentSelection.setDescription(nonStringDescription);
        currentSelection.update();
    }

    @FXML
    private void onClearButton(ActionEvent event) {
        System.out.println("Clear  Button");
        this.resetForm();
    }

    public void resetForm() {

      nameField.setText("");
      descriptionField.setText("");
      noticeMessage.setText("");
      objectIDField.setText("");
      saveButton.setDisable(false);
      saveButton.setText("Save New");
      currentSelection = null;
    }

    public void setCurrentSelection(Capability newCurrent) {

        this.currentSelection = newCurrent;
        if (newCurrent==null) return;

        this.saveButton.setText("Update");
        this.saveButton.setDisable(false);

        nameField.setText(newCurrent.toString());
        descriptionField.setText(newCurrent.getDescription());
        objectIDField.setText(newCurrent.getObjectId().toString());
    }

    public void setNewState() {
        this.resetForm();

    }

    public void handleSelectionChange(Capability oldValue, Capability newValue) {
        System.out.println("Capability Name "+newValue.getName());
        this.setCurrentSelection(newValue);

    }


}
