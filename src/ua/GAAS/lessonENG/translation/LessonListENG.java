package ua.GAAS.lessonENG.translation;

/** загрузка списка з сайта 
 * @param id lesson 
 * @param name  lesson 
 * @param url  lesson */

public class LessonListENG {

    String id;
    String name;
    String url;
   
    public LessonListENG(String id, String name, String url) {

            this.id = id;
            this.name = name;
            this.url = url;
    }

    public String getId() {

            return id;
    }

    public String getName() {

            return name;
    }

    public String getUrl() {

            return url;
    }

    public void setId(String id) {

            this.id = id;
    }

    public void setName(String name) {

            this.name = name;
    }

    public void setUrl(String url) {

            this.url = url;
    }
}
