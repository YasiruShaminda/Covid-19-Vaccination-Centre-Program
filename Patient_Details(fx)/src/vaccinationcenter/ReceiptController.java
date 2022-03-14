package vaccinationcenter;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ReceiptController
{

    @FXML private Label boothNumber;
    @FXML private Label fName;
    @FXML private Label sName;
    @FXML private Label pAge;
    @FXML private Label pIdNumber;
    @FXML private Label pCity;
    @FXML private Label pVaccType;
    @FXML private Label date;
    @FXML private Label time;

    @FXML private Button printButton;
    @FXML private Button okButton;


    /**
     * The data obtained from the Details window is printed on the labels here.
     * @param boothNum booth number
     * @param fname first name of the patient
     * @param sname surname of the patient
     * @param page age of the patient
     * @param pcity city of the patient
     * @param pIdnumber NIC or Passport of the patient
     * @param vaccType vaccination type of the patient
     */
    @FXML
    public void display(String boothNum, String fname, String sname, String page, String pcity, String pIdnumber, String vaccType)
    {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();

        boothNumber.setText(boothNum);
        fName.setText(fname);
        sName.setText(sname);
        pAge.setText(page);
        pCity.setText(pcity);
        pIdNumber.setText(pIdnumber);
        pVaccType.setText(vaccType);

        date.setText(String.valueOf(localDate));      //set local date into receipt when clicked generate receipt button
        time.setText(String.valueOf(localTime.format(DateTimeFormatter.ofPattern("HH : mm : ss"))));     //set local time into receipt when clicked generate receipt button
    }


    /**
     * An alert will pop up when the print button is clicked.
     */
    @FXML
    public void print()
    {
        new alert(Alert.AlertType.WARNING);
    }


    /**
     * Clicking the OK button closes the receipt window.
     */
    @FXML
    public void closeWindow() {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }


    /**
     * This generates an alert for the print button.
     */
    public class alert extends Alert
    {
        {
            getDialogPane().getStylesheets().add(getClass().getResource("VaccinationCentreGUI.css").toExternalForm());
        }

        public alert(AlertType alertType)
        {
            super(alertType);
            setTitle("Device Not Found");
            setContentText("Printer Not Found!!!");
            show();
        }
    }
}
