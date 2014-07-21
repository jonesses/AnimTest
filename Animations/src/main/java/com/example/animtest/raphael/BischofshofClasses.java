package com.example.animtest.raphael;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class BischofshofClasses{	
	
	public ArrayList<String> getJpgFilesInDir(String dir, Context mContext) {
		ArrayList<String> myFiles = new ArrayList<String>();
		File f = new File(Environment.getExternalStorageDirectory().toString() + dir);
		f.mkdirs();
		File[] files = f.listFiles();
		if (files.length == 0) {
			return null;
		} else {
			for (int i = 0; i < files.length; i++) {
				if (files[i].getName().contains(".jpg")) {
					myFiles.add(files[i].getName());
				}
			}
		}
		return myFiles;
	}
	
	public ArrayList<String> getPngFilesInDir(String dir, Context mContext) {
		ArrayList<String> myFiles = new ArrayList<String>();
		File f = new File(Environment.getExternalStorageDirectory().toString() + dir);
		f.mkdirs();
		File[] files = f.listFiles();
		if (files.length == 0) {
			return null;
		} else {
			for (int i = 0; i < files.length; i++) {
				if (files[i].getName().contains(".png")) {
					myFiles.add(files[i].getName());
				}
			}
		}
		return myFiles;
	}
	
	public ArrayList<BitmapItem> getProdukte(String string, Context mContext) {
		ArrayList<BitmapItem> items = new ArrayList<BitmapItem>();
		ArrayList<String> myFiles = getPngFilesInDir(string, mContext);
		Bitmap currentBitmap;
		
		for(int i = 0; i < myFiles.size(); i++){
			currentBitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+string+myFiles.get(i));
			items.add(new BitmapItem((myFiles.get(i).substring(0, myFiles.get(i).length() - 4)),currentBitmap));
		}	
		
		return items;
	}
	
	
	
	public ArrayList<String> getTextFilesInDir(String dir, Context mContext) {
		ArrayList<String> myFiles = new ArrayList<String>();
		File f = new File(Environment.getExternalStorageDirectory().toString() + dir);
		f.mkdirs();
		File[] files = f.listFiles();
		if (files.length == 0) {
			return null;
		} else {
			for (int i = 0; i < files.length; i++) {
				if (files[i].getName().contains(".txt")) {
					myFiles.add(files[i].getName());
				}
			}
		}
		return myFiles;
	}
	
	

	public String getTextInDir(String string, Context mContext) {		
		ArrayList<String> myFiles = new ArrayList<String>();
		String text = "";		
		
		File f = new File(Environment.getExternalStorageDirectory().toString() + string);
		f.mkdirs();
		File[] files = f.listFiles();
		if (files.length == 0) {
			return null;
		} else {
			for (int i = 0; i < files.length; i++) {
				if (files[i].getName().contains(".txt")) {
					myFiles.add(files[i].getName());
				}
			}
		}		
		
		for(int i = 0; i < myFiles.size(); i++){
			text += readTxtDatei(string+myFiles.get(i), mContext);
		}		
	
		return text;
	}

	private String readTxtDatei(String string, Context mContext) {
		StringBuilder text = new StringBuilder();		
		File sdcard = Environment.getExternalStorageDirectory();
		File file = new File(sdcard, string);	
		try {
		    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "ISO-8859-1"));
		    String line;
		    while ((line = br.readLine()) != null) {
		        text.append(line);
		        text.append('\n');
		    }		    
		    br.close();
		}
		catch (IOException e) {
		}
		
		return text.toString();
	}

	public ArrayList<String> getTextFiles(String string, Context mContext) {
		ArrayList<String> texts = new ArrayList<String>();
		ArrayList<String> textNames = new ArrayList<String>();
		
		textNames = getTextFilesInDir(string, mContext);
		
		if(textNames!=null){
			for(int i = 0; i < textNames.size(); i++){
				texts.add(readTxtDatei(string+textNames.get(i), mContext));
			}	
		}
		
		return texts;
	}

	public ArrayList<BitmapItem> getBitmapItems(String string, Context mContext) {
		ArrayList<BitmapItem> items = new ArrayList<BitmapItem>();
		ArrayList<String> myFiles = getJpgFilesInDir(string, mContext);
		Bitmap currentBitmap;
		
		for(int i = 0; i < myFiles.size(); i++){
			currentBitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+string+myFiles.get(i));
			items.add(new BitmapItem((myFiles.get(i).substring(0, myFiles.get(i).length() - 4)),currentBitmap));
		}	
		
		return items;
	}
	
	public ArrayList<BitmapItem> getLogos(String string, Context mContext) {
		ArrayList<BitmapItem> items = new ArrayList<BitmapItem>();
		ArrayList<String> myFiles = getJpgFilesInDir(string, mContext);
		Bitmap currentBitmap;
		
		for(int i = 0; i < myFiles.size(); i++){
			currentBitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+string+myFiles.get(i));
			items.add(new BitmapItem((myFiles.get(i).substring(0, myFiles.get(i).length() - 4)),currentBitmap));
		}	
		
		return items;
	}
	
	public ArrayList<StringItem> getTextItems(String string, Context mContext){
		ArrayList<StringItem> items = new ArrayList<StringItem>();
		ArrayList<String> myFiles = getTextFilesInDir(string, mContext);
		
		for(int i = 0; i < myFiles.size(); i++){
			items.add(new StringItem(Integer.parseInt(myFiles.get(i).substring(0, myFiles.get(i).length() - 4)), readTxtDatei(string+myFiles.get(i), mContext)));			
		}		
		
		return items;		
	}
}
