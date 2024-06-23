package com.classy.common;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.bumptech.glide.Glide;

public class BaseMainActivity extends AppCompatActivity {

    protected DataMan dataMan;
    private AppMan appMan;
    private Button refreshBTN;
    private Button shareBTN;
    private TextView text;
    private ImageView imgView;
    private Spinner dropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.classy.common.R.layout.activity_main);

        findViews();

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_item, getResources().getStringArray(R.array.dropdown_items)) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                applyCustomFont(view, R.styleable.CustomFontTextView, R.styleable.CustomFontTextView_customFont);
                return view;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                applyCustomFont(view, R.styleable.CustomFontTextView, R.styleable.CustomFontTextView_customFont);
                return view;
            }
        };

        dropdown.setAdapter(adapter);
        applyCustomFont(text, R.styleable.CustomFontTextView, R.styleable.CustomFontTextView_customFont);

        appMan = new AppMan(dataMan);
        next();

        refreshBTN.setOnClickListener(v -> next());

        shareBTN.setOnClickListener(v -> shareContent());

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                appMan.filterByDropDown(selectedItem);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    private void applyCustomFont(View view, int[] styleable, int styleableFont) {
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            TypedArray a = view.getContext().obtainStyledAttributes(null, styleable, 0, 0);
            int fontResId = a.getResourceId(styleableFont, -1);
            if (fontResId != -1) {
                Typeface myTypeface = ResourcesCompat.getFont(this, fontResId);
                textView.setTypeface(myTypeface);
            }
            a.recycle();
        }
    }

    private void next() {
        String content = appMan.getRandomContent();
        String img = appMan.getCurrentImg();
        Glide.with(this).load(img).into(imgView);
        text.setText(content);
    }

    private void shareContent() {
        String contentToShare = text.getText().toString();
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, contentToShare);
        shareIntent.setType("text/plain");
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }

    private void findViews() {
        refreshBTN = findViewById(com.classy.common.R.id.BTN_refresh);
        shareBTN = findViewById(com.classy.common.R.id.BTN_share);
        text = findViewById(com.classy.common.R.id.LBL_TXT);
        imgView = findViewById(com.classy.common.R.id.IMG);
        dropdown = findViewById(com.classy.common.R.id.spinner);
    }
}
