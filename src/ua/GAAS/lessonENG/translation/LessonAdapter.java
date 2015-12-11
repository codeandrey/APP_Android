package ua.GAAS.lessonENG.translation;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.translation.R;


public class LessonAdapter extends ArrayAdapter<LessonListENG> { // унаследовали от  класса LessonListENG

        private final Activity activity;
        //
        private final ArrayList<LessonListENG> entries;

        // конструктор класса, принимает активность, листвью и массив данных
        public LessonAdapter(final Activity a, final int textViewResourceId, final ArrayList<LessonListENG> entries) {

                super(a, textViewResourceId, entries);
                this.entries = entries;
                activity = a;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

                View v = convertView;
                ViewHolder holder;
                if (v == null) {
                        LayoutInflater inflater = (LayoutInflater) activity
                                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        v = inflater.inflate(R.layout.lesson_list, parent, false);
                        holder = new ViewHolder();
                        // инициализируем нашу разметку
                        holder.textView = (TextView) v.findViewById(R.id.label);
                        holder.imageView = (ImageView) v.findViewById(R.id.logo);
                        v.setTag(holder);
                } else {
                        holder = (ViewHolder) v.getTag();
                }
                LessonListENG LessonListENG = entries.get(position);
                if (LessonListENG != null) {
                        // получаем текст из массива
                        holder.textView.setText(LessonListENG.getName());
                        // скачиваем lesson
                       // ImageLoader imageLoader = ImageLoader.getInstance();
                   //     imageLoader.init(ImageLoaderConfiguration.createDefault(getContext()));
                     //   imageLoader.displayImage(LessonListENG.getUrl(), holder.imageView);
                }
                return v;
        }

        // для быстроты вынесли в отдельный класс
        private static class ViewHolder {

                public ImageView imageView;
                public TextView textView;
        }
}
