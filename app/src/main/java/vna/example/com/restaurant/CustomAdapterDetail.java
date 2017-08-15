package vna.example.com.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Google       Company on 14/08/2017.
 */

public class CustomAdapterDetail extends BaseAdapter {
    Context mContext;
    ArrayList<ModelDetail> items;
    public CustomAdapterDetail(Context mContext, ArrayList<ModelDetail> items) {
        this.mContext=mContext;
       this. items=items;
    }

    @Override
    public int getCount() {
       return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i).getName();
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_info_detail, null);
        TextView name=(TextView)v.findViewById(R.id.namemenu);
        TextView price=(TextView)v.findViewById(R.id.pricemenu);
        name.setText(items.get(i).getName());
        price.setText(items.get(i).getPrice());
        return v;
    }
}
