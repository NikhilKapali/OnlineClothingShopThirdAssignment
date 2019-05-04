package com.example.thirdassignment;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ItemActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_item_name,et_item_price,et_item_image_name,et_item_description;
    TextView et_item_show;
    Button btn_item_submit,btn_item_show;
    private static final String FILE_NAME="items.txt";
    ActionBar actionBar;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Online Clothing Shopping App");
        actionBar.setSubtitle("Add Item");
        views();
    }
    public void views(){
        et_item_name=findViewById(R.id.et_itemName);
        et_item_price=findViewById(R.id.et_itemPrice);
        et_item_image_name=findViewById(R.id.et_item_image_name);
        et_item_description=findViewById(R.id.et_itemDescription);
        et_item_show=findViewById(R.id.et_item_show);
        btn_item_submit=findViewById(R.id.btn_itemSubmit);
        btn_item_submit.setOnClickListener(this);
        btn_item_show=findViewById(R.id.btn_item_show);
        btn_item_show.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_itemSubmit) {
            if (validate()) {
                String itemName = et_item_name.getText().toString();
                String itemPrice = et_item_price.getText().toString();
                String itemImageName = et_item_image_name.getText().toString();
                String itemDescription = et_item_description.getText().toString();
                try {
                    FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE | MODE_APPEND);

                    String data = itemName + "->" + itemPrice + "->" + itemImageName + "->" + itemDescription + "\n";
                    fos.write(data.getBytes());//changes text to bytes
                    fos.close();
                    Toast.makeText(this, "Saved to" + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        }
        if (v.getId() == R.id.btn_item_show) {
            try {
                String data;
                FileInputStream fis = openFileInput(FILE_NAME);
                InputStreamReader isr = new InputStreamReader(fis);//converts to character sequence
                BufferedReader br = new BufferedReader(isr);//change to readable character
                StringBuilder sb = new StringBuilder();//converts to string
                while ((data = br.readLine()) != null) {
                    sb.append(data).append("\n");//for line break
                    et_item_show.setText(sb.toString());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private boolean validate(){
        if(TextUtils.isEmpty(et_item_name.getText().toString())){
            et_item_name.setError("Enter Item name");
            et_item_name.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(et_item_price.getText().toString())){
            et_item_price.setError("Enter Item Price");
            et_item_price.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(et_item_image_name.getText().toString())){
            et_item_image_name.setError("Enter Item Image Name");
            et_item_image_name.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(et_item_description.getText().toString())){
            et_item_description.setError("Enter Item Description");
            et_item_description.requestFocus();
            return false;
        }
        return true;
    }
}
