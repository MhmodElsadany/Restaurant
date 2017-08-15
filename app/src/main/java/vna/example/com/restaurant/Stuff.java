package vna.example.com.restaurant;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static vna.example.com.restaurant.R.id.txt;

public class Stuff extends AppCompatActivity {
    EditText firstname,secondname,phone,address,salary,username,passward;
    RadioButton admin,cachier;
    Toolbar toolbar;
    Button add,delete,search,update;
    StringRequest stringRequest;
    String radio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stuff);
        firstname=(EditText)findViewById(R.id.firstname_user);
        secondname=(EditText)findViewById(R.id.secondname_user);
        phone=(EditText)findViewById(R.id.phone_user);
        address=(EditText)findViewById(R.id.address_user);
        salary=(EditText)findViewById(R.id.salary_user);
        username=(EditText)findViewById(R.id.username_user);
        passward=(EditText)findViewById(R.id.passwad_user);

        add=(Button)findViewById(R.id.add);
        delete=(Button)findViewById(R.id.delete);
        update=(Button)findViewById(R.id.update);
        search=(Button)findViewById(R.id.search);

        admin=(RadioButton)findViewById(R.id.admin);
        admin=(RadioButton)findViewById(R.id.cachier);

        toolbar=(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("STUFF MANAGE");

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(admin.isChecked()){
            radio="admin";
        }
        else {
            radio="cachier";
        }




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://" + getResources().getString(R.string.ip) + ":8080/restaurant/insertuser.php";
                stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                           Toast.makeText(Stuff.this,"insertion is successful",Toast.LENGTH_SHORT).show();
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
                        map.put("firstname", firstname.getText().toString());
                        map.put("secondname", secondname.getText().toString());
                        map.put("phone", phone.getText().toString());
                        map.put("address", address.getText().toString());
                        map.put("salary", salary.getText().toString());
                        map.put("jobname", radio);
                        map.put("username", username.getText().toString());
                        map.put("passward", passward.getText().toString());
                        return map;
                    }
                };
                if (registration()) {
                    Singleton.getInstance(Stuff.this).addRequestQue(stringRequest);
                }
                    else {
                    Toast.makeText(Stuff.this,"some thing is error",Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(Stuff.this,"successful insertion,",Toast.LENGTH_SHORT).show();

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://" + getResources().getString(R.string.ip) + ":8080/restaurant/update.php";
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
                        map.put("firstname", firstname.getText().toString());
                        map.put("secondname", secondname.getText().toString());
                        map.put("phone", phone.getText().toString());
                        map.put("address", address.getText().toString());
                        map.put("salary", salary.getText().toString());
                        map.put("jobname", radio);
                        map.put("username", username.getText().toString());
                        map.put("passward", passward.getText().toString());                        return map;
                    }
                };
                Singleton.getInstance(Stuff.this).addRequestQue(stringRequest);
                Toast.makeText(Stuff.this,"successful updating,",Toast.LENGTH_SHORT).show();



            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://" + getResources().getString(R.string.ip) + ":8080/restaurant/delete.php";
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
                        map.put("username", username.getText().toString());
                        return map;
                    }
                };
                Singleton.getInstance(Stuff.this).addRequestQue(stringRequest);

                Toast.makeText(Stuff.this,"successful deleting,",Toast.LENGTH_SHORT).show();


            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://" + getResources().getString(R.string.ip) + ":8080/restaurant/search.php";
                stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        final Dialog dialog = new Dialog(Stuff.this);
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
                        map.put("username", username.getText().toString());
                        return map;
                    }
                };
                Singleton.getInstance(Stuff.this).addRequestQue(stringRequest);



            }
        });


    }
    public Boolean registration() {
        boolean result = true;
        if (TextUtils.isEmpty(username.getText().toString())) {
            username.setError("plz enter your username");
            result = false;
        }
        if (TextUtils.isEmpty(passward.getText().toString())) {
            passward.setError("plz enter your passward");
            result = false;
        }
        if (TextUtils.isEmpty(phone.getText().toString())) {
            phone.setError("plz enter your phone");
            result = false;
        }
        if (TextUtils.isEmpty(secondname.getText().toString())) {
            secondname.setError("plz enter your secondname");
            result = false;
        }
        if (TextUtils.isEmpty(firstname.getText().toString())) {
            firstname.setError("plz enter your firstname");
            result = false;
        }
        if (TextUtils.isEmpty(address.getText().toString())) {
            address.setError("plz enter your firstname");
            result = false;
        }

        return result;
    }
}
