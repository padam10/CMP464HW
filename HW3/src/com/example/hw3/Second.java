package com.example.hw3;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Second extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		SharedPreferences preference1 = PreferenceManager.getDefaultSharedPreferences(this);
		Button button = (Button) findViewById(R.id.button1);
		boolean checked = preference1.getBoolean("box2", false);
		
		if(checked == true){
			button.setTextAppearance(this, R.style.default_Style);
		
		}
		else button.setTextAppearance(this, R.style.alternate_style);
	}

	public void thirdActivity(View view) {
		Intent intent = new Intent(this,ThirdActivity.class);
		startActivity(intent);
	}

	

}
