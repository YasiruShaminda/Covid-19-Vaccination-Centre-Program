package vaccinationcenter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class DetailsController
{
    @FXML private TextField firstName;
    @FXML private TextField surName;
    @FXML private TextField age;
    @FXML private TextField city;
    @FXML private TextField idNumber;

    @FXML private RadioButton rbAstra;
    @FXML private RadioButton rbSino;
    @FXML private RadioButton rbPfizer;

    @FXML private RadioButton rbBooth1;
    @FXML private RadioButton rbBooth2;
    @FXML private RadioButton rbBooth3;
    @FXML private RadioButton rbBooth4;
    @FXML private RadioButton rbBooth5;
    @FXML private RadioButton rbBooth6;

    @FXML private Button receiptButton;



    String vaccType;

    /**
     * This enables and disables the radio buttons with booth numbers,
     * which are relevant for a patient to add to the booths
     * according to the requested vaccination.
     */
    @FXML
    public void getVaccType()
    {
        if (rbAstra.isSelected())
        {
            rbBooth1.setDisable(false);
            rbBooth2.setDisable(false);
            rbBooth3.setDisable(true);
            rbBooth4.setDisable(true);
            rbBooth5.setDisable(true);
            rbBooth6.setDisable(true);
            vaccType = rbAstra.getText();

        }
        else
        {
            rbBooth1.setSelected(false);
            rbBooth2.setSelected(false);
        }

        if (rbSino.isSelected())
        {
            rbBooth1.setDisable(true);
            rbBooth2.setDisable(true);
            rbBooth3.setDisable(false);
            rbBooth4.setDisable(false);
            rbBooth5.setDisable(true);
            rbBooth6.setDisable(true);
            vaccType = rbSino.getText();

        }
        else
        {
            rbBooth3.setSelected(false);
            rbBooth4.setSelected(false);
        }

        if (rbPfizer.isSelected())
        {
            rbBooth1.setDisable(true);
            rbBooth2.setDisable(true);
            rbBooth3.setDisable(true);
            rbBooth4.setDisable(true);
            rbBooth5.setDisable(false);
            rbBooth6.setDisable(false);
            vaccType = rbPfizer.getText();
        }
        else
        {
            rbBooth5.setSelected(false);
            rbBooth6.setSelected(false);
        }
    }


    /**
     * The Details window closes and the receipt window opens.
     * The data entered by the user is sent to the receiptController.
     * @throws IOException
     */
    @FXML
    public void ganerateReceipt() throws IOException
    {
        String fName = firstName.getText();
        String sName = surName.getText();
        String pAge = age.getText();
        String pCity = city.getText();
        String pIdNumber = idNumber.getText();
        String boothNumber = setBoothNumber();

        Stage stage = (Stage) receiptButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientReceipt.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Patient Receipt");

        ReceiptController receiptController = loader.getController();
        receiptController.display(boothNumber, fName, sName, pAge, pCity, pIdNumber, vaccType);  //passes the values to the receiptController

        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }


    /**
     * Reads the booth number associated with the selected radio button.
     * @return the booth number selected.
     */
    @FXML
    public String setBoothNumber()
    {
        if (rbBooth1.isSelected())
            return "1";
        else if (rbBooth2.isSelected())
            return "2";
        else if (rbBooth3.isSelected())
            return "3";
        else if (rbBooth4.isSelected())
            return "4";
        else if (rbBooth5.isSelected())
            return "5";
        else if (rbBooth6.isSelected())
            return "6";

        return null;
    }

}
