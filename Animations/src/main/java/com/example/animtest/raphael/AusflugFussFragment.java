package com.example.animtest.raphael;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.animtest.animations.R;


/**
 * Created by Jonas (Bitch!!!) on 08.07.2014.
 */
public class AusflugFussFragment extends Fragment {
    View v; 

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {        
        v = inflater.inflate(R.layout.layout_ausflug_fuss, container, false);
        initData();
        return v;
    }
    
    private void initData(){
    	ListView lv_articles = (ListView) v.findViewById(R.id.articleLayout_ausflug_fuss);
		lv_articles.setDivider(null);
		ListViewAdapterArticles adapter = new ListViewAdapterArticles(getActivity(), ((BischofshofApplikation)getActivity().getApplication()).getWeltenburgAufNachWeltenburgFuss().getArticles(), ((BischofshofApplikation)getActivity().getApplication()).getWeltenburgAufNachWeltenburgFuss().getHeader());
		lv_articles.setAdapter(adapter);	
		adapter.notifyDataSetChanged();	
    }
}
