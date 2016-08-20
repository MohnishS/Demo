package com.example.permissiontest;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;

public class DemoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		MyDbHelper helper = new MyDbHelper(this);
		helper.insertProduct(1, "A", "A ka description", 100);
		helper.insertProduct(2, "B", "B ka description", 200);
		helper.insertProduct(3, "C", "C ka description", 300);
		helper.insertProduct(4, "D", "D ka description", 400);
		helper.insertProduct(5, "E", "E ka description", 500);
		helper.insertProduct(6, "F", "F ka description", 600);
		
		ArrayList<String> arrayList = helper.getAllProducts();
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.println("Hurray "+arrayList.get(i));
		}
	}
	
}
