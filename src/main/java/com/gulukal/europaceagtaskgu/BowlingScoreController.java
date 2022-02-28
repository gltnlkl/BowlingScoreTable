package com.gulukal.europaceagtaskgu;


import com.gulukal.europaceagtaskgu.utils.TryShoot;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Gulten Ulukal
 */

public class BowlingScoreController {

    private TryShoot tryShoot;
    private int roundCount = 0;
    private int shootCount = 0;
    private int gameScore;
    private int roundScore;
    private boolean isStrike = false;


    @FXML
    private Button num0;

    @FXML
    private Button num1;

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

    @FXML
    private Button num10;

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
    private Label round41;

    @FXML
    private Label round42;

    @FXML
    private Label round51;

    @FXML
    private Label round52;

    @FXML
    private Label round61;

    @FXML
    private Label round62;

    @FXML
    private Label round71;

    @FXML
    private Label round72;

    @FXML
    private Label round81;

    @FXML
    private Label round82;

    @FXML
    private Label round91;

    @FXML
    private Label round92;

    @FXML
    private Label round101;

    @FXML
    private Label round102;

    @FXML
    private Label total1;

    @FXML
    private Label total2;

    @FXML
    private Label total3;

    @FXML
    private Label total4;

    @FXML
    private Label total5;

    @FXML
    private Label total6;

    @FXML
    private Label total7;

    @FXML
    private Label total8;

    @FXML
    private Label total9;

    @FXML
    private Label total10;

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
                put(6, round41);
                put(7, round42);
                put(8, round51);
                put(9, round52);
                put(10, round61);
                put(11, round62);
                put(12, round71);
                put(13, round72);
                put(14, round81);
                put(15, round82);
                put(16, round91);
                put(17, round92);
                put(18, round101);
                put(19, round102);

            }
        };


        totalLabelMap = new HashMap<Integer, Label>() {

            {
                put(0, total1);
                put(1, total2);
                put(2, total3);
                put(3, total4);
                put(4, total5);
                put(5, total6);
                put(6, total7);
                put(7, total8);
                put(8, total9);
                put(9, total10);

            }
        };


        tryShoot = TryShoot.FIRST_SHUT;
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

        switch (value) {
            case 10: {

                tryShoot = TryShoot.STRIKE;
                labelMap.get(shootCount).setText("X");
            }
            break;
            case 0: {
                labelMap.get(shootCount).setText("-");
            }
            break;
            default:{
                labelMap.get(shootCount).setText(value + "");
            }

        }

        shootCount++;

        switch (tryShoot) {
            case FIRST_SHUT: {
                roundScore += value;
                makeEnableFalseBiggerThanSelectedNumber(value);
                tryShoot = TryShoot.SECOND_SHUT;
                break;

            }
            case SECOND_SHUT: {

                roundScore += value;

                if (roundScore < 10) {
                    gameScore += roundScore;
                    roundScore = 0;
                    totalLabelMap.get(roundCount).setText(gameScore + "");

                } else if (roundScore > 10) {
                    if (isStrike) {
                        gameScore += roundScore;
                        isStrike = false;
                    } else {
                        gameScore += roundScore - value;
                    }

                    totalLabelMap.get(roundCount - 1).setText(gameScore + "");
                    gameScore += roundScore - 10;
                    totalLabelMap.get(roundCount).setText(gameScore + "");
                    roundScore = 0;

                }else if (roundScore == 10){
                    labelMap.get(shootCount-1).setText("/");
                }

                roundCount++;

                makeEnableForTrueAllButton();
                tryShoot = TryShoot.FIRST_SHUT;

                break;
            }

            case STRIKE: {
                makeEnableForTrueAllButton();
                roundCount++;
                shootCount++;

                roundScore += 10;
                isStrike = true;


                tryShoot = TryShoot.FIRST_SHUT;
                break;

            }
        }
    }
}