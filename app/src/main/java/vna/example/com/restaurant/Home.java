package vna.example.com.restaurant;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    Toolbar toolbar;
    TextView textView1,textView2;
    Typeface typefacetxt1,typefacetxt2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        textView1=(TextView) findViewById(R.id.tt);
        textView2=(TextView) findViewById(R.id.ttt);
        typefacetxt1= Typeface.createFromAsset(getAssets(),"fonts/Oswald-Stencbab.ttf");
        textView1.setTypeface(typefacetxt1);
        typefacetxt2=Typeface.createFromAsset(getAssets(),"fonts/gtw.ttf");
        textView2.setTypeface(typefacetxt2);

        toolbar=(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("MANAGING");
        NavMenue navgationDrwaer=(NavMenue) getSupportFragmentManager().findFragmentById(R.id.draw);
        navgationDrwaer.setUp((DrawerLayout) findViewById(R.id.drawer),toolbar);


    }

}
