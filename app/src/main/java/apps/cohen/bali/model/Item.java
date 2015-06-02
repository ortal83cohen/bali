package apps.cohen.bali.model;


public class Item {

    private int id;

    private String name;

    private String url;

    private String image;

    private int mCategoryId;

    public Item(int id,int categoryId, String name, String url, String image) {
        mCategoryId = categoryId;
        this.id = id;
        this.name = name;
        this.url = url;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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


    public int getCategoryId() {
        return mCategoryId;
    }
}
