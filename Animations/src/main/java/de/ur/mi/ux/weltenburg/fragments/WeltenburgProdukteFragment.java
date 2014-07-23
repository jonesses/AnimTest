package de.ur.mi.ux.weltenburg.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.animtest.animations.MainActivity;
import com.example.animtest.animations.R;
import com.example.animtest.raphael.Carousel;
import com.example.animtest.raphael.CarouselAdapter;

import de.ur.mi.bischofshof.helpers.AnimationHelper;
import de.ur.mi.projektion.bischofshof.CategoryConstants;
import de.ur.mi.projektion.bischofshof.listeners.OnFragmentInteractionListener;

public class WeltenburgProdukteFragment extends android.support.v4.app.Fragment implements OnClickListener{
	
	private Typeface font, fontBold;
	private BischofshofApplikation app;
	private Carousel carousel;
	private TextView tvBackLayoutProdukte;
	private View v;
	private LinearLayout backLayoutProdukte;
    private OnFragmentInteractionListener interactionListener;
    private View dimmerbackground;

    @Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    v = inflater.inflate(R.layout.layout_weltenburg_produkte, container, false);
	    setFonts();
	    referenceUIElements();
	    initView();	
	    return v;
	  }
	
	
	private void setFonts() {
		font = Typeface.createFromAsset(getActivity().getAssets(), "font/Georgia.ttf");
		fontBold = Typeface.createFromAsset(getActivity().getAssets(), "font/Georgia_Bold.ttf");

	}


	private void referenceUIElements() {
		app = (BischofshofApplikation) getActivity().getApplicationContext();        
        carousel = (Carousel)v.findViewById(R.id.carousel);
        backLayoutProdukte = (LinearLayout) v.findViewById(R.id.backLayoutProdukte);
        backLayoutProdukte.setOnClickListener(this);
        tvBackLayoutProdukte = (TextView) v.findViewById(R.id.tvBackLayoutProdukte);
        tvBackLayoutProdukte.setTypeface(fontBold);
        dimmerbackground = v.findViewById(R.id.background_dialog_dimmer);
		
	}


	private void initView(){     
        carousel.setOnItemClickListener(new CarouselAdapter.OnItemClickListener(){
			public void onItemClick(CarouselAdapter<?> parent, View view, int position, long id) {					
				if(carousel.getSelectedPosition()==position){
					if(carousel.touchPositionIsCorrect()){
						Dialog dialog = new Dialog(getActivity());
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.getWindow().setBackgroundDrawableResource(R.color.translucent_black);
                        dialog.setContentView(R.layout.dialog_layout);


                        TextView header = (TextView) dialog.findViewById(R.id.dialog_header);
                        header.setText(app.getWeltenburgProdukte().get(position).getHeader());
                        header.setTypeface(font);
                        TextView text = (TextView) dialog.findViewById(R.id.dialog_text);
                        text.setText(app.getWeltenburgProdukte().get(position).getProduktDeText());
                        text.setTypeface(font);

                        AnimationHelper.dimDialogBackground(dimmerbackground);
                        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialogInterface) {
                                AnimationHelper.removeDialogBackground(dimmerbackground);
                            }
                        });
                        dialog.show();



                    }
				}else{
					int idH1 = carousel.getSelectedPosition()+1;
					int idH2 = carousel.getSelectedPosition()+2;
					int idH3 = carousel.getSelectedPosition()+3;
					int idN1 = carousel.getSelectedPosition()-1;
					int idN2 = carousel.getSelectedPosition()-2;
					int idN3 = carousel.getSelectedPosition()-3;
					if(carousel.getSelectedPosition()==app.getWeltenburgProdukte().size()-1){
						idH1 = 0;
						idH2 = 1;
						idH3 = 2;
					}else if(carousel.getSelectedPosition()==app.getWeltenburgProdukte().size()-2){
						idH2 = 0;
						idH3 = 1;
					}else if(carousel.getSelectedPosition()==app.getWeltenburgProdukte().size()-3){
						idH3 = 0;
					}else if(carousel.getSelectedPosition()==0){
						idN1 = app.getWeltenburgProdukte().size()-1;
						idN2 = app.getWeltenburgProdukte().size()-2;
						idN3 = app.getWeltenburgProdukte().size()-3;
					}else if(carousel.getSelectedPosition()==1){
						idN2 = app.getWeltenburgProdukte().size()-1;
						idN3 = app.getWeltenburgProdukte().size()-2;
					}else if(carousel.getSelectedPosition()==2){
						idN3 = app.getWeltenburgProdukte().size()-1;						
					}					
					
					if(idH1==position||idH2==position||idH3==position){
						carousel.setScrollLeft();
						carousel.scrollIntoSlots();					
					}else if(idN1==position||idN2==position||idN3==position){						
						carousel.setScrollRight();
						carousel.scrollIntoSlots();
					}
				}
			}        	
        });
    }   
	 
	 public void initShit(){
		 final Handler handler = new Handler();
		 handler.postDelayed(new Runnable() {
			 @Override
			 public void run() { 			   
				 carousel.refreshShit();
			 }
		}, 200);
	 }


	@Override
	public void onClick(View v) {
		 interactionListener.onTransitionFromFullscreenRequested(CategoryConstants.CATEGORY_PRODUKTE);
		 }


    public void setFragmentInteractionListener(OnFragmentInteractionListener interactionListener) {
        this.interactionListener = interactionListener;
    }
}