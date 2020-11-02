import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GeneticAlgorithim {
  public static void PrintArray(int array[][], int row, int col)
  {
    for(int i = 0; i < row; i++){
      for(int j = 0; j < col; j++ ){
        System.out.print(array[i][j] + "\t");
      }
      System.out.println();
    }
  }
  public static int CostComparison(int cost1, int cost2){
    int totalcost = 0;
    totalcost += cost1 + cost2;
    return totalcost;
  }

  public static int fitness(int array[][] , int row, int col) {
    int cost = 0;
    for (int i = 0; i < row; ++i) { // rows
      for (int j = 0; j < col - 1; ++j) { // columns
        int tempcost = cost;
        cost = cost + absolutevalue(array[i][j + 1] - array[i][j]);
        //System.out.println("The Costs: " + "\t" + tempcost + " + (" + array[i][j + 1] + " - " + array[i][j] + ") = " + cost);
      }
    }
    for (int i = 0; i < col; ++i) { // columns
      for (int j = 0; j < row - 1; ++j) { // rows
        int tempcost = cost;
        cost = cost + absolutevalue(array[j + 1][i] - array[j][i]);
        //System.out.println("The Costs: " + "\t" + tempcost + " + (" + array[j + 1][i] + " - " + array[j][i]+ ") = " + cost);
      }
    }
    return cost;
  }

  public static int absolutevalue(int a) {
    if (a > 0) {
      return a;
    }
    return -1 * a;
  }
  public static void main(String[] args) {

    int m = 2;
    int n = 5;
    Random random = new Random();
    //These codes handle the input from the user whether it be Command Line or From the console Input.
    //The code can handle the cases of no arguments in command line or cases where the command line may have more than the required 2
    //The reason why the code must require 2 arguments is that there must be an m or n value.
    Scanner scan = new Scanner(System.in);
    if (args.length != 2) {
      System.err.println("Number of Arguments has been exceeded. Please input again.");
      System.out.println("Enter M value");
      m = scan.nextInt();
      System.out.println("Enter N value");
      n = scan.nextInt();
    }
    else if (args.length == 0) {
      System.err.println("Number of Arguments has been 0. Please input again.");
      System.out.println("Enter M value");
      m = scan.nextInt();
      System.out.println("Enter N value");
      n = scan.nextInt();
    } else {
      m = Integer.parseInt(args[0]); // rows
      n = Integer.parseInt(args[1]); // cols
    }

    try {
      //FileWriter myWriter = new FileWriter("src\\input.txt");
      FileWriter myWriter = new FileWriter("output.txt");
      int populationsize = m * n;
      int cost = 0;
      System.out.println("\nThe population of (" + m + ") Rows (" + n + ") Columns\n" + populationsize + " individuals Has been generated");
      GeneticHandler parent1 = new GeneticHandler(m, n);
      GeneticHandler parent2 = new GeneticHandler(m, n);
      int population[][] = parent1.getPopulation();
      int population2[][] = parent2.getPopulation();

      // This is the intialization of the first array
      // This array will be populated in order.
    /*int k = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        k++;
        population[i][j] = k;
        System.out.print(k + "\t");
      }
      System.out.println("");
    }
    System.out.println("\n");

    //This is the initialization of a second array which will represent the second population in the set.
    //This second Array will be populated with random numbers within the constraints of the populationsize.
      int x = 0;
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          x++;
          x = random.nextInt(populationsize);// keep the random numbers within the size of the population
          population2[i][j] = x;
          System.out.print(x + "\t");
        }
        System.out.println();
      }*/
      // The output of the costs based off off the funtion which determines the Costs

      int cost1 = fitness(population, m, n);
      int cost2 = fitness(population2, m, n);
      int childrencost = 0;

      // total cost determines the total costs of both arrays.
      int totalcost = CostComparison(cost1, cost2);

      System.out.println("The cost of the first array " + cost1);
      System.out.println("The cost of the second array " + cost2);
      System.out.println("The total cost between the first array and the second array is going to be " + totalcost);
      int child[][] = new int[m][n];

      System.out.println("CROSSOVER ARRAY BELOW");
      child = parent2.crossOver(parent1);
      population = parent1.getPopulation();
      for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++ ){
          System.out.print(child[i][j] + "\t");
        }
        System.out.println();
      }
      int crossovercost = fitness(child, m, n);
      System.out.println("The cost of the Crossover is " + crossovercost);
      int mutantchild[][] = new int[m][n];
      mutantchild = parent1.occurMutation(child);

      System.out.println("First mutation\n");
      for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++ ){
          System.out.print(mutantchild[i][j] + "\t");
        }
        System.out.println();
      }
      childrencost = fitness(mutantchild, m, n);
      System.out.println("The cost of the first Mutation is " + childrencost);

      /*
      mutantchild = parent1.occurMutation(mutantchild);
      System.out.println("Second mutation\n");
      for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++ ){
          System.out.print(mutantchild[i][j] + "\t");
        }
        System.out.println();
      }
      childrencost = fitness(mutantchild, m, n);
      System.out.println("The cost of the Second Mutation is " + childrencost);*/
      int premutatedchildcost = childrencost + crossovercost;
      cost2 += premutatedchildcost;

      for(int y = 1; y < 7; y++){
        /*
        child cost
        mutation
        print which version
        print the mutated child
        fitness function on the child
        print the cost for this child
        print the running cost of all child including this one
        mutate the newchild
        repeat
         */
        mutantchild = parent1.occurMutation(mutantchild);
        System.out.println(" Mutation " + y + "\n");
        for(int i = 0; i < m; i++){
          for(int j = 0; j < n; j++ ){
            System.out.print(mutantchild[i][j] + "\t");
          }
          System.out.println();
        }
        childrencost = fitness(mutantchild, m, n);
        System.out.println("The cost of Mutation " + y + " is " + childrencost);
        cost2 += childrencost;
      }
      totalcost = CostComparison(cost1, cost2);
      System.out.println("The total cost is " + totalcost);
      myWriter.close();
    } catch (IOException e) {
      System.out.println("The input file could not be found.");
    }
    System.out.println();




    // m and n has been specified from this point on

    // secondary method which may be to make the the algorithim be able to run


      //total cost





      /*try {
        //File file = new File("src\\input.txt");
        File file = new File("input.txt");
        Scanner reader = new Scanner(file);
        System.out.println("\nReading from the Input.txt file\n------------------------\n");
        for (int i = 0; i < m; i++) {
          for (int j = 0; j < n; j++) {
            population[i][j] = Integer.parseInt(reader.next());
            System.out.print(population[i][j] + "\t");
          }
          System.out.println();
        }
      } catch (Exception E) {

        System.out.println(E);
        System.out.println("There has been an error in the Input.txt file.");
      }*/

      System.out.println("------------------------\nThe Cost paths : \n");

    /*(System.out.println("Algorithim 1 starting");
    for (int i = 0; i < m; ++i) { // rows
      for (int j = 0; j < n - 1; ++j) { // columns
        int tempcost = cost;
        cost = cost + abs(population[i][j + 1] - population[i][j]);
        System.out.println("The Costs: " + "\t" + tempcost + " + (" + population[i][j + 1] + " - " + population[i][j] + ") = " + cost);
      }
    }

    System.out.println("Algorithim 2 starting");
    for (int i = 0; i < n; ++i) { // columns
      for (int j = 0; j < m - 1; ++j) { // rows
        int tempcost = cost;
        cost = cost + abs(population[j + 1][i] - population[j][i]);
        System.out.println("The Costs: " + "\t" + tempcost + " + (" + population[j + 1][i] + " - " + population[j][i]+ ") = " + cost);
      }
    }*/


