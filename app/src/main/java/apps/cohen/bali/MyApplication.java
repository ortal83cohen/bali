package apps.cohen.bali;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class MyApplication extends Application implements App.Provider {

    public static final String API_KEY_ROTTEN_TOMATOES = "54wzfswsa4qmjg8hjwa64d4c";

    private static MyApplication sInstance;

    private ObjectGraph mObjectGraph;

    public static MyApplication getInstance() {
        return sInstance;
    }

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }

    public static ObjectGraph provide(Context context) {
        return get(context).getObjectGraph();
    }

    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    public static void saveToPreferences(Context context, String preferenceName,
            String preferenceValue) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName,
            String defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        return sharedPreferences.getString(preferenceName, defaultValue);
    }

    @Override
    public ObjectGraph getObjectGraph() {
        return mObjectGraph;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mObjectGraph = new ObjectGraph(this);
        sInstance = this;
    }
}
