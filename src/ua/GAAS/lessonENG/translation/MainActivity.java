package ua.GAAS.lessonENG.translation;

import java.io.File;
import java.util.Dictionary;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Environment;

import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewDebug.IntToString;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.translation.R;

public class MainActivity extends Activity implements OnInitListener{
	private  String []AraayFline; 
	// variable for selection intent
	private final int PICKER = 1;
	// variable to store the currently selected image
	public static int currentPic;
	private Gallery gallery;
	private ImageView bigimage;
	
    final public static String DIR_SD="/mnt/sdcard/Lesson/";
   	private ImageLessonAdapter adapter;

	//Language langSelected;
	static final String MY_LOG="LOG_TSL";
	private TextToSpeech tts;
	private int check_code = 0;
	private int check_IOF = 1;
	private TextToSpeech convert;
	/** Called when the activity is first created. */
	public void addPicture(Bitmap pic) {
		adapter.addPic(pic);
		// redraw the gallery thumbnails to reflect the new addition
		gallery.setAdapter(adapter);
		// display the newly selected image at larger size
		bigimage.setImageBitmap(pic);
		// scale options
		bigimage.setScaleType(ImageView.ScaleType.FIT_CENTER);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		bigimage = (ImageView) findViewById(R.id.picture);
		gallery = (Gallery) findViewById(R.id.gallery);
		// create a new adapter
		tts = new TextToSpeech(this, this);
	   	Intent check = new Intent();
	  	check.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
	   	startActivityForResult(check, check_code);
		// create a new adapter
		adapter = new ImageLessonAdapter(this);
		// set the gallery adapter
		gallery.setAdapter(adapter);
		// set long click listener for each gallery thumbnail item
		gallery.setOnItemLongClickListener(new OnItemLongClickListener() {
			//handle long clicks
			public boolean onItemLongClick(AdapterView<?> parent, View v,
				int position, long id) {
			
						
			return true;
			}
		});
		final OnItemClickListener itBtn = new OnItemClickListener(){
		
		//gallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// Выводим номер позиции при щелчке на картинке из галереи
				if(AraayFline==null) {Toast.makeText(MainActivity.this, "Виберіть Урок!" , Toast.LENGTH_SHORT).show();}
		else{
		// Выводим елементи масива при щелчке на картинке из галереи
		Toast.makeText(MainActivity.this,
		AraayFline[position] , Toast.LENGTH_SHORT).show();
		bigimage.setImageBitmap(adapter.getPic(position));
		speakOut(AraayFline[position]);
		}
	}};
	gallery.setOnItemClickListener(itBtn);
		
		Button illeft = (Button) findViewById(R.id.leftIt);
		
	Button bopenlesson = (Button) findViewById(R.id.bopenlesson); 
	Button butconfig = (Button) findViewById(R.id.butconfig);
	OnClickListener oclBtn = new OnClickListener() {
		    @Override
		    public void onClick(View v) {
	    	   // по id определеяем кнопку, вызвавшую этот обработчик
	           switch (v.getId()) {
	      
		          case R.id.butconfig:
		          //   кнопка конфіг
		        	   Intent intent = new Intent(MainActivity.this, ConfigLesson.class);
		        	    startActivity(intent);
		        	    break;
		         case R.id.bopenlesson:
		        	  int reqCode=0;
						Intent OLesson = new Intent(MainActivity.this, OpenLesson.class);
						startActivityForResult(OLesson, reqCode);
						 break;
		         case R.id.leftIt:
		        	itBtn.onItemClick(gallery, v, 1, 0);
		        //	String bb=  getString(gallery. );
		      //  	  Log.d(MY_LOG , "ID : " + bb );
						
	           }
		          }

			
		}; 
		butconfig.setOnClickListener(oclBtn);

		bopenlesson.setOnClickListener(oclBtn);
		illeft.setOnClickListener(oclBtn);
		}
	
		@Override
		public void onInit(int status) {
	       if (status == TextToSpeech.SUCCESS) {
	          Toast.makeText(MainActivity.this,"Engine is initialized", Toast.LENGTH_LONG).show();
	          int result = convert.setLanguage(Locale.ENGLISH);
	          if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
	             Log.i("TTS", "This Language is not supported");
	          } else {                
	              speakOut("Ich");
	             Log.i("TTS", "This Language is supported");
	             }
	          }
	        else if (status == TextToSpeech.ERROR) {
	           Toast.makeText(MainActivity.this,"Error occurred while initializing engine", Toast.LENGTH_LONG).show();
	        }
	    }
		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			/*if (requestCode == check_code) {
				   if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
				      // success, create the TTS instance
				      convert = new TextToSpeech(this, this);
				   } else {
				      // missing data, install it
				       Intent installIntent = new Intent();
				       installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				       startActivity(installIntent);
				   }}
				   */
			//if (requestCode ==check_IOF) {
			if (resultCode == RESULT_OK) {
				// check if we are returning from picture selection
				if (data == null) {return;}
				String FLesson ="";
				 FLesson = data.getStringExtra("Lesson");
				Log.i("MY_L", "-----"+DIR_SD+FLesson);
							
				TextView tViewL = (TextView) findViewById(R.id.tViewLes);
				tViewL.setText(FLesson);
				Boolean file_BL = true;
				AraayFline= IOFile.readFileSD(DIR_SD+FLesson+"/", FLesson+".txt", file_BL ) ;
				if (AraayFline != null){
					Log.i("MY_L", " Масив файлів - існує");}
				else{
					Log.i("MY_L", " Масив файлів - пустий");
					Toast.makeText(MainActivity.this, "Урок не завантажений !", Toast.LENGTH_SHORT).show();
				}
				String imgPath ="";
				adapter.delPic();
				adapter.notifyDataSetChanged();
				currentPic = 0;
				Bitmap pic = null;
				for (int i=0; i < IOFile.CLineFile; i++ ) {
					imgPath=DIR_SD +FLesson+"/"+ AraayFline[i]+".png";
					//Log.i("MY_L", imgPath);
					pic = BitmapFactory.decodeFile(imgPath );
					adapter.addPic(pic);
					currentPic++;
				}
			
				gallery.setAdapter(adapter);
				bigimage.setImageBitmap(pic);
				bigimage.setScaleType(ImageView.ScaleType.FIT_CENTER);
				}
			}
	
	private void speakOut(String text) {
      	tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
	public void onPause(){
	   if(tts !=null){
	      tts.stop();
	      tts.shutdown();
	   }
	   super.onPause();
	}
}