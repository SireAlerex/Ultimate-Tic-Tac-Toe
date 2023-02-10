public class TicTacToe {
    public static void main(String[] args) {
        double[] inputs = {5.0, 5.0};
        String testChrom = "cbcbzzzzzzzzzvvvvvvvv";
        NeuralNetwork testNN = new NeuralNetwork(testChrom);

        int testTarget = testNN.findTarget(inputs);
        System.out.println(testTarget);
        testNN.showNetwork();
    }
}
