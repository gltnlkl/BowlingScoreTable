package com.gulukal.europaceagtaskgu;


import com.gulukal.europaceagtaskgu.utils.TryShut;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.HashMap;
import java.util.Map;


public class HelloController {

    private TryShut tryShut;
    private int roundCount = 0;
    private int shutCount = 0;
    private int gameScore;
    private int rountScore;
    private boolean isStrike = false;


    @FXML
    private Label round11;

    @FXML
    private Label round12;

    @FXML
    private Label round21;

    @FXML
    private Label round22;

    @FXML
    private Label round31;

    @FXML
    private Label round32;

    @FXML
    private Label total1;

    @FXML
    private Label total2;

    @FXML
    private Label total3;

    @FXML
    private Button num0;

    @FXML
    private Button num1;

    @FXML
    private Button num10;

    @FXML
    private Button num2;

    @FXML
    private Button num3;

    @FXML
    private Button num4;

    @FXML
    private Button num5;

    @FXML
    private Button num6;

    @FXML
    private Button num7;

    @FXML
    private Button num8;

    @FXML
    private Button num9;

    Map<Integer, Button> buttonMap;
    Map<Integer, Label> labelMap;
    Map<Integer, Label> totalLabelMap;

    public void initController() {

        buttonMap = new HashMap<Integer, Button>() {
            {
                put(0, num0);
                put(1, num1);
                put(2, num2);
                put(3, num3);
                put(4, num4);
                put(5, num5);
                put(6, num6);
                put(7, num7);
                put(8, num8);
                put(9, num9);
                put(10, num10);

            }


        };

        labelMap = new HashMap<Integer, Label>() {

            {
                put(0, round11);
                put(1, round12);
                put(2, round21);
                put(3, round22);
                put(4, round31);
                put(5, round32);

            }
        };


        totalLabelMap = new HashMap<Integer, Label>() {

            {
                put(0, total1);
                put(1, total2);
                put(2, total3);

            }
        };


        tryShut = TryShut.FIRST_SHUT;
    }

    private void makeEnableForTrueAllButton() {

        for (Map.Entry<Integer, Button> entry : buttonMap.entrySet()) {
            Integer key = entry.getKey();
            Button val = entry.getValue();
            val.setDisable(false);
        }

    }


    private void makeEnableFalseBiggerThanSelectedNumber(int num) {

        for (Map.Entry<Integer, Button> entry : buttonMap.entrySet()) {
            Integer key = entry.getKey();
            Button val = entry.getValue();

            if (key > (10 - num)) {
                val.setDisable(true);
            }
        }
    }

    @FXML
    void chooseNumber(ActionEvent event) {


        int value = Integer.parseInt(((Button) event.getSource()).getText());

        if (value == 10) {

            tryShut = TryShut.STRIKE;
        }

        labelMap.get(shutCount).setText(value + "");
        shutCount++;

        switch (tryShut) {
            case FIRST_SHUT: {
                rountScore += value;
                makeEnableFalseBiggerThanSelectedNumber(value);
                tryShut = TryShut.SECOND_SHUT;
                break;

            }
            case SECOND_SHUT: {

                rountScore += value;

                if (rountScore < 10) {
                    gameScore += rountScore;
                    rountScore = 0;
                    totalLabelMap.get(roundCount).setText(gameScore + "");

                } else if (rountScore > 10) {
                    if (isStrike) {
                        gameScore += rountScore;
                        isStrike = false;
                    } else {
                        gameScore += rountScore - value;
                    }

                    totalLabelMap.get(roundCount - 1).setText(gameScore + "");
                    gameScore += rountScore - 10;
                    totalLabelMap.get(roundCount).setText(gameScore + "");
                    rountScore = 0;

                }

                roundCount++;

                makeEnableForTrueAllButton();
                tryShut = TryShut.FIRST_SHUT;

                break;
            }

            case STRIKE: {
                makeEnableForTrueAllButton();
                roundCount++;
                shutCount++;

                rountScore += 10;
                isStrike = true;


                tryShut = TryShut.FIRST_SHUT;
                break;

            }
        }
    }
}