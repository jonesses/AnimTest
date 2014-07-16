package com.example.animtest.animations;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.animtest.adapters.NavigationListAdapter;

import java.util.ArrayList;

import de.ur.mi.bischofshof.helpers.NavigationItem;
import de.ur.mi.projektion.bischofshof.CategoryConstants;
import de.ur.mi.projektion.bischofshof.listeners.OnCategorySelectedListener;
import de.ur.mi.projektion.bischofshof.listeners.OnFragmentInteractionListener;

/**
 * Created by Jonas on 15.06.2014.
 */
public class LeftSideFragment extends Fragment implements AdapterView.OnItemClickListener {

    View v;
    private OnFragmentInteractionListener listener;
    private NavigationListAdapter adapter;
    ArrayList<NavigationItem> navigationItems;
    private OnCategorySelectedListener categoryListener;

    private Activity parentaAtivity;
    private View previouslySelectedItem = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.parentaAtivity = activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        v = inflater.inflate(R.layout.left_side_fragment_layout, container, false);
        setContent();
        initAdapter();
        setListeners();
        return v;
    }

    private void initAdapter() {
        adapter = new NavigationListAdapter(parentaAtivity, R.layout.navigation_list_item, navigationItems);
        ListView listView;
        listView = (ListView) v.findViewById(R.id.navigation_listview);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setItemsCanFocus(true);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        adapter.setSelectedPosition(0);
        adapter.notifyDataSetChanged();
    }

    private void setContent() {
        navigationItems = new ArrayList<NavigationItem>();
        navigationItems.add(new NavigationItem("Aktuelles", getActivity().getResources().getColor(R.color.weltenburg_green_light), CategoryConstants.CATEGORY_AKTUELLES));
        navigationItems.add(new NavigationItem("Geschichte", getActivity().getResources().getColor(R.color.weltenburg_green_med), CategoryConstants.CATEGORY_GESCHICHTE));
        navigationItems.add(new NavigationItem("Produkte", getActivity().getResources().getColor(R.color.weltenburg_blue_med), CategoryConstants.CATEGORY_PRODUKTE));
        navigationItems.add(new NavigationItem("Ausflugsziel", getActivity().getResources().getColor(R.color.weltenburg_blue_dark), CategoryConstants.CATEGORY_AUSFLUG));

    }

    private void setListeners() {

    }


    public void setFragmentInteractionListener(OnFragmentInteractionListener listener) {
        this.listener = listener;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        adapter.setSelectedPosition(i);

        categoryListener.onCategorySelected(navigationItems.get(i).getItemColor());
        categoryListener.onCategoryChangeRequested(navigationItems.get(i).getCategory());
    }


    public void setCategorySelectedListener(OnCategorySelectedListener categoryListener){
        this.categoryListener = categoryListener;
    }


}
