package de.ur.mi.ux.weltenburg.adapters;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.animtest.animations.R;

import java.util.ArrayList;

import de.ur.mi.bischofshof.helpers.AnimationHelper;
import de.ur.mi.bischofshof.helpers.NavigationItem;

/**
 * Created by Jonas on 17.06.2014.
 */
public class NavigationListAdapter extends ArrayAdapter<NavigationItem> {
    private ArrayList<NavigationItem> content;
    private int resource;
    private Context context;
    private int selectedPos;

    public NavigationListAdapter(Context context, int resource, ArrayList<NavigationItem> list) {
        super(context, resource, list);
        this.context = context;
        this.content = list;
        this.resource = resource;
        selectedPos = -1;

    }
    public void setSelectedPosition(int pos){
        selectedPos = pos;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(resource, parent, false);
        }

        TextView text = (TextView) v.findViewById(R.id.navigation_list_text);
        ImageView bullet = (ImageView) v.findViewById(R.id.navigation_list_bullet);
        int color = content.get(position).getItemColor();
        bullet.getBackground()
                .setColorFilter(color, PorterDuff.Mode.ADD);

        text.setText(content.get(position).getItemTitle());

        if (selectedPos == position) {

            AnimationHelper.startNavigationSelectedAnimation(v, color);
        } else {
            AnimationHelper.startNavigationDeselectedAnimation(v);
        }


        return v;

    }

}
