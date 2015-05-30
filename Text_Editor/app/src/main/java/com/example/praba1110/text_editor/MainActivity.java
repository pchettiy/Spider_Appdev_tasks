package com.example.praba1110.text_editor;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    public Bundle bundle = new Bundle();
    String[] fonts = {"Times New Roman", "Arial", "Comic Sans","Paddington"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner size_spinner = (Spinner) findViewById(R.id.size);
        ArrayList<String> sizes = new ArrayList<String>();
        for (int i = 10; i <= 40; i += 5) {
            sizes.add(Integer.toString(i));
        }
        final ArrayAdapter<String> adapter_size = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sizes);
        adapter_size.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        size_spinner.setAdapter(adapter_size);
        size_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String size = adapter_size.getItem(position);
                bundle.putString("Size", size);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner color_spinner = (Spinner) findViewById(R.id.color);
        final ArrayAdapter<CharSequence> adapter_color = ArrayAdapter.createFromResource(this, R.array.Colors, android.R.layout.simple_spinner_item);
        adapter_color.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        color_spinner.setAdapter(adapter_color);
        color_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String color = adapter_color.getItem(position).toString();
                bundle.putString("Color", color);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ListView listView = (ListView) findViewById(R.id.fontView);

        final ArrayAdapter<String> font_array = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fonts);
        listView.setAdapter(font_array);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String font = font_array.getItem(position);
                bundle.putString("Font", font);
                CheckBox bold = (CheckBox) findViewById(R.id.checkBold);
                CheckBox italic = (CheckBox) findViewById(R.id.checkItalic);
                final boolean boldChecked = bold.isChecked();
                final boolean italicChecked = italic.isChecked();
                bundle.putBoolean("Bold", boldChecked);
                bundle.putBoolean("Italic", italicChecked);

                EditText editText = (EditText) findViewById(R.id.editText);
                bundle.putString("FinalText", editText.getText().toString());
                Intent intent = new Intent(getApplicationContext(), Display.class);
                intent.putExtra("Bundle", bundle);
                startActivity(intent);
            }

        });


    }
}