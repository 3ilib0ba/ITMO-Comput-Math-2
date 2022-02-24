package graphs;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.*;

import java.awt.*;
import java.util.Map;

public class Picture extends ApplicationFrame {
    public static int counter = 0;

    public Picture(String title) {
        super(title);
    }

    public void graph(Map<Double,Double> points) {
        IntervalXYDataset dataset = generateDataset(points);
        JFreeChart chart = ChartFactory.createXYAreaChart(
                "Graph",
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                false
        );

        chart.setBackgroundPaint(Color.green);
        chart.getPlot().setOutlinePaint(Color.black);//setBackgroundPaint(Color.black);
        chart.getPlot().setOutlineStroke(new BasicStroke(2));//setBackgroundPaint(Color.black);


        ChartPanel panel = new ChartPanel(chart);
        panel.setFillZoomRectangle(false);
        panel.setDomainZoomable(true);
        panel.setRangeZoomable(true);
        panel.setBackground(Color.green);
        pack();
        setSize(800, 600);
        setLocationRelativeTo(null);
        setContentPane(panel);

        setVisible(true);

        //setBackground(Color.white);
    }

    private IntervalXYDataset generateDataset(Map<Double, Double> points) {
        XYIntervalSeriesCollection dataset = new XYIntervalSeriesCollection();

        XYIntervalSeries series = new XYIntervalSeries(counter++);
        for (Map.Entry<Double, Double> point : points.entrySet()) {
                series.add(point.getKey(), point.getKey(), point.getKey(), point.getValue(), point.getValue(), point.getValue());
        }

        dataset.addSeries(series);

        return dataset;
    }


}
