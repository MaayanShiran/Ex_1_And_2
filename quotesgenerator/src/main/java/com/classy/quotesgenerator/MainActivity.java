package com.classy.quotesgenerator;

import android.os.Bundle;

import com.classy.common.BaseMainActivity;

public class MainActivity extends BaseMainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dataMan = new DataManagerQuotes();
        super.onCreate(savedInstanceState);


    }
}