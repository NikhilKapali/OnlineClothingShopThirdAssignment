package com.example.thirdassignment;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemEach extends AppCompatActivity {
    TextView item_name,item_price,item_description;
    ImageView item_image;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_each);

        actionBar=getSupportActionBar();
        actionBar.setTitle("Online Clothing Shopping App");
        actionBar.setSubtitle("Details of item");

        item_name=findViewById(R.id.item_name);
        item_price=findViewById(R.id.item_price);
        item_description=findViewById(R.id.item_description);
        item_image=findViewById(R.id.item_image);

        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            //Integer image=Integer.parseInt(bundle.getString("itemImageName"));
            item_name.setText(bundle.getString("itemName"));
            item_price.setText(bundle.getString("itemPrice"));
            item_description.setText(bundle.getString("itemDescription"));
            item_image.setImageResource(bundle.getInt("itemImageName"));
        }

    }
}
