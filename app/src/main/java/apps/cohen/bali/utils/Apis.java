package apps.cohen.bali.utils;

import android.content.Context;

import java.util.ArrayList;

import apps.cohen.bali.R;
import apps.cohen.bali.model.Category;
import apps.cohen.bali.model.Item;

/**
 * Created by inbal on 5/30/2015.
 */
public class Apis {

    Context mContext;

    public Apis(Context context) {
        mContext = context;
    }

    public ArrayList<Category> getCategories() {
        ArrayList list = new ArrayList();
        list.add(new Category(0, mContext.getString(R.string.mangal), "",
                R.drawable.ic_gril));
        list.add(new Category(1, mContext.getString(R.string.brit), "",
                R.drawable.ic_brit));
        list.add(new Category(2, mContext.getString(R.string.marige), "",
                R.drawable.ic_merige));
        list.add(new Category(3, mContext.getString(R.string.yomhuledet), "",
                R.drawable.ic_birthday));
        list.add(new Category(4, mContext.getString(R.string.buing_stuff), "",
                R.drawable.ic_super));
        list.add(new Category(5, mContext.getString(R.string.bar_mitsva), "",
                R.drawable.ic_bar_mitsva));

        return list;
    }

    public ArrayList<apps.cohen.bali.model.List> getLists() {
        ArrayList list = new ArrayList();
        list.add(new apps.cohen.bali.model.List(0, mContext.getString(R.string.mangal), "",
                "http://shtieble.net/news/images/posts/2013/02/header_5312.jpg"));
        list.add(new apps.cohen.bali.model.List(1, mContext.getString(R.string.brit), "",
                "http://www.geektime.co.il/wp-content/uploads/2014/01/shutterstock_129717818.jpg"));
        list.add(new apps.cohen.bali.model.List(2, mContext.getString(R.string.marige), "",
                "https://iplan.co.il/images/front/wedding.png"));
        list.add(new apps.cohen.bali.model.List(3, mContext.getString(R.string.yomhuledet), "",
                "http://www.1800flowers.co.il/images/itempics/160_large.jpg"));
        list.add(new apps.cohen.bali.model.List(4, mContext.getString(R.string.buing_stuff), "",
                "http://i.ebayimg.com/00/s/NzAwWDcwMA==/z/5S8AAOxyUrZS7htY/$_12.JPG"));

        return list;
    }

    public ArrayList<Item> getPopularItemsForCategory(int id) {
        ArrayList list = new ArrayList();
        for (int i=0 ; i<100 ; i++ ){
            switch (id) {
                case 0:
                    list.add(new Item(i,id, mContext.getString(R.string.mangal), "",
                            "http://shtieble.net/news/images/posts/2013/02/header_5312.jpg"));
                    break;
                case 1:
                    list.add(new Item(i,id, mContext.getString(R.string.brit), "",
                            "http://www.geektime.co.il/wp-content/uploads/2014/01/shutterstock_129717818.jpg"));
                    break;
                case 2:
                    list.add(new Item(i,id, mContext.getString(R.string.marige), "",
                            "https://iplan.co.il/images/front/wedding.png"));
                    break;
                case 3:
                    list.add(new Item(i,id, mContext.getString(R.string.yomhuledet), "",
                            "http://www.1800flowers.co.il/images/itempics/160_large.jpg"));
                    break;
                case 4:
                    list.add(new Item(i,id, mContext.getString(R.string.buing_stuff), "",
                            "http://i.ebayimg.com/00/s/NzAwWDcwMA==/z/5S8AAOxyUrZS7htY/$_12.JPG"));
                    break;
            }
        }
        return list;
    }
}
