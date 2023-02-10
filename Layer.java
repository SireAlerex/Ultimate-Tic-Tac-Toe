public class Layer {
    private int inputNodeCount, outputNodeCount;
    private double[][] weights;
    private double[] biases;

    public Layer(int inputNodeCount, int outputNodeCount) {
        this.inputNodeCount = inputNodeCount;
        this.outputNodeCount = outputNodeCount;
        weights = new double[inputNodeCount][outputNodeCount];
        biases = new double[outputNodeCount];
    }

    public Layer(int inputNodeCount, int outputNodeCount, String chrom) {
        this.inputNodeCount = inputNodeCount;
        this.outputNodeCount = outputNodeCount;
        weights = new double[inputNodeCount][outputNodeCount];
        biases = new double[outputNodeCount];

        generateParameters(chrom);
    }

    public void generateParameters(String genes) {
        char[] gene = genes.toCharArray();
        int k = 0;
        for (int i = 0; i < biases.length; i++) {
            biases[i] = getValueFromGene(gene[k]);
            k++;
        }
        for (int j = 0; j < outputNodeCount; j++) {
            for (int i = 0; i < inputNodeCount; i++) {
                weights[i][j] = getValueFromGene(gene[k]);
                k++;
            }
        }
    }

    public double[] calculateOutputs(double[] inputs) {
        double[] activations = new double[outputNodeCount];

        for (int outputNode = 0; outputNode < outputNodeCount; outputNode++) {
            double weightedInput = biases[outputNode];
            for (int inputNode = 0; inputNode < inputNodeCount; inputNode++) {
                weightedInput += inputs[inputNode] * weights[inputNode][outputNode];
            }
            activations[outputNode] = activationFunction(weightedInput);
        }

        return activations;
    }

    private double activationFunction(double weightedInput) {
        return 1/(1 + java.lang.Math.exp(-weightedInput));
    }
    private double stepActivation(double weightedInput) { return (weightedInput > 0)? 1 : 0;}

    private double getValueFromGene(char gene) {
        int decValue = (int)gene - 97;
        double value = (double)decValue / 12.5; //value between 0 and 2
        return value-1; //return bewteen -1 and 1
    }

    public void paramsToOne() {
        for (int i = 0; i < outputNodeCount; i++) {
            biases[i] = 1;
        }
        for (int i = 0; i < inputNodeCount; i++) {
            for (int j = 0; j < outputNodeCount; j++) {
                weights[i][j] = 1;
            }
        }
    }

    public void showLayer() {
        String biases = "Biases :";
        for (int i = 0; i < outputNodeCount; i++) biases += " " + this.biases[i];
        System.out.println(biases);
        System.out.println("Weights :");
        for (int i = 0; i < inputNodeCount; i++) {
            String weights = "";
            for (int j = 0; j < outputNodeCount; j++) {
                weights += " " + this.weights[i][j];
            }
            System.out.println(weights);
        }
    }

    public int getInputNodeCount() { return inputNodeCount; }
    public int getOutputNodeCount() { return outputNodeCount; }
}
