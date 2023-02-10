public class NeuralNetwork {
    private Layer[] layers;
    private Chromosome chromosome;

    public Layer getFirstLayer() {
        return layers[0];
    }

    public NeuralNetwork(int[] layerSize) {
        layers = new Layer[layerSize.length -1];
        for (int i = 0; i < layers.length; i++) {
            layers[i] = new Layer(layerSize[i], layerSize[i+1]);
        }
    }

    public NeuralNetwork(String chrom) {
        this.chromosome = new Chromosome(chrom);
        int networkSize = chromosome.getNetworkSize();
        layers = new Layer[networkSize-1];

        layers[0] = new Layer(chromosome.getNodeCount(1), chromosome.getNodeCount(2));
        for (int i = 1; i < layers.length; i++) { //create new empty layers
            layers[i] = new Layer(layers[i-1].getOutputNodeCount(), chromosome.getNodeCount(i+2));
        }
        int i = 1;
        for (int j = 0; j < layers.length; j++) { //set layers weights and biases from chromosome
            int length = layers[j].getOutputNodeCount()*(1 + layers[j].getInputNodeCount());
            int firstIndex = networkSize+i;
            int secondindex = networkSize+i+length;
            String layerGenes = chrom.substring(firstIndex, secondindex);
            layers[j].generateParameters(layerGenes);
            i += length;
        }
    }

    private double[] calculateOutputs(double[] inputs) {
        for (Layer layer:layers) {
            inputs = layer.calculateOutputs(inputs);
        }
        return inputs;
    }

    public int findTarget(double[] inputs) {
        double[] outputs = calculateOutputs(inputs);
        return Utility.IndexOfMax(outputs);
    }

    public void showNetwork() {
        for (int i = 0; i < layers.length; i++) {
            System.out.println("Layer " + i + " :");
            layers[i].showLayer();
        }
    }

    public String getChromosome() { return this.chromosome.stringChromosome; }
    public void setChromosome(String chromosome) { this.chromosome.stringChromosome = chromosome; }
}
