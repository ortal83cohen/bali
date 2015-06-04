package apps.cohen.bali.model;

import apps.cohen.bali.R;

/**
 * Created by inbal on 5/30/2015.
 */
public class List {

    private final int color;
    private int id;

    private String name;


    private int image;

    public List(int id, String name, int category) {
        this.id = id;
        this.name = name;
        this.image = getImageForCategories(category);
        this.color = getBackgroundColor(category);
    }

    public int getImageForCategories(int id) {
        switch (id) {
            case 0:
                return R.drawable.ic_gril;
            case 1:
                return R.drawable.ic_brit;
            case 2:
                return R.drawable.ic_merige;
            case 3:
                return R.drawable.ic_birthday;
            case 4:
                return R.drawable.ic_super;
            case 5:
            default:
                return R.drawable.ic_bar_mitsva;

        }
    }

    public int getBackgroundColor(int id) {
        switch (id) {
            case 0:
                return (R.color.colorCategory0);
            case 1:
                return (R.color.colorCategory1);
            case 2:
                return (R.color.colorCategory2);
            case 3:
                return (R.color.colorCategory3);
            case 4:
                return (R.color.colorCategory4);
            case 5:
            default:
                return (R.color.colorCategory5);

        }

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

    public int getColor() {
        return color;
    }
}
