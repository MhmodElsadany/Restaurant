package vna.example.com.restaurant;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.*;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Detailmenu extends AppCompatActivity {
     StringRequest stringRequest;
     ListView itemslist;
     ListView itemdrinks;
     ArrayList<ModelDetail> items=new ArrayList<>();
    ArrayList<ModelDetail> itemsdrink=new ArrayList<>();
    TextView textView1,textView2,textView3,textView4;
    Typeface typefacetxt1,typefacetxt2,typefacetxt3,typefacetxt4;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailmenu);
        itemslist=(ListView) findViewById(R.id.listitem);
        itemdrinks=(ListView) findViewById(R.id.listdrink);

        textView1=(TextView) findViewById(R.id.txt);
        textView2=(TextView) findViewById(R.id.txt2);
        textView3=(TextView) findViewById(R.id.name);
        textView4=(TextView) findViewById(R.id.name1);

        typefacetxt1=Typeface.createFromAsset(getAssets(),"fonts/gtw.ttf");
        textView1.setTypeface(typefacetxt1);

        typefacetxt3=Typeface.createFromAsset(getAssets(),"fonts/gtw.ttf");
        textView3.setTypeface(typefacetxt3);

        typefacetxt2=Typeface.createFromAsset(getAssets(),"fonts/gtw.ttf");
        textView2.setTypeface(typefacetxt2);

        typefacetxt4=Typeface.createFromAsset(getAssets(),"fonts/gtw.ttf");
        textView4.setTypeface(typefacetxt4);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        String url = "http://" + getResources().getString(R.string.ip) + ":8080/restaurant/detailmenu.php";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{

                    JSONObject jsonObject=new JSONObject(response);
                    Log.i("kkkkkk","22222222222222222222");

                    JSONArray jsonArray=jsonObject.getJSONArray("message");

                    for (int i =0;i<jsonArray.length();i++) {

                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        items.add(new ModelDetail(jsonObject1.getString("name"),jsonObject1.getString("price")));

                        CustomAdapterDetail customAdapterDetail=new CustomAdapterDetail(Detailmenu.this,items);
                        itemslist.setAdapter(customAdapterDetail);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                ;
                map.put("parmter","fish");
                return map;
            }
        };
        Singleton.getInstance(Detailmenu.this).addRequestQue(stringRequest);


        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{

                    JSONObject jsonObject=new JSONObject(response);
                    Log.i("kkkkkk","22222222222222222222");

                    JSONArray jsonArray=jsonObject.getJSONArray("message");

                    for (int i =0;i<jsonArray.length();i++) {

                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        itemsdrink.add(new ModelDetail(jsonObject1.getString("name"),jsonObject1.getString("price")));

                        CustomAdapterDetail customAdapterDetail=new CustomAdapterDetail(Detailmenu.this,itemsdrink);
                        itemdrinks.setAdapter(customAdapterDetail);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                ;
                map.put("parmter","drink");
                return map;
            }
        };
        Singleton.getInstance(Detailmenu.this).addRequestQue(stringRequest);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menue, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.order){
            final Dialog dialog = new Dialog(Detailmenu.this);
            dialog.setTitle(" Check ");
            dialog.setContentView(R.layout.activity_check);
            dialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

}
