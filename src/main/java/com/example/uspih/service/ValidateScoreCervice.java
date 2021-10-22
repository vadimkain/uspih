package com.example.uspih.service;

import org.springframework.stereotype.Service;

@Service
public class ValidateScoreCervice {

    public boolean ValidateScore(String score) {
        char[] c = {'-', '.', ','};
        char[] num = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        score = score.replace(c[2], c[1]);

        if (((score.charAt(0) == c[0]) && ((score.charAt(1) == num[0]) && (score.charAt(2) != c[1]))) && (score.length() >= 3)) {
            return false;
        } else if (((score.charAt(0) == num[0]) && (score.charAt(1) != c[1])) && (score.length() >= 2)) {
            return false;
        } else {
            try {
                Double.parseDouble(score);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }
}
