package ca.era.stride.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ca.era.stride.R;
import ca.era.stride.activities.PatientProfile;
import ca.era.stride.activities.UserProfile;

public class CustomAdapter extends BaseAdapter{

    String [] myMenu;
    Context context;

    // Setting listview background colors
    private int[] colors = new int[] {
            Color.parseColor("#53befc"),
            Color.parseColor("#53befc"),
            Color.parseColor("#53befc"),
            Color.parseColor("#53befc"),
            Color.parseColor("#53befc"),
            Color.parseColor("#53befc")
    };

    private static LayoutInflater inflater=null;

    public CustomAdapter(UserProfile userProfile, String[] menu) {
        // TODO Auto-generated constructor stub
        myMenu=menu;

        context=userProfile;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public CustomAdapter(PatientProfile patientProfile, String[] menu) {
        // TODO Auto-generated constructor stub
        myMenu=menu;

        context=patientProfile;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return myMenu.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView groceryTxt;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.custom_row, null);

        // get ID
        holder.groceryTxt=(TextView) rowView.findViewById(R.id.propertyTxt);

        // set Text and Image
        holder.groceryTxt.setText(myMenu[position]);


        rowView.setBackgroundColor(colors[position % colors.length]);

        return rowView;
    }

}