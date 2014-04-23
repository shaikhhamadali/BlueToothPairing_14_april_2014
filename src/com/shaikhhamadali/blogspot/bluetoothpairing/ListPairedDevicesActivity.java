package com.shaikhhamadali.blogspot.bluetoothpairing;

import java.util.Set;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ListPairedDevicesActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//Create ArrayAdapter to store the list of paired Bluetooth Devices
		ArrayAdapter<String> btArrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
		//Create instance of Bluetooth Adapter
		BluetoothAdapter bluetoothAdapter= BluetoothAdapter.getDefaultAdapter();
		//create Set instance to get the set of Paired devices  
		Set<BluetoothDevice> pairedDevices= bluetoothAdapter.getBondedDevices();
		//check that device has paired devices list or not
		if (pairedDevices.size() > 0) {
			//loop through the every paired device.
			for (BluetoothDevice device : pairedDevices) {
				//Get Name of the paired Device	  
				String deviceBTName = device.getName();
				//Get Class of the Paired Device
				String deviceBTMajorClass= getBTMajorDeviceClass(device.getBluetoothClass().getMajorDeviceClass());
				//Add name and class of the Paired device
				btArrayAdapter.add(deviceBTName + "\n" + deviceBTMajorClass);
			}
		}
		//Set list Adapter to the ListActivity
		setListAdapter(btArrayAdapter);

	}

	private String getBTMajorDeviceClass(int major){
		//Types of the Bluetooth Classes
		switch(major){ 
		case BluetoothClass.Device.Major.AUDIO_VIDEO:
			return "AUDIO_VIDEO";
		case BluetoothClass.Device.Major.COMPUTER:
			return "COMPUTER";
		case BluetoothClass.Device.Major.HEALTH:
			return "HEALTH";
		case BluetoothClass.Device.Major.IMAGING:
			return "IMAGING"; 
		case BluetoothClass.Device.Major.MISC:
			return "MISC";
		case BluetoothClass.Device.Major.NETWORKING:
			return "NETWORKING"; 
		case BluetoothClass.Device.Major.PERIPHERAL:
			return "PERIPHERAL";
		case BluetoothClass.Device.Major.PHONE:
			return "PHONE";
		case BluetoothClass.Device.Major.TOY:
			return "TOY";
		case BluetoothClass.Device.Major.UNCATEGORIZED:
			return "UNCATEGORIZED";
		case BluetoothClass.Device.Major.WEARABLE:
			return "AUDIO_VIDEO";
		default: return "unknown!";
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		//set Result OK and finish the list activity
		Intent intent = new Intent();
		setResult(RESULT_OK, intent);
		finish();
	}

}
