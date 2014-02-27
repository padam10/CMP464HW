package com.example.hw3;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SharedPreferences preference1 = PreferenceManager
				.getDefaultSharedPreferences(this);
		Button button = (Button) findViewById(R.id.button1);
		boolean checked = preference1.getBoolean("box1", false);

		if (checked == true) {
			button.setTextAppearance(this, R.style.default_Style);

		} else
			button.setTextAppearance(this, R.style.alternate_style);
	}

	public void secondActivity(View view) {
		Intent intent = new Intent(this, Second.class);
		startActivity(intent);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}

	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_settings) {
			Intent intent = new Intent(this, Settings.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}

}
