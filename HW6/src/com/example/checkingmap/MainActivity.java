package com.example.checkingmap;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends FragmentActivity {
	private final LatLng LEHCOL = new LatLng(40.873318, -73.894139);
	private final LatLng WHOUSE  = new LatLng(38.898748, -77.037684);
	
	GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setUpMapIfNeeded();
        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
    }   
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    	getMenuInflater().inflate(R.menu.main, menu);
		return true;
    }
    
    public void onClick_Lehman(View v){
    	map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
    	CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LEHCOL,20);
    	map.addMarker(new MarkerOptions().position(LEHCOL).title("Hello lehman"));
    	map.animateCamera(update);
    	
    }
    
    public void onClick_LehmanS(View v){
    	map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    	CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LEHCOL,15);
    	map.addMarker(new MarkerOptions().position(LEHCOL).title("Lehman College"));
    	map.animateCamera(update);
    	
    }
    public void onClick_WHouse(View v){
    	map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    	CameraUpdate update = CameraUpdateFactory.newLatLngZoom(WHOUSE,15);
    	map.addMarker(new MarkerOptions().position(WHOUSE).title("Hello Obama"));
    	map.animateCamera(update);
	
    }
 
}





