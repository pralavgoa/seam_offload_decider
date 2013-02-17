package ucla.nrl.seam.neuroph;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.learning.DataSet;
import org.neuroph.core.learning.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.LMS;


public class NeurophTest {

	//2 years of data for prices of an item
	private static final double[] data = {77.77D, 76.85D, 77.25D, 79.15D, 81.23D, 82.04D, 83.46D, 85.71D, 88.25D, 88.42D, 88.40D, 87.54D, //2008
			87.02D, 87.25D, 86.7D, 85.73D, 85.38D, 86.96D, 88.17D, 88.56D, 86.77D, 82.85D, 82.13D};//2009
	
	private static void testNeuroph(){
		
		 System.out.println("Time stamp N1:" + new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss:MM").format(new Date()));

	        int maxIterations = 100000;
	        NeuralNetwork neuralNet = new MultiLayerPerceptron(4, 9, 1);
	        ((LMS) neuralNet.getLearningRule()).setMaxError(0.001);//0-1
	        ((LMS) neuralNet.getLearningRule()).setLearningRate(0.7);//0-1
	        ((LMS) neuralNet.getLearningRule()).setMaxIterations(maxIterations);
	        DataSet dataSet = new DataSet(4,1);

	        // task: will be the chicken more expensive as today?
	        double datamax = -9999.0D;
	        double datamin = 9999.0D;
	        for (int i = 0; i < 22; i++) {

	            if (data[i] > datamax) {
	                datamax = data[i];
	            }
	            if (data[i] < datamin) {
	                datamin = data[i];
	            }
	        }

	        datamax = datamax * 1.2D;
	        datamin = datamin * 0.8D;

	        for (int i = 0; i < data.length - 5; i++) {
	            System.out.println(data[i] + " " + data[i + 1] + " " + data[i + 2] + " " + data[i + 3] + "->" + data[i + 4]);
	            dataSet.addRow(new DataSetRow(new double[]{(data[i] - datamin) / datamax, (data[i + 1] - datamin) / datamax, (data[i + 2] - datamin) / datamax, (data[i + 3] - datamin) / datamax}, new double[]{(data[i + 4] - datamin) / datamax}));
	       
	        }

	        System.out.println("Training network please wait...");

	        neuralNet.learn(dataSet);

	        System.out.println("Time stamp N2:" + new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss:MM").format(new Date()));

	        DataSet testSet = new DataSet(4);
	        testSet.addRow(new DataSetRow(new double[]{(88.17D - datamin) / datamax, (88.56D - datamin) / datamax, (86.77D - datamin) / datamax, (82.85D - datamin) / datamax}));
	        double middle = (88.17D + 88.56D + 86.77D + 82.85D) / 4.0D;
	        for (DataSetRow testElement : testSet.getRows()) {
	            neuralNet.setInput(testElement.getInput());
	            neuralNet.calculate();
	           double[] networkOutput = neuralNet.getOutput();
	            //System.out.print("Input: " + testElement.getInput()+"\n");//to test
	            //System.out.println(" Output: " + networkOutput);//to test
	            double pred = (networkOutput[0]) * datamax + datamin;
	            System.out.printf("Middle price of chicken for last 4 months =%4.2f(US cents per pound)\nPredicted price of chicken for next month =%4.2f(US cents per pound)\n", middle, pred);
	            if (pred < middle) {
	                System.out.print("You can buy. It will not be expensive.)");
	            } else {
	                System.out.print("Chicken might be more expensive!");
	            }
	        }


	        System.out.println("Time stamp N3:" + new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss:MM").format(new Date()));
	}
	
	
	public static void main(String[] args){
		System.out.println("Starting the Neuroph test...");
		
		testNeuroph();
	}
}
