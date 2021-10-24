package com.example.uspih.service;

import org.springframework.stereotype.Service;

@Service
public class ValidateScoreCervice {

    public boolean ValidateScore(String score) {
        char[] c = {'-', '.', ','};
        char[] num = {'0'};

        score = score.replace(c[2], c[1]);

        if (score.length() >= 3) {
            if (((score.charAt(0) == c[0]) && ((score.charAt(1) == num[0]) && (score.charAt(2) != c[1])))) {
                return false;
            } else {
                try {
                    Double.parseDouble(score);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        } else if (score.length() >= 2) {
            if (((score.charAt(0) == num[0]) && (score.charAt(1) != c[1]))) {
                return false;
            } else {
                try {
                    Double.parseDouble(score);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
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
