package com.example.hw5;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParserException;
import com.example.hw4.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.ListView;


public class MainActivity extends Activity{
	
	private BroadcastReceiver receiver = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			//bundles are used by activities to pass data to themselves in the future
			Bundle bundle = intent.getExtras();
			if (bundle != null){
				boolean checkUpdate = bundle.getBoolean("update");
				if(checkUpdate){
					updateDatabase(context);
				}
			}
			
		}
		
		
	};
	
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listview = (ListView) findViewById(R.id.listView); 
        final Activity goTo = this;
        
        // this is a no name thread
        (new Thread(new Runnable(){
        	ArrayList<RssItem> RssItem;
			@Override
			public void run() {
				try {
					
					this.RssItem = MyPullParser.parse("http://www.nba.com/rss/nba_rss.xml");
					MainActivity.this.runOnUiThread(new Runnable(){
						@Override
						public void run() {
							listview.setAdapter(new MyAdapter(goTo, RssItem));
							
						}
					});
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
        })).start();
    }




	private void updateDatabase(Context context) {
		dataManager dm = new dataManager(context);
		
	} 
	
	protected void onRestart() {
		super.onRestart();
		registerReceiver(receiver, new IntentFilter());
	}
	
	protected void onDestroy(){
		super.onDestroy();
	}
}
   