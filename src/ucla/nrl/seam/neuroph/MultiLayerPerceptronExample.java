package ucla.nrl.seam.neuroph;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.core.learning.DataSet;
import org.neuroph.core.learning.DataSetRow;

import java.util.Arrays;
import org.neuroph.util.TransferFunctionType;

/**
* This sample shows how to create, train, save and load simple Multi Layer Perceptron
*/
public class MultiLayerPerceptronExample {

public static void main(String[] args) {

// create training set (logical XOR function)
DataSet trainingSet = new DataSet(2, 1);
trainingSet.addRow(new DataSetRow(new double[]{0, 0}, new double[]{0}));
trainingSet.addRow(new DataSetRow(new double[]{0, 1}, new double[]{1}));
trainingSet.addRow(new DataSetRow(new double[]{1, 0}, new double[]{1}));
trainingSet.addRow(new DataSetRow(new double[]{1, 1}, new double[]{0}));

// create multi layer perceptron
MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(TransferFunctionType.TANH, 2, 3, 1);
// learn the training set
myMlPerceptron.learn(trainingSet);

// test perceptron
System.out.println("Testing trained neural network");
testNeuralNetwork(myMlPerceptron, trainingSet);

// save trained neural network
myMlPerceptron.save("myMlPerceptron.nnet");

// load saved neural network
NeuralNetwork loadedMlPerceptron = NeuralNetwork.load("myMlPerceptron.nnet");

// test loaded neural network
System.out.println("Testing loaded neural network");
testNeuralNetwork(loadedMlPerceptron, trainingSet);

}

public static void testNeuralNetwork(NeuralNetwork nnet, DataSet tset) {

for(DataSetRow dataRow : tset.getRows()) {

nnet.setInput(dataRow.getInput());
nnet.calculate();
double[ ] networkOutput = nnet.getOutput();
System.out.print("Input: " + Arrays.toString(dataRow.getInput()) );
System.out.println(" Output: " + Arrays.toString(networkOutput) );

}

}

 

}
