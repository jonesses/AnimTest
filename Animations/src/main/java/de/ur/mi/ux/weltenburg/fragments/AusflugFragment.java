package de.ur.mi.ux.weltenburg.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.animtest.animations.R;
import com.example.animtest.raphael.Logos;

import java.util.ArrayList;


/**
 * Created by Jonas (Bitch!!!) on 08.07.2014.
 */
public class AusflugFragment extends Fragment implements View.OnClickListener {
    private View v; 
    private ArrayList<Logos> weltenburg_aufNachWeltenburg_logos;
	private ImageView imageButton1, imageButton2, imageButton3, imageButton4, germanButton, englishButton;
	private Logos logo;
    private FrameLayout container;
    private android.app.FragmentManager fManager;

    private AusflugAutoFragment ausflugAuto;
    private AusflugFussFragment ausflugFuss;
    private AusflugSchiffFragment ausflugSchiff;
    private AusflugRadFragment ausflugRad;

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
        ausflugAuto = new AusflugAutoFragment();
        ausflugFuss = new AusflugFussFragment();
        ausflugRad = new AusflugRadFragment();
        ausflugSchiff = new AusflugSchiffFragment();
        fManager = getFragmentManager();
        imageButton1 = (ImageView) v.findViewById (R.id.bt_car);
        imageButton2 = (ImageView) v.findViewById (R.id.bt_boat);
        imageButton3 = (ImageView) v.findViewById (R.id.bt_bike);
        imageButton4 = (ImageView) v.findViewById (R.id.bt_hike);
        imageButton1.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
        imageButton3.setOnClickListener(this);
        imageButton4.setOnClickListener(this);
        container = (FrameLayout) v.findViewById(R.id.ausflug_container);
        fManager.beginTransaction().add(R.id.ausflug_container, ausflugAuto).commit();
    }


	
	private void onClickListener(){

	}

    @Override
    public void onClick(View view) {
        FragmentTransaction transaction = fManager.beginTransaction().setCustomAnimations(R.anim.fade_in_trip, R.anim.fade_out_trip);
        switch (view.getId()){
            case R.id.bt_car:
                transaction.replace(R.id.ausflug_container, ausflugAuto);
                break;
            case R.id.bt_boat:
                transaction.replace(R.id.ausflug_container, ausflugSchiff);

                break;
            case R.id.bt_bike:
                transaction.replace(R.id.ausflug_container, ausflugRad);

                break;
            case R.id.bt_hike:
                transaction.replace(R.id.ausflug_container, ausflugFuss);

                break;
        }
        transaction.commit();

    }
}
