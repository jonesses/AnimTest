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
public class AusflugSchiffFragment extends Fragment {
    View v;
    ListView lv_articles;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.layout_ausflug_schiff, container, false);
        initData();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        resetListPos();

    }


    private void resetListPos() {
        lv_articles.setSelection(0);
    }

    private void initData() {
        lv_articles = (ListView) v.findViewById(R.id.articleLayout_ausflug_schiff);
        lv_articles.setDivider(null);
        ListViewAdapterArticles adapter = new ListViewAdapterArticles(getActivity(), ((BischofshofApplikation) getActivity().getApplication()).getWeltenburgAufNachWeltenburgSchiff().getArticles(), ((BischofshofApplikation) getActivity().getApplication()).getWeltenburgAufNachWeltenburgSchiff().getHeader());
        lv_articles.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
