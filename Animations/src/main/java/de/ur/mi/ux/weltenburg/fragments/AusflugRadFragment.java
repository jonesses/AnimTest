package de.ur.mi.ux.weltenburg.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.animtest.animations.R;
import com.example.animtest.raphael.ListViewAdapterArticles;


/**
 * Created by Jonas (Bitch!!!) on 08.07.2014.
 */
public class AusflugRadFragment extends Fragment {
    View v; 

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {        
        v = inflater.inflate(R.layout.layout_ausflug_rad, container, false);
        initData();
        return v;
    }
    
    private void initData(){
    	ListView lv_articles = (ListView) v.findViewById (R.id.articleLayout_ausflug_rad);
		lv_articles.setDivider(null);
		ListViewAdapterArticles adapter = new ListViewAdapterArticles(getActivity(), ((BischofshofApplikation)getActivity().getApplication()).getWeltenburgAufNachWeltenburgRad().getArticles(), ((BischofshofApplikation)getActivity().getApplication()).getWeltenburgAufNachWeltenburgRad().getHeader());
		lv_articles.setAdapter(adapter);	
		adapter.notifyDataSetChanged();	
    }
}
