package ucla.nrl.seam;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.learning.DataSet;
import org.neuroph.core.learning.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.LMS;

public class NeuralNetworkTimeSeriesPredictor {

	public static final String NNTSP = "NNPredictor:";

	public static double getNextValue(double[] data){

		System.out.println(NNTSP+"Length of training data "+data.length);

		int maxIterations = 100000;
		NeuralNetwork neuralNet = new MultiLayerPerceptron(4, 9, 1);
		((LMS) neuralNet.getLearningRule()).setMaxError(0.001);//0-1
		((LMS) neuralNet.getLearningRule()).setLearningRate(0.7);//0-1
		((LMS) neuralNet.getLearningRule()).setMaxIterations(maxIterations);
		DataSet dataSet = new DataSet(4,1);

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
			dataSet.addRow(new DataSetRow(new double[]{(data[i] - datamin) / datamax, (data[i + 1] - datamin) / datamax, (data[i + 2] - datamin) / datamax, (data[i + 3] - datamin) / datamax}, new double[]{(data[i + 4] - datamin) / datamax}));

		}

		neuralNet.learn(dataSet);

		DataSet testSet = new DataSet(4);
		testSet.addRow(new DataSetRow(new double[]{(data[data.length-4]- datamin) / datamax, (data[data.length-3]- datamin) / datamax, (data[data.length-2] - datamin) / datamax, (data[data.length-1] - datamin) / datamax}));

		DataSetRow testElement = testSet.getRowAt(0);
		neuralNet.setInput(testElement.getInput());
		neuralNet.calculate();

		double[] networkOutput = neuralNet.getOutput();

		double pred = (networkOutput[0]) * datamax + datamin;

		return pred;
	}

	public static void main(String[] args){

		double[] data = {77.77D, 76.85D, 77.25D, 79.15D, 81.23D, 82.04D, 83.46D, 85.71D, 88.25D, 88.42D, 88.40D, 87.54D, //2008
				87.02D, 87.25D, 86.7D, 85.73D, 85.38D, 86.96D, 88.17D, 88.56D, 86.77D, 82.85D, 82.13D};
		System.out.println(NNTSP+"Predicted Value is "+getNextValue(data));
	}

}
