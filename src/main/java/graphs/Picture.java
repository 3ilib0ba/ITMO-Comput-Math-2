package graphs;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.Map;

public class Picture extends ApplicationFrame {
    public static int counter = 0;

    public Picture(String title) {
        super(title);
    }

    public void graph(Map<Double,Double> points) {
        XYDataset dataset = generateDataset(points);
        JFreeChart chart = ChartFactory.createXYAreaChart(
                "Graph",
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );
        ChartPanel panel = new ChartPanel(chart);
        panel.setFillZoomRectangle(false);
        panel.setDomainZoomable(true);
        panel.setRangeZoomable(true);
        pack();
        setSize(800, 600);
        setLocationRelativeTo(null);
        setContentPane(panel);
        setVisible(true);
    }

    private XYDataset generateDataset(Map<Double, Double> points) {
        XYSeriesCollection dataset = new XYSeriesCollection();

        XYSeries series = new XYSeries(counter++);
        for (Map.Entry<Double, Double> point : points.entrySet()) {
                series.add(point.getKey(), point.getValue());
        }
        dataset.addSeries(series);
        return dataset;
    }


}
