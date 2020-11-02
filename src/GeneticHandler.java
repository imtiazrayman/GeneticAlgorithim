import java.util.ArrayList;
import java.util.Random;

public class GeneticHandler {
    private int m;
    private int n;
    public int population[][];
    public int population2[][];
    public int columnSequence[];
    public int child[][];
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
            columnSequence[i]=-1;
            System.out.println();
        }
    }
    public int[][] crossOver(GeneticHandler Population){
        int temporaryvalue = 0;
        int halfrow = getM()/2;
        int parent1[][] = Population.getPopulation();
        int parent2[][] = this.population;
        child = new int[m][n];
        for(int i = 0; i < m/2; i++){
            for (int j = 0; j < n; j++){
                child[i][j] = parent1[i][j];
            }
        }
        for(int i = m/2; i < m; i++){
            for (int j = 0; j < n; j++){
                child[i][j] = parent2[i][j];
            }
        }
        return child;
    }
    public String toString()
    {
        String string = "";
        for(int i=0; i<getM(); i++)
        {
            for(int j=0; j<getN(); j++)
            {
                string += population[i][j] + "  ";
            }
            string += "\n";
        }
        return string;
    }
    public void OutputPopulation(int array[][]){
        for(int i = 0; i < getM(); i++){
            for(int j = 0; j < getN(); j++ ){
                System.out.println(getPopulation()[i][j] + "\t");
            }
            System.out.println("\n");
        }
    }
    public int[][] occurMutation(int offspring[][])
    {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Random random = new Random();
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                while(list.contains(offspring[i][j]))
                {
                    offspring[i][j]=random.nextInt(populationsize())+1;
                }
                list.add(offspring[i][j]);
            }
        }
        int index = 0;
        for(int a=0; a<m; a++)
        {
            for(int b=0; b<n; b++)
            {
                offspring[a][b] = list.get(index);
                index++;
            }
        }
        int temp;
        int rowNo1 = random.nextInt(m);
        int columnNo1 = random.nextInt(n);

        int rowNo2 = random.nextInt(m);
        int columnNo2 = random.nextInt(n);
        temp =  offspring[rowNo1][columnNo1];
        offspring[rowNo1][columnNo1] = offspring[rowNo2][columnNo2];
        offspring[rowNo2][columnNo2] =temp;

        return offspring;
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
