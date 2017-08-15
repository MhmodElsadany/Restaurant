package vna.example.com.restaurant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Login extends AppCompatActivity {
    EditText username, paswward;
    Button login;
    TextView textView1;
    CheckBox check;
    Typeface typefacetxt1;
    SharedPreferences.Editor editor;
    String id,jobname;

    @Override
    protected void onPause() {
        super.onPause();
        checkbox();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username    =(EditText) findViewById(R.id.user_name);
        paswward    = (EditText) findViewById(R.id.pass_user);
        login       = (Button) findViewById(R.id.loginBtn);
        check       = (CheckBox) findViewById(R.id.remmember);

        textView1=(TextView) findViewById(R.id.title);

        typefacetxt1= Typeface.createFromAsset(getAssets(),"fonts/gtw.ttf");
        textView1.setTypeface(typefacetxt1);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    if (login()) {new Async().execute("logIn", username.getText().toString(), paswward.getText().toString());}

                    else {
                        Toast.makeText(Login.this, "plz ensure enter  username and paswward", Toast.LENGTH_SHORT);}

            }



        });


        restorepreferance();

    }

    public class Async extends AsyncTask<String, Void, String> {
        String res = null;
        AlertDialog alertDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            alertDialog = new AlertDialog.Builder(Login.this).create();
            alertDialog.setTitle("status login");

        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            if (s == null || s.equals("username or passward invalid"))
            {
                alertDialog.setMessage(s);
                alertDialog.show();
            }
            else if(jobname.equals("admin"))
            {
                alertDialog.setMessage(id);
                alertDialog.show();
                Intent intent=new Intent(Login.this, Home.class);
                intent.putExtra("id_user",id);
                startActivity(intent);
            }
            else if(jobname.equals("cachier")){
                alertDialog.setMessage(id);
                alertDialog.show();
                Toast.makeText(Login.this,"this is cacher",Toast.LENGTH_SHORT).show();
            }
            else {

            }


        }

        @Override
        protected String doInBackground(String... params) {
            String type = params[0];
            String user_name = params[1];
            String user_pass = params[2];
            if (type.equals("logIn")) {
                try {
                    String ipaddress = getResources().getString(R.string.ip);

                    URL url = new URL("http://"+ipaddress+":8080/restaurant/user.php");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.connect();
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") +
                            "&" + URLEncoder.encode("user_pass", "UTF-8") + "=" + URLEncoder.encode(user_pass, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    res = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        res += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    Log.v("mmmmmmmm5", res + "jkjjhjjjjjjjjj");


                } catch (Exception e) {

                }

                try{

                    JSONObject jsonObject=new JSONObject(res);
                    Log.i("kkkkkk","22222222222222222222");

                    JSONArray jsonArray=jsonObject.getJSONArray("message");

                    for (int i =0;i<jsonArray.length();i++) {

                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        id=jsonObject1.getString("id");
                        jobname=jsonObject1.getString("jobname");

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return res;
            }


            return res;
        }

    }

    public boolean login()
    {
        boolean result = true;
        String username = this.username.getText().toString();
        String pass = this.paswward.getText().toString();
        if (TextUtils.isEmpty(username))
        {
            this.username.setError("plz enter your username");
            result = false;
        }
        if (TextUtils.isEmpty(pass))
        {
            this.paswward.setError("plz enter your username");
            result = false;
        }
        return result;
    }


    public void restorepreferance()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("Data", MODE_PRIVATE);
        username.setText(sharedPreferences.getString("username", ""));
        paswward.setText(sharedPreferences.getString("passward", ""));
        check.setChecked(true);


    }


    public void checkbox()
    {
        if (check.isChecked())
        {
            SharedPreferences sharedPreferences = getSharedPreferences("Data", MODE_PRIVATE);
            editor = sharedPreferences.edit();
            editor.putString("username", username.getText().toString());
            editor.putString("passward", paswward.getText().toString());
            editor.commit();
        }


    }
}