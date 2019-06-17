package sion.my.myapplication;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

import sion.my.myapplication.Until.NetworkListining;
import sion.my.myapplication.Until.OftenUntil;
import sion.my.myapplication.Until.OkhttpUntil;
import sion.my.myapplication.bean.GoldBean;
import sion.my.myapplication.bean.Index;
import sion.my.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    private CombinedChart mycahrt;
    private Thread thread;
    private CandleData candleData;
    private LineData lineData;
    private Spinner mysp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OftenUntil.ChangestatusBar(this, "#515A63");
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();
    }

    private void initView() {
        mycahrt = (CombinedChart) findViewById(R.id.mycahrt);
        mycahrt.setBackgroundColor(Color.parseColor("#1B2025"));

        Setbtf();
        getdata();


        mysp = (Spinner) findViewById(R.id.mysp);
        mysp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Index index=new Index(position);
                activityMainBinding.setPosition(index);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    XAxis xAxis;

    private void Setbtf() {
        xAxis = mycahrt.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGridColor(Color.GRAY);
        xAxis.setTextColor(Color.WHITE);
        mycahrt.getAxisLeft().setTextColor(Color.WHITE);
        mycahrt.getAxisRight().setTextColor(Color.WHITE);
        mycahrt.getDescription().setEnabled(false);
        List<String> labels = new ArrayList<>();
        labels.add("红涨");
        labels.add("绿跌");
        Legend legend = mycahrt.getLegend();
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        legend.setExtra(colors, labels);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
       // legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setTextColor(Color.WHITE);
        legend.setFormSize(15);
        legend.setTextSize(15);
        mycahrt.setExtraBottomOffset(50);
        mycahrt.setScaleEnabled(false);
        mycahrt.setPinchZoom(false);
        mycahrt.setMarker(new MyMarker(this, R.layout.custom_marker_view));

    }

    public static boolean isstop = false;

    private void getdata() {
        OkhttpUntil.enqueueGetrequest("http://apicloud.mob.com/gold/spot/query?key=2b5d07443a9aa", GoldBean.class, new NetworkListining<GoldBean>() {
            @Override
            public void BackResultSuccess(GoldBean bean, int code) {
                if (bean != null) {
                    boolean equals = bean.msg.equals("success");
                    if (equals) {
                        List<String> XX = new ArrayList<>();

                        activityMainBinding.setGold(bean);
                        List<GoldBean.ResultBean> result = bean.getResult();
                        CandleDataSet candleDataSet = setCandleData("", result,XX);
                        LineDataSet lineDataSet = lineDataSet(result, "#C21DD3", "开盘价");
                        // LineDataSet lineDataSet2 = lineDataSet(result, "#FFF100","收盘价");
                        CombinedData combinedData = new CombinedData();
                        combinedData.setData(new LineData(lineDataSet));
                        combinedData.setData(new CandleData(candleDataSet));

                        mycahrt.setData(combinedData);
                        xAxis.setValueFormatter(new IndexAxisValueFormatter(XX));
                        xAxis.setAxisMinimum(-0.5f);
                        xAxis.setLabelCount(XX.size());
                        xAxis.setAxisMaximum(XX.size());
                        xAxis.setLabelRotationAngle(50);
                        mycahrt.invalidate();
                        mycahrt.animateX(2000);

                    }
                }


            }

            @Override
            public void BackResultFail(Exception errow) {
                Log.i("AAA", errow.getMessage());
            }

            @Override
            public void tostring(String responseString) {

            }
        });
    }

    /*      "closePri": "301.00",
      "highPic": "301.45",
      "limit": "0.20%",
      "lowPic": "299.60",
      "name": "沪金9999",
      "openPri": "301.45",
      "time": "2019-06-13 23:43:24",
      "totalTurnover": "3369878750.00",
      "totalVol": "11212.00",
      "variety": "AU99.99",
      "yesDayPic": "300.41"*/
    /*    public CandleEntry(float x, float shadowH, float shadowL, float open, float close) {*/
    public CandleDataSet setCandleData(String msg, List<GoldBean.ResultBean> result,List<String> XX) {
        List<CandleEntry> candleEntries = new ArrayList<>();
        List<Float> mm=new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            GoldBean.ResultBean bean = result.get(i);
            if(bean.getClosePri().equals("--")){
                continue;
            }
            XX.add(bean.getName());
            candleEntries.add(new CandleEntry(candleEntries.size(), Toint(bean.getHighPic()), Toint(bean.getLowPic()), Toint(bean.getOpenPri()), Toint(bean.getClosePri())));
        }


        CandleDataSet candleDataSet = new CandleDataSet(candleEntries, msg);
        candleDataSet.setShadowColor(Color.DKGRAY);//影线颜色
        candleDataSet.setShadowColorSameAsCandle(true);//影线颜色与实体一致
        candleDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        candleDataSet.setShadowColor(Color.DKGRAY);//影线颜色
        candleDataSet.setShadowColorSameAsCandle(true);//影线颜色与实体一致

        candleDataSet.setShadowWidth(0.7f);//影线
        candleDataSet.setDecreasingColor(Color.RED);
        candleDataSet.setDecreasingPaintStyle(Paint.Style.FILL);//红涨，实体
        candleDataSet.setIncreasingColor(Color.GREEN);
        candleDataSet.setIncreasingPaintStyle(Paint.Style.STROKE);//绿跌，空心
        candleDataSet.setNeutralColor(Color.RED);//当天价格不涨不跌（一字线）颜色
        candleDataSet.setHighlightLineWidth(1f);//选中蜡烛时的线宽
        candleDataSet.setDrawValues(false);//在图表中的元素上面是否显示数值
        //图表名称，可以通过mChart.getLegend().setEnable(true)显示在标注上*/

        return candleDataSet;
    }

    public float Toint(String msg) {
        try {
            float abc = (Double.valueOf(msg)).floatValue();

            return abc;
        } catch (Exception e) {
            return 0;
        }

    }

    public void reflush(View view) {
        getdata();
    }

    public LineDataSet lineDataSet(List<GoldBean.ResultBean> result, String color, String label) {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            if(result.get(i).getClosePri().equals("--")){
                continue;
            }
            entries.add(new Entry(entries.size(), Toint(result.get(i).getOpenPri())));
        }
        LineDataSet lineDataSet = new LineDataSet(entries, label);
        lineDataSet.setColor(Color.parseColor(color));
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawValues(false);

        lineDataSet.setHighlightEnabled(false);
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        return lineDataSet;
    }

    public LineDataSet lineDataSet2(List<GoldBean.ResultBean> result, String color, String label) {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            entries.add(new Entry(i, Toint(result.get(i).getOpenPri())));
        }
        LineDataSet lineDataSet = new LineDataSet(entries, label);
        lineDataSet.setColor(Color.parseColor(color));
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawValues(false);

        lineDataSet.setHighlightEnabled(false);
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        return lineDataSet;
    }
}
