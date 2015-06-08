package com.example.thanhcs94;


import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
			.add(R.id.container, new PlaceholderFragment())
			.commit();
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public ConnectedDetector isConnected;
		Button btEnable, btDisable;
		WifiManager wifi;
		TextView tv;
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			isConnected =  new ConnectedDetector(getActivity().getApplicationContext());
			tv = (TextView) rootView.findViewById(R.id.isconnection);
			btEnable = (Button) rootView.findViewById(R.id.btenable);
			btDisable = (Button) rootView.findViewById(R.id.btdisable);

			//wifi manager
			wifi = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
			if(isConnected.isConnectingToInternet()==true)
			{
				tv.setText("Connected\n"+isConnected.arrString.get(0));
				btEnable.setEnabled(false);
				btDisable.setEnabled(true);
			}
			else
			{
				btEnable.setEnabled(true);
				btDisable.setEnabled(false);
				tv.setText("Disconnect");
			}


			btEnable.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					
					wifi.setWifiEnabled(true);
					tv.setText("Connected\n"+isConnected.arrString.get(0));
					btEnable.setEnabled(false);
					btDisable.setEnabled(true);
				}
			});


			btDisable.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					wifi.setWifiEnabled(false);
					tv.setText("Disconnect");
					btEnable.setEnabled(true);
					btDisable.setEnabled(false);

				}
			});

			return rootView;
		}
	}

}
