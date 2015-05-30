package apps.cohen.bali.adapters;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;
import android.widget.TextView;

import apps.cohen.bali.R;


public class CategoryView extends GridLayout {

    private TextView mAwayScore;

    public CategoryView(Context context) {
        super(context);
    }

    public CategoryView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CategoryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mAwayScore = (TextView) findViewById(R.id.text_score_away);
    }

    @Override
    public String toString() {
        return mAwayScore.getText() + "v"
                + ": " + getLeft() + "," + getTop()
                + ": " + getMeasuredWidth() + "x" + getMeasuredHeight();
    }
}
