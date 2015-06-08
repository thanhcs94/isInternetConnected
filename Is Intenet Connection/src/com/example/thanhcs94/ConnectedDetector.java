package com.example.thanhcs94;


import java.util.ArrayList;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectedDetector {

	 private Context _context;
     public static ArrayList<String> arrString;
  
	    public ConnectedDetector(Context context){
	        this._context = context;
	        arrString = new ArrayList<String>();
	    }
	 
	    public boolean isConnectingToInternet(){
	        ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
	          if (connectivity != null) 
	          {
	              NetworkInfo[] info = connectivity.getAllNetworkInfo();
	              if (info != null) 
	                  for (int i = 0; i < info.length; i++) 
	                  { 
	                	   String temp =  "Type name : "+info[i].getTypeName()+"\nType : "+info[i].getType()
	                	  +"\nReason : "+info[i].getReason();  
	                	   arrString.add(temp);//get infor
	                	 
	                      if (info[i].getState() == NetworkInfo.State.CONNECTED)
	                      {
	                          return true;
	                      }
	                  }
	 
	          }
	          return false;
	    }
}
