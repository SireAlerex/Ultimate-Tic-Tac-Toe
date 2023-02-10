public class Chromosome {
    String stringChromosome;

    public Chromosome(int length) {
        String chrom = "";
        for (int i = 0; i < length; i++) {
            chrom += getLetter();
        }
        this.stringChromosome = chrom;
    }

    public Chromosome(String chromosome) {
        this.stringChromosome = chromosome;
    }

    public int getNetworkSize() { return getIntFromGene(0); }
    public int getNodeCount(int index) { return getIntFromGene(index); }

    private int getIntFromGene(int index) {
        int decValue = (int)this.stringChromosome.charAt(index) - 96;
        return decValue; //return bewteen 1 and 26
    }

    public double getDoubleFromGene(int index) {
        int decValue = (int)this.stringChromosome.charAt(index) - 97;
        double value = (double)decValue / 12.5; //value between 0 and 2
        return value-1; //return bewteen -1 and 1
    }

    private static char getLetter() {
        String chars = "abcdefghijklmnopqrstuvwxyz";
        java.util.Random rnd = new java.util.Random();
        char c = chars.charAt(rnd.nextInt(chars.length()));
        return c;
    }
}
