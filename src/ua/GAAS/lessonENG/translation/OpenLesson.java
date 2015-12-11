package ua.GAAS.lessonENG.translation;


import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.example.translation.R;
public class OpenLesson extends  Activity{

    DirectoryItemAdapter adapter;
    String currentpath=MainActivity.DIR_SD;
    protected void showDir(String path) {
   	 
   	   File dir = new File(path);
       adapter.clear();
       //получам обєкти які знаходяться по поточному шляху
       File[] files = dir.listFiles();
       if (files != null) { 
       for (File  f : files)
          if (f.isDirectory()){
          	  adapter.add(f.getName());
             adapter.notifyDataSetChanged();
          }
       }
       currentpath = path;
    }

   private File File(String currentpath2) {
		// TODO Auto-generated method stub
		return null;
	}

public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.folder);
		// находим список
		final ListView lvMain = (ListView) findViewById(R.id.lvMain);
		//установлюємо одиночний вибір
		lvMain.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		//створюємо адаптер
		adapter = new DirectoryItemAdapter(this, android.R.layout.simple_list_item_single_choice);
       // присваиваем адаптер списку
       lvMain.setAdapter(adapter);
       //отбражам список папок
       showDir(currentpath);
       lvMain.setOnItemClickListener(new OnItemClickListener() { 
           @Override 
           public void onItemClick(AdapterView<?> arg0, View arg1, 
               int position, long arg3) { 
                
           	String folder_Les = "";
               folder_Les = adapter.getItem(position).toString();
               Log.i("MY_L","Вибраний урок : "+folder_Les);
				Intent intent = new Intent();
				intent.putExtra("Lesson", folder_Les);
				setResult(RESULT_OK, intent);
				finish();
            } 
        }); 
    } 
}
