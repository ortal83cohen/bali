package apps.cohen.bali.adapters;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;
import android.widget.TextView;

import apps.cohen.bali.R;


public class ViewItem extends GridLayout {

    private TextView mAwayScore;

    public ViewItem(Context context) {
        super(context);
    }

    public ViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    public String toString() {
        return mAwayScore.getText() + "v"
                + ": " + getLeft() + "," + getTop()
                + ": " + getMeasuredWidth() + "x" + getMeasuredHeight();
    }
}
