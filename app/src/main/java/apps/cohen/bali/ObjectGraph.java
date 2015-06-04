package apps.cohen.bali;

import android.app.Application;

import java.util.ArrayList;

import apps.cohen.bali.model.List;

public class ObjectGraph {

    protected final Application app;

    private ArrayList<List> mMyLists;


    public ObjectGraph(Application application) {
        this.app = application;
    }

//    public TelephonyManager getTelephonyManager() {
//        return (TelephonyManager) app.getSystemService(Context.TELEPHONY_SERVICE);
//    }


    public ArrayList<List> getMyLists() {
        if (mMyLists == null) {
            mMyLists = new ArrayList<>();
            mMyLists.add(new apps.cohen.bali.model.List(0, app.getString(R.string.mangal), (0)));
            mMyLists.add(new apps.cohen.bali.model.List(1, app.getString(R.string.brit), (1)));
            mMyLists.add(new apps.cohen.bali.model.List(2, app.getString(R.string.marige), (2)));
            mMyLists.add(
                    new apps.cohen.bali.model.List(3, app.getString(R.string.yomhuledet), (3)));
            mMyLists.add(
                    new apps.cohen.bali.model.List(4, app.getString(R.string.buing_stuff), (4)));
        }
        return mMyLists;
    }
}
