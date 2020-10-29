package genetic;

import java.util.Objects;
import java.util.Random;

public class GeneticHandler {
    private int m;
    private int n;

    public int population[][];
    public int population2[][];
    public int columnSequence[];

    public GeneticHandler(int mpassedin, int npassedin){
        Random random = new Random();
        setM(mpassedin);
        setN(npassedin);
        population = new int[m][n];
        columnSequence = new int[n];
        //population2 = new int[m][n];

        // This is the intialization of the first array
        // This array will be populated in order.
       /* int k = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                k++;
                population[i][j] = k;
                System.out.print(k + "\t");
            }
            System.out.println("");
        }
        System.out.println("\n");*/

        //This is the initialization of a second array which will represent the second population in the set.
        //This second Array will be populated with random numbers within the constraints of the populationsize.
        int x = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                x++;
                x = random.nextInt(populationsize());// keep the random numbers within the size of the population
                population[i][j] = x;
                System.out.print(x + "\t");
            }
            System.out.println();
        }
    }

    public void crossOver(GeneticHandler Population){
        int temporaryvalue = 0;
        int halfrow = getM()/2;
        int array2[][] = Population.getPopulation();
        for(int i = halfrow; i < getM(); i++){
            for(int j = getN(); j <getN(); j++){
                temporaryvalue = Population.getPopulation()[i][j];
                Population.getPopulation()[i][j] = this.getPopulation()[i][j];
                this.getPopulation()[i][j] = temporaryvalue;
               // array2[i][j] = array[i][j];
                //array[i][j] = temporaryvalue;
            }
        }
    }

    public void OutputPopulation(int array[][]){
        for(int i = 0; i < getM(); i++){
            for(int j = 0; j < getN(); j++ ){
                System.out.println(getPopulation()[i][j] + "\t");
            }
            System.out.println("\n");
        }
    }


    public int[][] getPopulation() {
        return population;
    }

    public int[][] getPopulation2() {
        return population2;
    }

    public int populationsize(){
        return m * n;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
}
