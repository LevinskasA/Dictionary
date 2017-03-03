package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import sample.Read.ReadData;

import java.util.HashMap;
import java.util.Map;

public class Controller {

    private Map<String, String> englishMap = new HashMap<>();
    private Map<String, String> lithuanianMap = new HashMap<>();

    @FXML
    private TextField englishInput;

    @FXML
    private TextField lithuanianInput;


    public void startTranslate(ActionEvent event) {
        ReadData.readFile(englishMap, lithuanianMap);
        //english - true if translating from english to lithuanian.
        boolean english = !englishInput.getText().isEmpty();
        // If both contain only whitespace, goes to else. Defaults to english if both are input.
        if (!(englishInput.getText().trim().isEmpty() && lithuanianInput.getText().trim().isEmpty())) {
            // Begins translation.
            String input;
            String translation;
            if (english) {
                input = englishInput.getText().trim().toLowerCase();
                translation = translateGivenInput(englishMap, input);
            } else {
                input = lithuanianInput.getText().trim().toLowerCase();
                translation = translateGivenInput(lithuanianMap, input);
            }

            if (!translation.isEmpty()) {
                // Translation successful.
                if (english) {
                    displayEnglishToLithuanian(input, translation);
                } else {
                    displayLithuanianToEnglish(input, translation);
                }
            } else {
                // no translation. Give option to insert a translation.
                Alert alert = new Alert(Alert.AlertType.WARNING, "");
                if (true) {
                    //TODO correctly prompt user to input new translation.
                    if (english) {
                        input = englishInput.getText();
                        alert.setContentText("No translation found. Would you like to contribute?");

                    } else {
                        input = lithuanianInput.getText();
                        alert.setContentText("Vertimas nerastas. Ar norėtumėte įvesti vertimą?");
                    }

                }


            }
            // Done translating. Clearing text fields.
            englishInput.setText("");
            lithuanianInput.setText("");

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No input?!");
            alert.show();
        }


    }

    // If finds key, returns translation. Otherwise defaults to "";
    private String translateGivenInput(Map<String, String> currentMap, String input) {
        return currentMap.getOrDefault(input, "");

    }

    private void displayEnglishToLithuanian(String input, String translation) {
        String message = input + " translates into: " + translation;
        Alert alert = new Alert(Alert.AlertType.WARNING, message);
        alert.show();
    }

    private void displayLithuanianToEnglish(String input, String translation) {
        String message = input + " verčiamas į: " + translation;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message);
        alert.show();
    }

    public void addTranslation(String english, String lithuanian) {
        englishMap.put(english, lithuanian);
        lithuanianMap.put(lithuanian, english);
    }


}
