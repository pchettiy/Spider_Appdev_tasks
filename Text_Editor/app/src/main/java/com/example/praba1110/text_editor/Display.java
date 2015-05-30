package com.example.praba1110.text_editor;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Display extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Intent intentReceived = getIntent();
        Bundle bundle = intentReceived.getBundleExtra("Bundle");
        String size = bundle.getString("Size");
        String color = bundle.getString("Color");
        float sz = Float.parseFloat(size);
        String font = bundle.getString("Font");
        Boolean bold = bundle.getBoolean("Bold");
        Boolean italics = bundle.getBoolean("Italic");
        TextView disptext = (TextView) findViewById(R.id.FinalText);
        disptext.setText(bundle.getString("FinalText"));
        color = color.toUpperCase();
        disptext.setTextColor(Color.parseColor(color));
        int style;
        if((bold)&&(italics)) style = Typeface.BOLD_ITALIC;
        else if(bold) style = Typeface.BOLD;
        else if(italics) style = Typeface.ITALIC;
        else style = Typeface.NORMAL;
        disptext.setTextSize(TypedValue.COMPLEX_UNIT_SP,sz * getResources().getDisplayMetrics().scaledDensity);
        Typeface typeface = Typeface.createFromAsset(getAssets(),font+".ttf");
        disptext.setTypeface(typeface,style);

    }
    public void back2edit(View view)
    {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);

    }
    public void exit(View v)
    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}



