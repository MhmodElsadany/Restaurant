package vna.example.com.restaurant;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class Menu extends AppCompatActivity {
    ImageView fish;
    Toolbar toolbar;
    TextView textView1;
    Typeface typefacetxt1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        fish=(ImageView) findViewById(R.id.fishmenu);

        toolbar=(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("MAIN MENU");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textView1=(TextView) findViewById(R.id.txt);
        typefacetxt1= Typeface.createFromAsset(getAssets(),"fonts/gtw.ttf");
        textView1.setTypeface(typefacetxt1);


        fish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu.this,Detailmenu.class);
                startActivity(intent);
            }
        });


    }
}
