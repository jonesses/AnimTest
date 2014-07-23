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

import de.ur.mi.projektion.bischofshof.CategoryConstants;
import de.ur.mi.projektion.bischofshof.listeners.OnFragmentInteractionListener;


/**
 * Created by Jonas (Bitch!!!) on 08.07.2014.
 */
public class ProdukteAllgemeinesFragment extends Fragment {
    View v;
    private OnFragmentInteractionListener fragmentInteractionListener;
    private Button bt_articleLayout_produkte_allgemeines;
    private Typeface font, fontBold;
    ListView lv_articles;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.layout_produkte_allgemeines, container, false);
        setFonts();
        initData();
        return v;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            resetListPos();
        }

    }


    private void resetListPos() {
        lv_articles.setSelection(0);
    }

    public void setOnFragmentInteractionListener(OnFragmentInteractionListener fragmentInteractionListener) {
        this.fragmentInteractionListener = fragmentInteractionListener;
    }

    private void setFonts() {
        font = Typeface.createFromAsset(getActivity().getAssets(), "font/Georgia.ttf");
        fontBold = Typeface.createFromAsset(getActivity().getAssets(), "font/Georgia_Bold.ttf");

    }

    private void initData() {
        v.findViewById(R.id.bt_articleLayout_produkte_allgemeines).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentInteractionListener.onTransitionToFullscreenRequested(CategoryConstants.CATEGORY_PRODUKTE);
            }
        });
        bt_articleLayout_produkte_allgemeines = (Button) v.findViewById(R.id.bt_articleLayout_produkte_allgemeines);
        bt_articleLayout_produkte_allgemeines.setTypeface(fontBold);
        lv_articles = (ListView) v.findViewById(R.id.articleLayout_produkte_allgemeines);
        lv_articles.setDivider(null);
        ListViewAdapterArticles adapter = new ListViewAdapterArticles(getActivity(), ((BischofshofApplikation) getActivity().getApplication()).getWeltenburgProdukteAllgemeines().getArticles(), ((BischofshofApplikation) getActivity().getApplication()).getWeltenburgProdukteAllgemeines().getHeader());
        lv_articles.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
