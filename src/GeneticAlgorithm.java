import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GeneticAlgorithm {

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
    //AS as default Value m and n are set to 2 and 5
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
    //This case handles if the user does not enter any arguments; It will then prompt the user to input the m and the n values
    else if (args.length == 0) {
      System.out.println("YOU DID NOT ENTER ANY ARGUMENTS INTO THE COMMAND LINE: REVERTING TO CONSOLE INPUT.");
      System.err.println("Number of Arguments has been 0. Please input again.");
      System.out.println("Enter M value");
      m = scan.nextInt();
      System.out.println("Enter N value");
      n = scan.nextInt();
    }
    else { //This case handles the inputs handled as a command line argument. Which for example may look like java GeneticAlgorithm
      m = Integer.parseInt(args[0]); // rows
      n = Integer.parseInt(args[1]); // cols
    }
    try {//THIS TRY CATCH CHECKS AND SEES IF THE OUTPUT.TXT FILE IS WITHIN THE SCOPE OR EXISTS.

      try {
        FileWriter costWriter = new FileWriter("cost.txt");
        System.out.println("Cost file has been found");




      FileWriter myWriter = new FileWriter("output.txt");
      int populationsize = m * n;
      int cost = 0;
      System.out.println("\nThe population of (" + m + ") Rows (" + n + ") Columns\n" + populationsize + " individuals Has been generated");

      //This is the creation of the two objects which will create the genetic variation.
      //The two objects calls the constructor which takes in the two parameters the m and n values which are the rows and columns
      System.out.println("\nParent 1");
      GeneticHandler parent1 = new GeneticHandler(m, n);
      System.out.println("\nParent 2");
      GeneticHandler parent2 = new GeneticHandler(m, n);

      //once the two parent objects are created then the arrays are then assigned to different arrays which will represent the two root parents of the algorithim
      int population[][] = parent1.getPopulation();

      int population2[][] = parent2.getPopulation();

      //The fitness functions are called on the two arrays
      //The two arrays which represent the two parents are then calculuating the individual fitness of each population
      //The fitness if generated by calculating the cost of side to side of the array and then top to bottom calcululating the absoulute value
      int cost1 = fitness(population, m, n);
      int cost2 = fitness(population2, m, n);
      int childrencost = 0;

      //The total cost calulates the totalcost of both arrays.
      int totalcost = CostComparison(cost1, cost2);
      System.out.println("The cost of the first array " + cost1);
      System.out.println("The cost of the second array " + cost2);
      costWriter.write("Costs for each step\n");
      costWriter.write(String.valueOf(cost1) + "\n");
      costWriter.write(String.valueOf(cost2) + "\n");
      System.out.println("The total cost between the first array and the second array is going to be " + totalcost + "\n");
      //Create a child array which will serve as a result of reproduction between the two parent arrays.
      int child[][] = new int[m][n];

      //Crossover works in this instance due to half of the genetic sequence from parent one
      //and the other half of the sequence is from parent2
      System.out.println("CROSSOVER ARRAY BELOW");
      child = parent2.crossOver(parent1);
      population = parent1.getPopulation();
      //Print out the resulting child from the Crossover of parent2 and parent1
      for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++ ){
          System.out.print(child[i][j] + "\t");
        }
        System.out.println();
      }

      //Calculate the cost of the crossover for the resulting child.
      int crossovercost = fitness(child, m, n);
      System.out.println("The cost of the Crossover is " + crossovercost);
      costWriter.write(String.valueOf(crossovercost) + "\n");
      //Create an instance of a child which will serve as a basis for future mutations called the mutant child
      int mutantchild[][] = new int[m][n];
      System.out.println("\nMutation 1");
      mutantchild = parent1.occurMutation(child);
      for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++ ){
          System.out.print(mutantchild[i][j] + "\t");
        }
        System.out.println();
      }
      childrencost = fitness(mutantchild, m, n);
      System.out.println("The cost of the first Mutation is " + childrencost);
      costWriter.write(String.valueOf(childrencost) + "\n");

      //Premuatedchildcost means it calculates the cost which are the non mutated children.
      int premutatedchildcost = childrencost + crossovercost;
      costWriter.write(String.valueOf(premutatedchildcost) + "\n");

      cost2 += premutatedchildcost;
      //MutationRate references to how many mutaions will occur I am not too sure how many to do so I set it to 6 as a default.
      int mutationrate = 6;


      //These rounds of mutations can work for any specified amount. In this instance it is set to 7 rounds of mutations.
      for(int y = 2; y < 2; y++) {
        /*
        How this loop Works
          Child cost
          mutation
          print which version
          print the mutated child
          fitness function on the child
          print the cost for this child
          print the running cost of all child including this one
          mutate the New Child
          repeat
         */
        mutantchild = parent1.occurMutation(mutantchild);
        System.out.println("\nMutation " + y);
        for(int i = 0; i < m; i++){
          for(int j = 0; j < n; j++ ){
            System.out.print(mutantchild[i][j] + "\t");
          }
          System.out.println();
        }
        childrencost = fitness(mutantchild, m, n);
        System.out.println("The cost of Mutation " + y + " is " + childrencost);
        costWriter.write(String.valueOf(childrencost) + "\n");
        cost2 += childrencost;
      }
      totalcost = CostComparison(cost1, cost2);
      System.out.println("\nThe total cost is " + totalcost);
      myWriter.write(String.valueOf(totalcost));
      costWriter.write("Total Cost\n");
      costWriter.write(String.valueOf(totalcost) + "\n");
      costWriter.close();
      myWriter.close();

      } catch (FileNotFoundException error) {
        System.out.println("The cost file could not be found.");
      }

    } catch (IOException e) {
      System.out.println("The output file could not be found.");
    }
    System.out.println();


    }
  }