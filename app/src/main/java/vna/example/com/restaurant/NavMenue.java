package vna.example.com.restaurant;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static android.R.attr.order;

/**
 * Created by Google       Company on 12/08/2017.
 */

public class NavMenue  extends Fragment {
    DrawerLayout mDrawerLayout;
    TextView stuff , menu,products,logout,location,chatting;
    public NavMenue() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout=inflater.inflate(R.layout.nav_header, container, false);
        stuff=(TextView) layout.findViewById(R.id.stuff);
        menu=(TextView) layout.findViewById(R.id.menu);
        products=(TextView) layout.findViewById(R.id.product);
        logout=(TextView) layout.findViewById(R.id.logout);
        location=(TextView) layout.findViewById(R.id.location);


        location.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        location.setTextColor(getResources().getColor(R.color.blue));
        startActivity(new Intent(getActivity(),Location.class));
    }
});
        


        stuff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stuff.setTextColor(getResources().getColor(R.color.blue));
                Intent intent=new Intent(getActivity(),Stuff.class);
                startActivity(intent);
            }
        });
        products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                products.setTextColor(getResources().getColor(R.color.blue));
                startActivity(new Intent(getActivity(),Products.class));

            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                menu.setTextColor(getResources().getColor(R.color.blue));
                startActivity(new Intent(getActivity(),Menu.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),Login.class));
            }
        });

        return layout;
    }





    public void setUp(DrawerLayout drawerLayout, final Toolbar toolbar){
        toolbar.setTitle("Jobs");
        mDrawerLayout=drawerLayout;
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.open
                ,R.string.close){

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                toolbar.setTitle("MANGAING");


            }



            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                toolbar.setTitle("♥ Main Menu");

            }
        };
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }



}

