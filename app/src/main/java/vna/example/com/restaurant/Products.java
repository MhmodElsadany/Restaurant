package vna.example.com.restaurant;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static vna.example.com.restaurant.R.string.address;
import static vna.example.com.restaurant.R.string.firstname;
import static vna.example.com.restaurant.R.string.phone;
import static vna.example.com.restaurant.R.string.secondname;

public class Products extends AppCompatActivity {
    EditText name,price,classify;
    Button add,delete,search,update;
    StringRequest stringRequest;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        name=(EditText)findViewById(R.id.name);
        price=(EditText)findViewById(R.id.price);
        classify=(EditText)findViewById(R.id.classify);
        add=(Button)findViewById(R.id.insert);
        delete=(Button)findViewById(R.id.delete);
        update=(Button)findViewById(R.id.update);
        search=(Button)findViewById(R.id.search);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Products");

         setSupportActionBar(toolbar);
         getSupportActionBar().setHomeButtonEnabled(true);
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://" + getResources().getString(R.string.ip) + ":8080/restaurant/insertmenu.php";
                stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("onresponse error   *******************************");
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> map = new HashMap<>();
                        map.put("name", name.getText().toString());
                        map.put("price", price.getText().toString());
                        map.put("classify", classify.getText().toString());

                        return map;
                    }
                };
                if (registration()) {
                    Singleton.getInstance(Products.this).addRequestQue(stringRequest);
                }
                else {
                    Toast.makeText(Products.this,"some thing is error",Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(Products.this,"successful insertion,",Toast.LENGTH_SHORT).show();

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://" + getResources().getString(R.string.ip) + ":8080/restaurant/updatemenu.php";
                stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("onresponse error   *******************************");
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> map = new HashMap<>();
                        map.put("name", name.getText().toString());
                        map.put("price", price.getText().toString());
                        map.put("classify", classify.getText().toString());
                        return map;
                    }
                };
                Singleton.getInstance(Products.this).addRequestQue(stringRequest);


                Toast.makeText(Products.this,"successful updating,",Toast.LENGTH_SHORT).show();

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://" + getResources().getString(R.string.ip) + ":8080/restaurant/deletmenu.php";
                stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("onresponse error   *******************************");
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> map = new HashMap<>();
                        map.put("name", name.getText().toString());
                        return map;
                    }
                };
                Singleton.getInstance(Products.this).addRequestQue(stringRequest);
                Toast.makeText(Products.this,"successful deleting,",Toast.LENGTH_SHORT).show();



            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://" + getResources().getString(R.string.ip) + ":8080/restaurant/searchmenu.php";
                stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        final Dialog dialog = new Dialog(Products.this);
                        dialog.setTitle(" Result of searching ");
                        dialog.setContentView(R.layout.dialog_sign);
                        dialog.show();
                        TextView textView=(TextView) dialog.findViewById(R.id.txt);
                        textView.setText(response);


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("onresponse error   *******************************");
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> map = new HashMap<>();
                        map.put("name", name.getText().toString());
                        return map;
                    }
                };
                Singleton.getInstance(Products.this).addRequestQue(stringRequest);



            }
        });


    }
    public Boolean registration() {
        boolean result = true;
        if (TextUtils.isEmpty(name.getText().toString())) {
            name.setError("plz enter your name");
            result = false;
        }
        if (TextUtils.isEmpty(price.getText().toString())) {
            price.setError("plz enter your price");
            result = false;
        }
        if (TextUtils.isEmpty(classify.getText().toString())) {
            classify.setError("plz enter your classify");
            result = false;
        }

        return result;
    }
}
