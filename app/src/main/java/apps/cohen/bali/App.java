package apps.cohen.bali;

import android.content.Context;


public class App {
    public static ObjectGraph provide(Context context) {
        return ((Provider) context.getApplicationContext()).getObjectGraph();
    }

    public interface Provider<T extends ObjectGraph> {
        T getObjectGraph();
    }
}
