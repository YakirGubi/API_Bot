package org.example;

import io.quickchart.QuickChart;

public class Graph {

    private QuickChart chart = new QuickChart();
    private int[] requestsLastDays = {0,0,0};
    private String config;

    public Graph(int[] requestsLastDays){
        this.requestsLastDays = requestsLastDays;
        setConfig();
        chart.setWidth(800);
        chart.setHeight(385);
        chart.setConfig(config);
    }
    public void setConfig (){
        this.config = "{"
                + "    type: 'bar',"
                + "    data: {"
                + "        labels: ['two days ago', 'yesterday', 'today'],"
                + "        datasets: [{"
                + "            label: 'Requests Last Days',"
                + "            data: [";
        for(int i = 0 ; i < this.requestsLastDays.length - 1 ; i++){
            this.config += this.requestsLastDays[i] + ", ";
        }
        this.config += this.requestsLastDays[this.requestsLastDays.length-1] + "";
        this.config += "]"
                + "        }]"
                + "    }"
                + "}";
        this.chart.setConfig(this.config);
    }

    public QuickChart getChart() {
        return chart;
    }

    public String getConfig() {
        return config;
    }

}
