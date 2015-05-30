package apps.cohen.bali;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class MyApplication extends Application implements App.Provider{

    private ObjectGraph mObjectGraph;
    public static final String API_KEY_ROTTEN_TOMATOES = "54wzfswsa4qmjg8hjwa64d4c";

    private static MyApplication sInstance;


    public static MyApplication getInstance() {
        return sInstance;
    }

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }
    public static ObjectGraph provide(Context context) {
        return ((MyApplication)get(context)).getObjectGraph();
    }
    @Override
    public ObjectGraph getObjectGraph() {
        return mObjectGraph;
    }

    public static Application get(Context context) {
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
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }
}
