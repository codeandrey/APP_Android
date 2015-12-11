package ua.GAAS.lessonENG.translation;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.translation.R;
public class ConfigLesson extends Activity {
        private LessonAdapter adapter;
        private final ArrayList<LessonListENG> fetch = new ArrayList<LessonListENG>();
        private ListView lv;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.list);
                //заполняем массив данными
                LessonListENG one = new LessonListENG("1", "Lesson #1  скачать ","http://google.com.ua");
                LessonListENG two = new LessonListENG("2", "Lesson #2  скачать ",
                                "#");
                LessonListENG three = new LessonListENG("3", "Lesson #3  скачать ",
                                "#");
                //далее запоминаем их в массиве
                fetch.add(one);
                fetch.add(two);
                fetch.add(three);
                lv = (ListView) findViewById(R.id.listView1);
                //выводим в листе
                adapter = new LessonAdapter(ConfigLesson.this, R.id.listView1, fetch);
                lv.setAdapter(adapter);
        }
}
