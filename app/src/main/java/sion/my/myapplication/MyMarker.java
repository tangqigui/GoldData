package sion.my.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.Utils;

public class MyMarker extends MarkerView {
    private TextView textView;
    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public MyMarker(Context context, int layoutResource) {
        super(context, layoutResource);
        textView=findViewById(R.id.tvContent);
        textView.setTextColor(Color.WHITE);
    }


    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        textView.setText("" + Utils.formatNumber(e.getY(), 0, true));
        super.refreshContent(e, highlight);
    }
}
