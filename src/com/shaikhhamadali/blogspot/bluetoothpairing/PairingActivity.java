package com.shaikhhamadali.blogspot.bluetoothpairing;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class PairingActivity extends Activity {
	//Create variables
	private static final int REQUEST_ENABLE_BT = 1;
	private static final int REQUEST_PAIRED_DEVICE = 2;

	//declare views/controls
	Button btnListPairedDevices;
	TextView tVBluetoothState;

	//declare Bluetooth Adapter
	BluetoothAdapter btAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pair);
		//get reference of the UI Controls
		btnListPairedDevices = (Button)findViewById(R.id.btnListPairedDevices);
		tVBluetoothState = (TextView)findViewById(R.id.tVBluetoothState);
		//Get Default bluetooth adapter
		btAdapter = BluetoothAdapter.getDefaultAdapter();
		//Check the state of bluetooth
		CheckBlueToothState();
		//set listner for the button.
		btnListPairedDevices.setOnClickListener(btnListPairedDevicesOnClickListener);
	}

	private void CheckBlueToothState(){
		//does device support Bluetooth 
		if (btAdapter == null){
			tVBluetoothState.setText("Bluetooth NOT support");
		}else{
			//is Bluetooth enable
			if (btAdapter.isEnabled()){
				//is in discovery mode
				if(btAdapter.isDiscovering()){
					tVBluetoothState.setText("Bluetooth is currently in device discovery process.");
				}else{
					tVBluetoothState.setText("Bluetooth is Enabled.");
					btnListPairedDevices.setEnabled(true);
				}
			}else{
				tVBluetoothState.setText("Bluetooth is NOT Enabled!");
				//Enable Bluetooth by intent 
				Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
				startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
			}
		}
	}

	private Button.OnClickListener btnListPairedDevicesOnClickListener= new Button.OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// Create instance of intent
			Intent intent = new Intent();
			//set class for intent 
			intent.setClass(PairingActivity.this, ListPairedDevicesActivity.class);
			//and start list activity for result
			startActivityForResult(intent, REQUEST_PAIRED_DEVICE); 
		}};

		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			// is the request code for enable bluetooth or the paired devices
			if(requestCode == REQUEST_ENABLE_BT){
				//check Bluetooth state
				CheckBlueToothState();
			}
			if (requestCode == REQUEST_PAIRED_DEVICE){
				//do nothing is result is ok
				if(resultCode == RESULT_OK){
				}
			} 
		}   
}