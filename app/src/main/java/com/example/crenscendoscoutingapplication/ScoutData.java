package com.example.crenscendoscoutingapplication;

import java.io.Serializable;
import java.lang.reflect.Array;

public class ScoutData {
    //auto mode vars
    String yourName = "";
    String teamNumber = "";
    String matchNumber = "";
    int autoAmpScore = 50;
    int autoSpeakerScore = 50;
    boolean leftZone = false;
    String endGameState = "N/A";

    //teleop vars
    int teleopSpeakerScore = 0;
    int teleopAmpScore = 0;

//    enum EndGameState {
//        PARKED,
//        CHAIN,
//        HARMONIZE,
//        HARMONIZE_2_BOT,
//        NONE
//    }

    boolean humanScored;
    boolean cooperationBonus;
    String comments = "";

    enum ArrayIndex {
        YOUR_NAME(0),
        TEAM_NUM(1),
        MATCH_NUM(2),
        AUTO_AMP_SCORE(3),
        AUTO_SPEAKER_SCORE(4),
        LEFT_ZONE(5),
        TELEOP_SPEAKER_SCORE(6),
        TELEOP_AMP_SCORE(7),
        HUMAN_SCORED(8),
        COOPERTITION_BONUS(9),
        COMMENTS(10),
        ENDGAME_STATE(11);

        ArrayIndex(int value) {
            this._value = value;
        }

        private final int _value;
        public int value() {
            return _value;
        }
    }

    public static ScoutData fromArray(String[] array) {
        ScoutData data = new ScoutData();

        if (array != null) {
            data.yourName = array[ArrayIndex.YOUR_NAME._value];
            data.teamNumber = array[ArrayIndex.TEAM_NUM._value];
            data.matchNumber = array[ArrayIndex.MATCH_NUM._value];
            data.autoAmpScore = Integer.parseInt(array[ArrayIndex.AUTO_AMP_SCORE._value]);
            data.autoSpeakerScore = Integer.parseInt(array[ArrayIndex.AUTO_SPEAKER_SCORE._value]);
            data.leftZone = Boolean.parseBoolean(array[ArrayIndex.LEFT_ZONE._value]);
            data.teleopSpeakerScore = Integer.parseInt(array[ArrayIndex.TELEOP_SPEAKER_SCORE._value]);
            data.teleopAmpScore = Integer.parseInt(array[ArrayIndex.TELEOP_AMP_SCORE._value]);
            data.humanScored = Boolean.parseBoolean(array[ArrayIndex.HUMAN_SCORED._value]);
            data.cooperationBonus = Boolean.parseBoolean(array[ArrayIndex.COOPERTITION_BONUS._value]);
            data.comments = array[ArrayIndex.COMMENTS._value];
            data.endGameState = array[ArrayIndex.ENDGAME_STATE._value];
        }

        return data;
    }

    public String[] toArray() {
        String[] output = new String[12];
        output[ArrayIndex.YOUR_NAME._value] = yourName;
        output[ArrayIndex.TEAM_NUM._value] = teamNumber;
        output[ArrayIndex.MATCH_NUM._value] = matchNumber;
        output[ArrayIndex.AUTO_SPEAKER_SCORE._value] = String.valueOf(autoSpeakerScore);
        output[ArrayIndex.AUTO_AMP_SCORE._value] = String.valueOf(autoAmpScore);
        output[ArrayIndex.LEFT_ZONE._value] = String.valueOf(leftZone);
        output[ArrayIndex.TELEOP_SPEAKER_SCORE._value] = String.valueOf(teleopSpeakerScore);
        output[ArrayIndex.TELEOP_AMP_SCORE._value] = String.valueOf(teleopAmpScore);
        output[ArrayIndex.HUMAN_SCORED._value] = String.valueOf(humanScored);
        output[ArrayIndex.COOPERTITION_BONUS._value] = String.valueOf(cooperationBonus);
        output[ArrayIndex.COMMENTS._value] = comments;
        output[ArrayIndex.ENDGAME_STATE._value] = endGameState;
        return output;
    }
}
