package de.ur.mi.ux.weltenburg.fragments;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.animtest.animations.R;
import com.example.animtest.raphael.ListViewAdapterArticles;

import de.ur.mi.projektion.bischofshof.listeners.OnFragmentInteractionListener;


/**
 * Created by Jonas (Bitch!!!) on 08.07.2014.
 */
public class GeschichteAllgemeinesFragment extends Fragment {
    View v; 
    Button button;

    private OnFragmentInteractionListener fragmentInteractionListener;
    private Typeface font_bold;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {        
        v = inflater.inflate(R.layout.layout_geschichte_allgemeines, container, false);
        initData();
        initListener();
        return v;
    }

    private void initListener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                fragmentInteractionListener.onTransitionToFullscreenRequested();
            }
        });
    }

    public void setOnFragmentInteractionListener(OnFragmentInteractionListener fragmentInteractionListener){
        this.fragmentInteractionListener = fragmentInteractionListener;
    }

    private void initData(){
    	ListView lv_articles = (ListView) v.findViewById(R.id.articleLayout_geschichte_allgemeines);
        button = (Button) v.findViewById(R.id.bt_articleLayout_geschichte_allgemeines);
        this.font_bold = Typeface.createFromAsset(getActivity().getAssets(), "font/Georgia_Bold.ttf");
        button.setTypeface(font_bold);

        lv_articles.setDivider(null);
		ListViewAdapterArticles adapter = new ListViewAdapterArticles(getActivity(), ((BischofshofApplikation)getActivity().getApplication()).getWeltenburgGeschichteAllgemeines().getArticles(), ((BischofshofApplikation)getActivity().getApplication()).getWeltenburgGeschichteAllgemeines().getHeader());
		lv_articles.setAdapter(adapter);	
		adapter.notifyDataSetChanged();	
    }
}
