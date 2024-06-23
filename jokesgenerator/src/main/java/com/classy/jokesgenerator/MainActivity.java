package com.classy.jokesgenerator;

import android.os.Bundle;

import com.classy.common.BaseMainActivity;

public class MainActivity extends BaseMainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dataMan = new DataManagerJokes();
        super.onCreate(savedInstanceState);


    }
}