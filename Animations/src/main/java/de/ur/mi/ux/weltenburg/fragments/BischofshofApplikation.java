package de.ur.mi.ux.weltenburg.fragments;

import java.util.ArrayList;
import android.app.Application;
import android.graphics.Bitmap;

import com.example.animtest.raphael.GeneralContent;
import com.example.animtest.raphael.Geschichte;
import com.example.animtest.raphael.Logos;
import com.example.animtest.raphael.Produkte;

public class BischofshofApplikation extends Application {

	private Boolean isEnglish;
	private Bitmap weltenburg_logo;
	private ArrayList<Produkte> weltenburg_produkte;
	private ArrayList<Geschichte> weltenburg_geschichte;
	private ArrayList<Logos> weltenburg_aufNachWeltenburg_logos;
	private GeneralContent weltenburg_aktuelles, weltenburg_geschichte_allgemeines, weltenburg_produkte_allgemeines;
	private GeneralContent weltenburg_route_rad, weltenburg_route_schiff, weltenburg_route_fuss, weltenburg_route_auto;
	
	public BischofshofApplikation() {
		super();
	}
	
	public void setLanguageIsEnglish(Boolean b){
		this.isEnglish = b;
	}
	
	public Boolean getLanguageIsEnglish(){
		return isEnglish;
	}

	public void setWeltenburgLogo(Bitmap weltenburg_logo) {
		this.weltenburg_logo = weltenburg_logo;		
	}
	
	public Bitmap getWeltenburgLogo(){
		return weltenburg_logo;
	}
	
	public void setWeltenburgAktuelles(GeneralContent weltenburg_aktuelles) {
		this.weltenburg_aktuelles = weltenburg_aktuelles;		
	}
	
	public GeneralContent getWeltenburgAktuelles(){
		return weltenburg_aktuelles;
	}

	public void setWeltenburgProdukte(ArrayList<Produkte> weltenburg_produkte) {
		this.weltenburg_produkte = weltenburg_produkte;
	}
	
	public ArrayList<Produkte> getWeltenburgProdukte(){
		return weltenburg_produkte;
	}

	public void setWeltenburgGeschichte(ArrayList<Geschichte> weltenburg_geschichte) {
		this.weltenburg_geschichte = weltenburg_geschichte;		
	}
	
	public ArrayList<Geschichte> getWeltenburgGeschichte(){
		return weltenburg_geschichte;
	}

	public void setWeltenburgAufNachWeltenburgLogos(ArrayList<Logos> weltenburg_aufNachWeltenburg_logos) {
		this.weltenburg_aufNachWeltenburg_logos = weltenburg_aufNachWeltenburg_logos;		
	}
	
	public ArrayList<Logos> getWeltenburgAufNachWeltenburgLogos(){
		return weltenburg_aufNachWeltenburg_logos;
	}

	public void setWeltenburgAufNachWeltenburgRad(GeneralContent weltenburg_route_rad) {
		this.weltenburg_route_rad = weltenburg_route_rad;		
	}

	public GeneralContent getWeltenburgAufNachWeltenburgRad(){
		return weltenburg_route_rad;
	}
	
	public void setWeltenburgAufNachWeltenburgFuss(GeneralContent weltenburg_route_fuss) {
		this.weltenburg_route_fuss = weltenburg_route_fuss;		
	}

	public GeneralContent getWeltenburgAufNachWeltenburgFuss(){
		return weltenburg_route_fuss;
	}
	
	public void setWeltenburgAufNachWeltenburgAuto(GeneralContent weltenburg_route_auto) {
		this.weltenburg_route_auto = weltenburg_route_auto;		
	}

	public GeneralContent getWeltenburgAufNachWeltenburgAuto(){
		return weltenburg_route_auto;
	}
	
	public void setWeltenburgAufNachWeltenburgSchiff(GeneralContent weltenburg_route_schiff) {
		this.weltenburg_route_schiff = weltenburg_route_schiff;		
	}

	public GeneralContent getWeltenburgAufNachWeltenburgSchiff(){
		return weltenburg_route_schiff;
	}
	
	public void setWeltenburgGeschichteAllgemeines(GeneralContent weltenburg_geschichte_allgemeines) {
		this.weltenburg_geschichte_allgemeines = weltenburg_geschichte_allgemeines;		
	}
	
	public GeneralContent getWeltenburgGeschichteAllgemeines() {
		return weltenburg_geschichte_allgemeines;		
	}

	public void setWeltenburgProdukteAllgemeines(GeneralContent weltenburg_produkte_allgemeines) {
		this.weltenburg_produkte_allgemeines = weltenburg_produkte_allgemeines;		
	}
	
	public GeneralContent getWeltenburgProdukteAllgemeines() {
		return weltenburg_produkte_allgemeines;		
	}
}
