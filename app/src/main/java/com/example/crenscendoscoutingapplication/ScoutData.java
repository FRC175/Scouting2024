package com.example.crenscendoscoutingapplication;

import java.io.Serializable;

public class ScoutData implements Serializable {
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
        parked,
        chain,
        harmonize,
        harmonize2bot,
        noneabove

    }
    boolean humanScored;
    boolean cooperationBonus;
    String comments = "";
}
