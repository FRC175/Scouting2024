package com.example.crenscendoscoutingapplication;

import java.io.Serializable;

public class ScoutData {
    //auto mode vars
    String teamNumber = "";
    String matchNumber = "";
    int autoAmpScore = 0;
    int autoSpeakerScore = 0;
    boolean leftZone;

    //teleop vars
    int teleopSpeakerScore = 0;
    int teleopAmpScore = 0;

    enum EndGameState {
        PARKED,
        CHAIN,
        HARMONIZE,
        HARMONIZE_2_BOT,
        NONE
    }

    enum ArrayIndex {
        TEAM_NUM(0),
        MATCH_NUM(1),
        AUTO_AMP_SCORE(2),
        AUTO_SPEAKER_SCORE(3),
        LEFT_ZONE(4),
        TELEOP_SPEAKER_SCORE(5),
        TELEOP_AMP_SCORE(6),
        HUMAN_SCORED(7),
        COOPERTITION_BONUS(8),
        COMMENTS(9);

        ArrayIndex(int value) {
            this._value = value;
        }

        private final int _value;
        public int value() {
            return _value;
        }
    }

    boolean humanScored;
    boolean cooperationBonus;
    String comments = "";

    public static ScoutData fromArray(String[] array) {
        ScoutData data = new ScoutData();

        if (array != null) {
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
        }

        return data;
    }

    public String[] toArray() {

        return null;
    }
}
