/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fluffyluffs;

import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.robot.Robot;

/**
 *
 * Clicker
 */
public class Clicker extends TimerTask {

        private final Robot robot;
        private final Label lbl;
        private int i = 0;
        private boolean isRunning;

        public Clicker(Robot robot, Label lbl) {
            this.robot = robot;
            this.lbl = lbl;
        }

        @Override
        public void run() {
            isRunning = true;
            Platform.runLater(() -> {
                robot.mousePress(MouseButton.PRIMARY);
                lbl.setText("Clicked " + i++ + " times");
            });
        }

        @Override
        public boolean cancel() {
            isRunning = false;
            return super.cancel();
        }

        public boolean running() {
            return isRunning;
        }
    }
