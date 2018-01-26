package com.example.fileconfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


import android.os.Environment;


public class FileConfigUtil{
	
	public static String getBaseUrl(){
		
		try {
	    	String pathname = "";
	    	if ( 0 == Environment.getExternalStorageState().compareTo(Environment.MEDIA_MOUNTED))
	    		pathname = Environment.getExternalStorageDirectory().getAbsolutePath().toString();
	        else
	        	pathname = Environment.getDataDirectory().getAbsolutePath().toString();
	    	
	    	File toget = new File(pathname,"/selmoFronLinerURL.cfg");
	    	FileInputStream fin = new FileInputStream(toget);
	    	InputStream inputStream = fin;

	        if ( inputStream != null ) {
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	            String receiveString = "";
	            int count = 0;
	            while ( (receiveString = bufferedReader.readLine()) != null && count == 0 ) {
	            	count++;
	            	String removeWhiteSpace = receiveString.replace("\\s+", "");
	            	String split0[] = removeWhiteSpace.split(";");
	                String split1[] = split0[0].split("=");
	                
	               
	                System.out.print("KEPO" +split1[1]);
	                return split1[1];
	                
	            }

	            inputStream.close();
	        }
	    }
	    catch (FileNotFoundException e) {
	    	/* todo: handle exception*/
	    	return "";
	    } catch (IOException e) {
	    	/* todo: handle exception*/
	    	return "";
	    }
		
		return null;
	}

//	public static Map<String,String> readFromFile() throws ResponseCodeException {
//
//		Map<String,String> map = new HashMap<String, String>();
//
//	    try {
//	    	String pathname = "";
//	    	if ( 0 == Environment.getExternalStorageState().compareTo(Environment.MEDIA_MOUNTED))
//	    		pathname = Environment.getExternalStorageDirectory().getAbsolutePath().toString();
//	        else
//	        	pathname = Environment.getDataDirectory().getAbsolutePath().toString();
//	    	
//	    	File toget = new File(pathname,"/Coba.cfg");
//	    	FileInputStream fin = new FileInputStream(toget);
//	    	InputStream inputStream = fin;
//
//	        if ( inputStream != null ) {
//	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//	            String receiveString = "";
//	            int count = 0;
//	            while ( (receiveString = bufferedReader.readLine()) != null && count == 0 ) {
//	            	count++;
//	            	String removeWhiteSpace = receiveString.replace("\\s+", "");
//	            	String split0[] = removeWhiteSpace.split(";");
//	                String split1[] = split0[0].split("=");
//	                
//	                baseUrl = split1[1];
//	                System.out.print("KEPO" +baseUrl);
//	                
//	            }
//
//	            inputStream.close();
//	        }
//	    }
//	    catch (FileNotFoundException e) {
//	    	throw new ResponseCodeException("file config tidak ditemukan !");
//	    } catch (IOException e) {
//	    	throw new ResponseCodeException("file config error");
//	    }
//
//	    return null;
//	}

	 
}
