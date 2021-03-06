package de.ur.mi.ux.weltenburg.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.animtest.animations.R;
import com.example.animtest.raphael.GeneralContent;
import com.example.animtest.raphael.ListViewAdapterArticles;

/**
 * Created by Jonas (Bitch!!!) on 08.07.2014.
 */
public class AktuellesFragment extends Fragment {
    private GeneralContent content;
    View v;
    ListView lv_articles;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {        
        v = inflater.inflate(R.layout.layout_aktuelles, container, false);
        initData();
        return v;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if(!hidden) {
            resetListPos();
        }

    }


    private void resetListPos() {
        lv_articles.setSelection(0);
    }

    private void initData(){
    	lv_articles = (ListView) v.findViewById (R.id.articleLayout_aktuelles);
		lv_articles.setDivider(null);
		ListViewAdapterArticles adapter = new ListViewAdapterArticles(getActivity(), ((BischofshofApplikation)getActivity().getApplication()).getWeltenburgAktuelles().getArticles(), ((BischofshofApplikation)getActivity().getApplication()).getWeltenburgAktuelles().getHeader());
		lv_articles.setAdapter(adapter);	
		adapter.notifyDataSetChanged();	
    }
}
