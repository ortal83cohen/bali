package apps.cohen.bali.model;

/**
 * Created by inbal on 5/30/2015.
 */
public class Category {

    private int id;

    private String name;

    private String url;

    private int image;

    public Category(int id, String name, String url, int image) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
