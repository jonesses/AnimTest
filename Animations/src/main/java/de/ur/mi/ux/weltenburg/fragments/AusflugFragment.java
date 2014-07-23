package de.ur.mi.ux.weltenburg.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.animtest.animations.R;
import com.example.animtest.raphael.Logos;

import java.util.ArrayList;


/**
 * Created by Jonas (Bitch!!!) on 08.07.2014.
 */
public class AusflugFragment extends Fragment {
    private View v; 
    private ArrayList<Logos> weltenburg_aufNachWeltenburg_logos;
	private ImageView imageButton1, imageButton2, imageButton3, imageButton4, germanButton, englishButton;
	private Logos logo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {        
        v = inflater.inflate(R.layout.layout_aufnach, container, false);
        initData();
        return v;
    }
    
    private void initData(){
    	referenceClasses();
    	initUIElements();
    	onClickListener();
    }
    
	private void referenceClasses() {
		weltenburg_aufNachWeltenburg_logos = ((BischofshofApplikation)getActivity().getApplication()).getWeltenburgAufNachWeltenburgLogos();	
	}

	private void initUIElements() {

		imageButton1 = (ImageView) v.findViewById (R.id.bt_car);
		imageButton2 = (ImageView) v.findViewById (R.id.bt_boat);
		imageButton3 = (ImageView) v.findViewById (R.id.bt_bike);
		imageButton4 = (ImageView) v.findViewById (R.id.bt_hike);
	}


	
	private void onClickListener(){

	}
}