/*
      try {
        //FileWriter myWriter3 = new FileWriter("src\\cost.txt");
        FileWriter myWriter3 = new FileWriter("cost.txt");
        System.out.println("SEARCHING FROM SIDE TO SIDE");
        myWriter3.write("Searching from side to side");
        for (int i = 0; i < m; ++i) { // rows
          for (int j = 0; j < n - 1; ++j) { // columns
            int tempcost = cost;
            cost = cost + absolutevalue(population[i][j + 1] - population[i][j]);

            System.out.println("The Costs: " + "\t" + tempcost + " + (" + population[i][j + 1] + " - " + population[i][j] + ") = " + cost);
            myWriter3.write("The Costs: " + "\t" + tempcost + " + (" + population[i][j + 1] + " - " + population[i][j] + ") = " + cost + "\n");
          }
          myWriter3.write("\n");
        }

        myWriter3.write("\nSearching Top to Bottom\n");
        System.out.println("\nSEARCHING FROM TOP TO BOTTOM");
        for (int i = 0; i < n; ++i) { // columns
          for (int j = 0; j < m - 1; ++j) { // rows
            int tempcost = cost;
            cost = cost + absolutevalue(population[j + 1][i] - population[j][i]);
            myWriter3.write("The Costs: " + "\t" + tempcost + " + (" + population[j + 1][i] + " - " + population[j][i] + ") = " + cost + "\n");
            System.out.println("The Costs: " + "\t" + tempcost + " + (" + population[j + 1][i] + " - " + population[j][i] + ") = " + cost);
          }
          myWriter3.write("\n");
        }
        myWriter3.close();
      } catch (IOException e) {
        System.out.println("An error of the cost.txt file has occured.");
      }
      try {
        // FileWriter myWriter2 = new FileWriter("src\\output.txt");
        FileWriter myWriter2 = new FileWriter("output.txt");
        System.out.println("-------------------------");
        System.out.println("THE COST WILL BE " + cost);
        myWriter2.write(String.valueOf(cost));
        myWriter2.close();
      } catch (IOException e) {
        System.out.println("An error of the output.txt file has occured.");
      }*/

    }
  }