package com.example.animtest.raphael;

import android.app.Dialog;
import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.animtest.animations.R;


public class WeltenburgProdukteFragment extends Fragment implements OnClickListener{
	
	private BischofshofApplikation app;
	private Typeface font;
	private Carousel carousel;
	private LinearLayout backLayout;
	private View v;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.layout_weltenburg_produkte, container, false);
        font = Typeface.createFromAsset(getActivity().getAssets(), "font/Georgia.ttf");
        app = (BischofshofApplikation) getActivity().getApplicationContext();
        carousel = (Carousel) v.findViewById(R.id.carousel);
        backLayout = (LinearLayout) v.findViewById(R.id.backLayoutProdukte);
        backLayout.setOnClickListener(this);

        carousel.setOnItemClickListener(new CarouselAdapter.OnItemClickListener() {
            public void onItemClick(CarouselAdapter<?> parent, View view, int position, long id) {
                if (carousel.getSelectedPosition() == position) {
                    if (carousel.touchPositionIsCorrect()) {
                        Dialog dialog = new Dialog(getActivity().getApplicationContext());
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.getWindow().setBackgroundDrawableResource(R.color.translucent_black);
                        dialog.setContentView(R.layout.dialog_layout);
                        TextView header = (TextView) dialog.findViewById(R.id.dialog_header);
                        header.setText(app.getWeltenburgProdukte().get(position).getHeader());
                        header.setTypeface(font);
                        TextView text = (TextView) dialog.findViewById(R.id.dialog_text);
                        text.setText(app.getWeltenburgProdukte().get(position).getProduktDeText());
                        text.setTypeface(font);
                        dialog.show();
                    }
                } else {
                    int idH1 = carousel.getSelectedPosition() + 1;
                    int idH2 = carousel.getSelectedPosition() + 2;
                    int idH3 = carousel.getSelectedPosition() + 3;
                    int idN1 = carousel.getSelectedPosition() - 1;
                    int idN2 = carousel.getSelectedPosition() - 2;
                    int idN3 = carousel.getSelectedPosition() - 3;
                    if (carousel.getSelectedPosition() == app.getWeltenburgProdukte().size() - 1) {
                        idH1 = 0;
                        idH2 = 1;
                        idH3 = 2;
                    } else if (carousel.getSelectedPosition() == app.getWeltenburgProdukte().size() - 2) {
                        idH2 = 0;
                        idH3 = 1;
                    } else if (carousel.getSelectedPosition() == app.getWeltenburgProdukte().size() - 3) {
                        idH3 = 0;
                    } else if (carousel.getSelectedPosition() == 0) {
                        idN1 = app.getWeltenburgProdukte().size() - 1;
                        idN2 = app.getWeltenburgProdukte().size() - 2;
                        idN3 = app.getWeltenburgProdukte().size() - 3;
                    } else if (carousel.getSelectedPosition() == 1) {
                        idN2 = app.getWeltenburgProdukte().size() - 1;
                        idN3 = app.getWeltenburgProdukte().size() - 2;
                    } else if (carousel.getSelectedPosition() == 2) {
                        idN3 = app.getWeltenburgProdukte().size() - 1;
                    }

                    if (idH1 == position || idH2 == position || idH3 == position) {
                        carousel.setScrollLeft();
                        carousel.scrollIntoSlots();
                    } else if (idN1 == position || idN2 == position || idN3 == position) {
                        carousel.setScrollRight();
                        carousel.scrollIntoSlots();
                    }
                }
            }
        });
        return v;
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {	     
	     case R.id.backLayoutProdukte:
	    	 
	    	 break;
	     default:
	    	 break;
	     }		
	}    
}
