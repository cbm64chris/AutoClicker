package com.fluffyluffs;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.scene.robot.Robot;
import javafx.util.StringConverter;

public class AutoClickerController implements Initializable {
    private Timer timer;
    private static Clicker clicker;
    
    @FXML
    private StackPane autoclickerPane;
    @FXML
    private Spinner<Double> spinner;
    @FXML
    private Label clickedLbl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        SpinnerValueFactory.DoubleSpinnerValueFactory doubleSpinnerValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.1, 5.0, 1.0, 0.1);
        doubleSpinnerValueFactory.setConverter(new StringConverter<Double>() {
            @Override
            public String toString(Double t) {
                return String.format("%s %s", t, t == 1 ? "second" : "seconds");
            }

            @Override
            public Double fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        spinner.setValueFactory(doubleSpinnerValueFactory);

        autoclickerPane.setOnKeyPressed(eh -> {
            if (eh.getCode().equals(new KeyCodeCombination(KeyCode.S, KeyCombination.META_DOWN).getCode())) {
                startClicking();
//                System.out.println("Started clicking...");
            }

            if (eh.getCode().equals(new KeyCodeCombination(KeyCode.E, KeyCombination.META_DOWN).getCode())) {
                stopClicking();
                System.out.println("Stopped clicking...");
            }
        });
    }

    private void startClicking() {
        clicker = new Clicker(new Robot(), clickedLbl);
        if (!clicker.running()) {
            timer = new Timer();
            System.out.println(((Number) (spinner.getValue() * 1000)).longValue());

            timer.scheduleAtFixedRate(clicker, 0, ((Number) (spinner.getValue() * 1000)).longValue());
        }
        //spinner.setDisable(true);
    }

    private void stopClicking() {
        if (clicker.running()) {
            timer.cancel();
            Platform.runLater(() -> {
                clickedLbl.setText("Stopped");
                //spinner.setDisable(false);
            });
        }

    }
}
