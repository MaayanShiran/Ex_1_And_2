package com.classy.common;

import java.util.ArrayList;
import java.util.Random;

public class AppMan {//like game manager

    private int currentText = 0;

    private ArrayList<Text>texts;
    private ArrayList<Text>filteredTexts;

    public AppMan(DataMan dataMan)
    {
        texts = dataMan.texts();
        filteredTexts = new ArrayList<>(texts); // Initialize filteredTexts with all texts

    }

    public ArrayList<Text> filterByDropDown(String drop) {
        filteredTexts.clear();
        for (Text text : texts) {
            if (drop.equals("Random")) {
                continue;
            } else if (text.getType().equals(drop)) {
                filteredTexts.add(text);
            }
        }
        if(filteredTexts.size() == 0)
        {
            filteredTexts = new ArrayList<>(texts);
        }
        return filteredTexts;
    }

    public String getCurrentImg()
    {
        return filteredTexts.get(currentText).getImg();
    }

    public void setCurrentText(){ currentText++;}

    public String getRandomContent()
    {
        Random random = new Random();
        currentText = random.nextInt(filteredTexts.size());

        return filteredTexts.get(currentText).getContent();
    }

    public String getCurrentType()
    {
        return filteredTexts.get(currentText).getType();
    }
}
