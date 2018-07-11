package com.example.lzc.myspms.custom;

import android.graphics.Color;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2018/6/10.
 */

public class LineChartManager {
    private LineChart mLineChart;
    private YAxis leftAxis;
    private YAxis rightAxis;
    private XAxis xAxis;

    public LineChartManager(LineChart lineChart) {
        this.mLineChart = lineChart;
        leftAxis = mLineChart.getAxisLeft();
        rightAxis = mLineChart.getAxisRight();
        xAxis = mLineChart.getXAxis();
    }

    /**
     * 初始化LineChart
     */
    private void initLineChart() {
        //背景颜色
        mLineChart.setBackgroundColor(Color.WHITE);
        //网格
        mLineChart.setDrawGridBackground(false);
        //显示边界
        mLineChart.setDrawBorders(true);
        //设置动画效果
        mLineChart.animateY(1000, Easing.EasingOption.Linear);
        mLineChart.animateX(1000, Easing.EasingOption.Linear);

        //左下角的图例 标签 设置
        Legend legend = mLineChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(11f);
        //显示位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        //XY轴的设置
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        //保证Y轴从0开始，不然会上移一点
        leftAxis.setAxisMinimum(0f);
        rightAxis.setAxisMinimum(0f);
    }

    /**
     * 展示柱状图(一条)
     *
     * @param xAxisValues
     * @param yAxisValues
     * @param label
     * @param color
     */
    public void showLineChart(List<Integer> xAxisValues, List<Float> yAxisValues, String label, int color) {
        initLineChart();
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < yAxisValues.size(); i++) {
            entries.add(new Entry( i,yAxisValues.get(i)));
        }
        // 每一个LineDataSet代表一条折线
        LineDataSet lineDataSet = new LineDataSet(entries, label);

        lineDataSet.setColor(color);
        lineDataSet.setValueTextSize(9f);
        lineDataSet.setFormLineWidth(1f);
        lineDataSet.setFormSize(15.f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        LineData data = new LineData(dataSets);
        //设置X轴的刻度数
        xAxis.setLabelCount(xAxisValues.size()-1 , false);
        mLineChart.setData(data);
    }

    /**
     * 展示柱状图(多条)
     *
     * @param xAxisValues
     * @param yAxisValues
     * @param labels
     * @param colours
     */
    public void showLineChart(List<Float> xAxisValues, List<List<Float>> yAxisValues, List<String> labels, List<Integer> colours) {
        initLineChart();
        LineData data = new LineData();
        for (int i = 0; i < yAxisValues.size(); i++) {
            ArrayList<Entry> entries = new ArrayList<>();
            for (int j = 0; j < yAxisValues.get(i).size(); j++) {
                entries.add(new Entry( j,yAxisValues.get(i).get(j)));
            }
            LineDataSet lineDataSet = new LineDataSet(entries, labels.get(i));

            lineDataSet.setColor(colours.get(i));
            lineDataSet.setValueTextColor(colours.get(i));
            lineDataSet.setValueTextSize(10f);
            lineDataSet.setDrawValues(true);
            lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
            data.addDataSet(lineDataSet);
        }
        int amount = yAxisValues.size();

        //x轴的间隔 xAxisValues.size()-1就是x的一个值代表一个格子 默认就是这个值 后面的参数为true时才根据前面的值进行设置
        xAxis.setLabelCount(xAxisValues.size()-1 , false);

        mLineChart.setData(data);
    }


    /**
     * 设置Y轴值
     *
     * @param max
     * @param min
     * @param labelCount
     */
    public void setYAxis(float max, float min, int labelCount) {
        if (max < min) {
            return;
        }
        leftAxis.setAxisMaximum(max);
        leftAxis.setAxisMinimum(min);
        leftAxis.setLabelCount(labelCount, false);

        rightAxis.setAxisMaximum(max);
        rightAxis.setAxisMinimum(min);
        rightAxis.setLabelCount(labelCount, false);
        mLineChart.invalidate();
    }

    /**
     * 设置X轴的值
     *
     * @param max
     * @param min
     * @param labelCount
     */
    public void setXAxis(float max, float min, int labelCount) {
        xAxis.setAxisMaximum(max);
        xAxis.setAxisMinimum(min);
        xAxis.setLabelCount(labelCount, false);

        mLineChart.invalidate();
    }

    /**
     * 设置高限制线
     *
     * @param high
     * @param name
     */
    public void setHightLimitLine(float high, String name, int color) {
        if (name == null) {
            name = "高限制线";
        }
        LimitLine hightLimit = new LimitLine(high, name);
        hightLimit.setLineWidth(4f);
        hightLimit.setTextSize(10f);
        hightLimit.setLineColor(color);
        hightLimit.setTextColor(color);
        leftAxis.addLimitLine(hightLimit);
        mLineChart.invalidate();
    }

    /**
     * 设置低限制线
     *
     * @param low
     * @param name
     */
    public void setLowLimitLine(int low, String name) {
        if (name == null) {
            name = "低限制线";
        }
        LimitLine hightLimit = new LimitLine(low, name);
        hightLimit.setLineWidth(4f);
        hightLimit.setTextSize(10f);
        leftAxis.addLimitLine(hightLimit);
        mLineChart.invalidate();
    }

    /**
     * 设置描述信息
     *
     * @param str
     */
    public void setDescription(String str) {
        Description description = new Description();
        description.setText(str);
        mLineChart.setDescription(description);
        mLineChart.invalidate();
    }
    /**
     * 设置描述信息
     *
     * @param str
     */
    public void setDescription(String str,float x, float y) {
        Description description = new Description();
        description.setText(str);
        description.setXOffset(x);
        description.setYOffset(y);
        mLineChart.setDescription(description);
        mLineChart.invalidate();
    }
}