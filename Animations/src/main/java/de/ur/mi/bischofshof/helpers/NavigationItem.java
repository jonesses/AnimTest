package de.ur.mi.bischofshof.helpers;

/**
 * Created by Jonas on 07.07.2014.
 */
public class NavigationItem {
    private String title;
    private int color;
    private int category;

    public NavigationItem(String title, int color, int category){
        this.title = title;
        this.color = color;
        this.category = category;
    }

    public int getItemColor(){
        return color;
    }

    public String getItemTitle(){
        return title;
    }

    public int getCategory(){
        return category;
    }
}
