package ua.GAAS.lessonENG.translation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import android.os.Environment;
import android.util.Log;

public class IOFile {
	 private static  String []Arraystr ;
	 private static  String MY_LOG ="MY_L";
	 public static int CLineFile = 0;
	 public static String [] readFileSD (String DIR_SD, String FName, Boolean File_SD) {
	    // проверяем доступность SD
		 Arraystr=null;
	    if (!Environment.getExternalStorageState().equals(
	        Environment.MEDIA_MOUNTED)) {
	      Log.d(MY_LOG , "SD-карта не доступна: " + Environment.getExternalStorageState());
	   //   return;
	    }
	    // получаем путь к SD
	//    File sdPath = Environment.getExternalStorageDirectory();
	    // добавляем свой каталог к пути
	    File sdPath = new File(DIR_SD);
	    // формируем объект File, который содержит путь к файлу
	    File sdFile = new File(sdPath, FName);
	    CLineFile =getLineCount(sdFile);
	   if (CLineFile>0) { 
	    try {
	    	  // открываем поток для чтения
	        BufferedReader br = new BufferedReader(new FileReader(sdFile));
	        Arraystr = new String [CLineFile];
	        String str = "";
	        int i=0;
	        // читаем содержимое
	        while ((str = br.readLine()) != null) {
	            Arraystr[i] =str;
	            i++;
	        }
	        br.close();
	        Log.d(MY_LOG, "Файл прочитан - ОК"); 
	    } catch (FileNotFoundException e) {
	    	 Arraystr =null;
	    	Log.d(MY_LOG, "Файл - ненайден");
	    	
	      e.printStackTrace();
	      return null;
	    } catch (IOException e) {
	    	 Arraystr =null;
	    	Log.d(MY_LOG, "Файл - ошибка чтения");
	    	File_SD=false;
	      e.printStackTrace();
	      return null;
	    }}
		return Arraystr;
	
	  }		
	
	public static int getLineCount(File file) {
		int CLine = 0;
		FileReader fr = null;
		try {
			fr = new FileReader(file);

			/**
			 * buffered character-input stream that keeps track of line numbers
			 */
			LineNumberReader lnr = new LineNumberReader(fr);
			while (lnr.readLine() != null) {
				CLine++;
			}
			lnr.close();

			return CLine;
		} catch (FileNotFoundException e) {
			Log.d(MY_LOG, "Файл - ненайден");
			e.printStackTrace();
			return 0;
		} catch (IOException e) {
			Log.d(MY_LOG, "Файл - ошибка чтения");
			e.printStackTrace();
			return 0;
		}

		finally {
			if (null != fr)
			   try {
				fr.close();
			   } catch (IOException e) {
				e.printStackTrace();
				
			}
		}
		
	}
}
