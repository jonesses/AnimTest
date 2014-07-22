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
    private TextView textView1, textView2, textView3, textView4;
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
    	initImageAndTextViews();
    	onClickListener();
    }
    
	private void referenceClasses() {
		weltenburg_aufNachWeltenburg_logos = ((BischofshofApplikation)getActivity().getApplication()).getWeltenburgAufNachWeltenburgLogos();	
	}

	private void initUIElements() {
		textView1 = (TextView) v.findViewById (R.id.textView1);
		textView2 = (TextView) v.findViewById (R.id.textView2);
		textView3 = (TextView) v.findViewById (R.id.textView3);
		textView4 = (TextView) v.findViewById (R.id.textView4);
		imageButton1 = (ImageView) v.findViewById (R.id.imageButton1);
		imageButton2 = (ImageView) v.findViewById (R.id.imageButton2);
		imageButton3 = (ImageView) v.findViewById (R.id.imageButton3);
		imageButton4 = (ImageView) v.findViewById (R.id.imageButton4);
	}

	private void initImageAndTextViews() {
		logo = weltenburg_aufNachWeltenburg_logos.get(0);
		textView1.setText(logo.getGermanText());	
		imageButton1.setImageBitmap(logo.getBitmapLogo());
		logo = weltenburg_aufNachWeltenburg_logos.get(1);
		textView2.setText(logo.getGermanText());
		imageButton2.setImageBitmap(logo.getBitmapLogo());
		logo = weltenburg_aufNachWeltenburg_logos.get(2);
		textView3.setText(logo.getGermanText());
		imageButton3.setImageBitmap(logo.getBitmapLogo());
		logo = weltenburg_aufNachWeltenburg_logos.get(3);
		textView4.setText(logo.getGermanText());
		imageButton4.setImageBitmap(logo.getBitmapLogo());	
	}
	
	private void onClickListener(){

	}
}
