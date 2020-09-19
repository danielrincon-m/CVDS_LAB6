package edu.eci.cvds.servlet;


import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;

@ManagedBean(name="calculadoraBean")
@ApplicationScoped

public class BackingBean {
    private final ArrayList<Double> numbers = new ArrayList<>();
    private double mean;
    private double standardDeviation;
    private double variance;
    private double mode;

    public void calculateMean() {
        double sum = 0;
        for (Double number : numbers) {
            sum += number;
        }
        mean = sum / numbers.size();
    }

    public void calculateStandardDeviation() {
        calculateVariance();
        standardDeviation = Math.sqrt(variance);
    }

    public void calculateVariance() {
        double variance = 0;
        calculateMean();
        for (Double number : numbers) {
            variance += Math.pow(number - mean, 2);
        }
        this.variance =  variance / numbers.size();
    }

    public void calculateMode() {
        double maxValue = 0, maxCount = 0;

        for (int i = 0; i < numbers.size(); ++i) {
            int count = 0;
            for (Double number : numbers) {
                if (number.equals(numbers.get(i))) ++count;
            }
            if (count > maxCount) {
                maxCount = count;
                maxValue = numbers.get(i);
            }
        }

        mode = maxValue;
    }

    public void restart() {
        numbers.clear();
        calculateData();
    }

    public void addNumber(double number) {
        numbers.add(number);
        System.out.println(numbers.toString());
        calculateData();
    }

    private void calculateData() {
        calculateMean();
        calculateMode();
        calculateStandardDeviation();
        calculateVariance();
    }

    public int getNumberQuantity() {
        return numbers.size();
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public double getMode() {
        return mode;
    }

    public double getMean() {
        return mean;
    }

    public double getVariance() {
        return variance;
    }
}
