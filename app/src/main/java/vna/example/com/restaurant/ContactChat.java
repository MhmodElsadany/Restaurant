package vna.example.com.restaurant;

import android.support.v7.app.AppCompatActivity;

/*
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
//import com.firebase.ui.database.FirebaseListAdapter;
//import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
*/
public class ContactChat{}// extends AppCompatActivity {
   /* FloatingActionButton fab;
    private FirebaseListAdapter<ChatMessage> adapter;
    String two;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactchat);
        fab=(FloatingActionButton)findViewById(R.id.fab);
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Chatting");


        String ip = getResources().getString(R.string.ip);

        String url = "http://" + ip + ":8080/restaurant/namechat.php?";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        try {
                            Log.i("ooooooooooo",response);
                            fab.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    EditText input = (EditText) findViewById(R.id.input);

                                    FirebaseDatabase.getInstance().getReference().child("all").child("chat").push().
                                            setValue(new ChatMessage(input.getText().toString(), response));

                                    input.setText("");
                                }
                            });
                            displayChatMessages();
                        } catch (Exception e) {
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("onresponse error   *******************************");
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("id_user", id_user());
                return map;
            }
        };
        Singleton.getInstance(this).addRequestQue(stringRequest);

    }
    public String id_user() {

        Intent user = getIntent();
        String id_user = user.getExtras().getString("id_user");
        return id_user;
    }
    private void displayChatMessages()
    {
        ListView listOfMessages = (ListView)findViewById(R.id.list_of_messages);

        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
                R.layout.rowinfochatingofcourse, FirebaseDatabase.getInstance().getReference().child("private").child(two)) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                TextView messageText = (TextView)v.findViewById(R.id.message_text);
                TextView messageUser = (TextView)v.findViewById(R.id.message_user);
                TextView messageTime = (TextView)v.findViewById(R.id.message_time);

                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));
            }
        };

        listOfMessages.setAdapter(adapter);
    }
}
*/
