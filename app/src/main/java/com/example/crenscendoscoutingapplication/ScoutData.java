package com.example.crenscendoscoutingapplication;

import java.io.Serializable;
import java.lang.reflect.Array;

public class ScoutData {
    //auto mode vars
    String yourName = "" ;
    String teamNumber = "";
    String matchNumber = "";
    int autoAmpScore = 0;
    int autoSpeakerScore = 0;
    boolean leftZone = false;
    String endGameState = "4";

    //teleop vars
    int teleopSpeakerScore = 0;
    int teleopAmpScore = 0;

    boolean humanScored = false;
    boolean cooperationBonus = false;
    boolean carried = false;
    boolean trap = false;

    String comments = "";

    enum ArrayIndex {
        YOUR_NAME(0),
        TEAM_NUM(1),
        MATCH_NUM(2),
        AUTO_AMP_SCORE(4),
        AUTO_SPEAKER_SCORE(3),
        LEFT_ZONE(5),
        TELEOP_SPEAKER_SCORE(6),
        TELEOP_AMP_SCORE(7),
        HUMAN_SCORED(10),
        COOPERTITION_BONUS(11),
        COMMENTS(13),
        ENDGAME_STATE(8),
        CARRIED(12),
        TRAP(9);

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
            data.carried = Boolean.parseBoolean(array[ArrayIndex.CARRIED._value]);
            data.trap = Boolean.parseBoolean(array[ArrayIndex.TRAP._value]);
        }

        return data;
    }

    public String[] toArray() {
        String[] output = new String[ArrayIndex.values().length];
        output[ArrayIndex.YOUR_NAME._value] = yourName;
        output[ArrayIndex.TEAM_NUM._value] = teamNumber;
        output[ArrayIndex.MATCH_NUM._value] = matchNumber;
        output[ArrayIndex.AUTO_SPEAKER_SCORE._value] = String.valueOf(autoSpeakerScore);
        output[ArrayIndex.AUTO_AMP_SCORE._value] = String.valueOf(autoAmpScore);
        if (leftZone) {
            output[ArrayIndex.LEFT_ZONE._value] = "1";
        } else {
            output[ArrayIndex.LEFT_ZONE._value] = "0";
        }

        output[ArrayIndex.TELEOP_SPEAKER_SCORE._value] = String.valueOf(teleopSpeakerScore);
        output[ArrayIndex.TELEOP_AMP_SCORE._value] = String.valueOf(teleopAmpScore);

        if (humanScored) {
            output[ArrayIndex.HUMAN_SCORED._value] = "1";
        } else {
            output[ArrayIndex.HUMAN_SCORED._value] = "0";
        }

        if (cooperationBonus) {
            output[ArrayIndex.COOPERTITION_BONUS._value] = "1";
        } else {
            output[ArrayIndex.COOPERTITION_BONUS._value] = "0";
        }

        if (carried) {
            output[ArrayIndex.CARRIED._value] = "1";
        } else {
            output[ArrayIndex.CARRIED._value] = "0";
        }

        if (trap) {
            output[ArrayIndex.TRAP._value] = "1";
        } else {
            output[ArrayIndex.TRAP._value] = "0";
        }

        output[ArrayIndex.COMMENTS._value] = comments;
        output[ArrayIndex.ENDGAME_STATE._value] = endGameState;

        return output;
    }
}
