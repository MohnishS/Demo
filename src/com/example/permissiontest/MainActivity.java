package com.example.permissiontest;
import android.Manifest;
import android.app.Activity;
import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;


public class MainActivity extends Activity{

	final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 0;
	final int REQUEST_CODE_ENABLE_ADMIN = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		openAppSettingsScreen();
//		retrievePermissionsFromManifest();
		grantPermission();
//		addPermission();
		
		
		//Test case 1
		//Check for permission
		/*int permissionCheck = ContextCompat.checkSelfPermission(this,
		        Manifest.permission.RECORD_AUDIO);
		if(permissionCheck != PackageManager.PERMISSION_GRANTED)
		{
			ActivityCompat.requestPermissions(this,
					new String[]{Manifest.permission.RECORD_AUDIO},
					MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
		}*/

		//Test case 2
		/*// Here, thisActivity is the current activity
		if (ContextCompat.checkSelfPermission(this,
				Manifest.permission.READ_EXTERNAL_STORAGE)
				!= PackageManager.PERMISSION_GRANTED) {

			// Should we show an explanation?
			if (ActivityCompat.shouldShowRequestPermissionRationale(this,
					Manifest.permission.READ_EXTERNAL_STORAGE)) {

				// Show an expanation to the user *asynchronously* -- don't block
				// this thread waiting for the user's response! After the user
				// sees the explanation, try again to request the permission.

			} else {

				// No explanation needed, we can request the permission.

				ActivityCompat.requestPermissions(this,
						new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
						MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

				// MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
				// app-defined int constant. The callback method gets the
				// result of the request.
			}
		}*/
	}

	private void addPermission() {
		PackageManager pm = getPackageManager();
		PermissionInfo info = new PermissionInfo();
		info.name = Manifest.permission.READ_EXTERNAL_STORAGE;
		pm.addPermission(info);
		
		int permissionCheck = ContextCompat.checkSelfPermission(this,
		        Manifest.permission.READ_EXTERNAL_STORAGE);
		if(permissionCheck == PackageManager.PERMISSION_GRANTED)
		{
			Toast.makeText(this, "Read external storage permission granted", Toast.LENGTH_LONG).show();
		}
		else if(permissionCheck == PackageManager.PERMISSION_DENIED)
		{
			Toast.makeText(this, "Read external storage permission denied", Toast.LENGTH_LONG).show();
		}
		
	}

	private void retrievePermissionsFromManifest() {
		try{
			PackageManager manager = this.getPackageManager();
			PackageInfo info = manager.getPackageInfo(this.getPackageName(), PackageManager.GET_PERMISSIONS);
			String[] pi = info.requestedPermissions;
			for(int i = 0; i < pi.length; i++)
			{
				Log.d("MainActivity ", "MainActivity permission name "+pi[i]);
				if(pi[i].equalsIgnoreCase(Manifest.permission.READ_EXTERNAL_STORAGE))
				{
					Toast.makeText(this, "Read external storage is present", Toast.LENGTH_LONG).show();
				}
			}
		}
		catch(Exception e)
		{
			Toast.makeText(this, "Exception "+e.getMessage(), Toast.LENGTH_SHORT).show();
		}
		
	}

	private void openAppSettingsScreen() {
		startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
				Uri.fromParts("package", getPackageName(), null)));
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
	
	private void grantPermission()
	{
		int permissionCheck = ContextCompat.checkSelfPermission(this,
				Manifest.permission.READ_EXTERNAL_STORAGE);
		if(permissionCheck != PackageManager.PERMISSION_GRANTED)
		{
//			ComponentName cm = new ComponentName(this, MainActivity.class);
			ComponentName cm = new ComponentName(this, DeviceAdminReceiver.class);
			DevicePolicyManager admin = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
			boolean bIsActiveAdmin = admin.isAdminActive(cm);
//			admin.setPermissionPolicy(getComponentName(), DevicePolicyManager.PERMISSION_POLICY_AUTO_GRANT);		
//			boolean flag = admin.setPermissionGrantState(cm, getPackageName(), Manifest.permission.READ_EXTERNAL_STORAGE, DevicePolicyManager.PERMISSION_GRANT_STATE_GRANTED);
			Toast.makeText(MainActivity.this, "Permission granted ", Toast.LENGTH_SHORT).show();
		}
		else
		{
			Toast.makeText(MainActivity.this, "Permission already granted", Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode,
			String permissions[], int[] grantResults) {
		switch (requestCode) {
		case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
			// If request is cancelled, the result arrays are empty.
			if (grantResults.length > 0
					&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {

				// permission was granted, yay! Do the
				// contacts-related task you need to do.

			} else {

				// permission denied, boo! Disable the
				// functionality that depends on this permission.
			}
			return;
		}
		}
	}
}
