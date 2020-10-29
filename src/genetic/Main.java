package genetic;

import java.util.*;
import java.io.*;

public class Main{

  public static int absolutevalue(int a)
  {
    if (a > 0) {
      return a;
    }
    return -1 * a;
  }

  public static void populate(int m, int n){


  }

  public static void fitness(){

  }



  public static void main(String[] args) {
    // int N,M;
    // N = 6;
    // M = 4;
    //N = Integer.parseInt(args[0]);
    //M = Integer.parseInt(args[1]);
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter M value");
    int m = scan.nextInt();
    System.out.println("Enter N value");
    int n = scan.nextInt();

    int populationsize = m * n;
    int cost = 0;
    System.out.println("\nThe population of (" + m + ") Rows ("+ n + ") Columns\n" + populationsize + " individuals Has been generated");
   
    int population[][] = new int[m][n];
    try {
      FileWriter myWriter = new FileWriter("src\\input.txt");
      System.out.println("Generating Population size: " + populationsize + "\t");
      System.out.println("\nWriting to the input.txt file\n-----------------------------\n");

      int k = 0;
      for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
          k++;
          myWriter.write(Integer.toString(k) + " ");
          System.out.print(k + "\t");
        }
        System.out.println();
        myWriter.write("\n");
      }
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
    }
    System.out.println();
    try {
      File file = new File("src\\input.txt");
      Scanner reader = new Scanner(file);
      System.out.println("\nReading from the Input.txt file\n------------------------\n");
      for ( int i = 0; i < m; i++) {
        for ( int j = 0; j < n; j++) {
          population[i][j] = Integer.parseInt(reader.next());
          System.out.print(population[i][j] + "\t");
        }
        System.out.println();
      }
    } catch (Exception E) {

      System.out.println(E);
    }
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


    try {
      FileWriter myWriter3 = new FileWriter("src\\cost.txt");
      System.out.println("SEARCHING FROM SIDE TO SIDE");
      myWriter3.write("Searching from side to side");
      for (int i = 0; i < m; ++i) { // rows
        for (int j = 0; j < n - 1; ++j) { // columns
          int tempcost = cost;
          cost = cost + absolutevalue(population[i][j + 1] - population[i][j]);

          System.out.println("The Costs: " + "\t" + tempcost + " + (" + population[i][j + 1] + " - " + population[i][j] + ") = " + cost );
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
          myWriter3.write("The Costs: " + "\t" + tempcost + " + (" + population[j + 1][i] + " - " + population[j][i]+ ") = " + cost + "\n");
          System.out.println("The Costs: " + "\t" + tempcost + " + (" + population[j + 1][i] + " - " + population[j][i]+ ") = " + cost );
        }
        myWriter3.write("\n");
      }
      myWriter3.close();
    } catch (IOException e) {
        System.out.println("An error occurred.");
    }


    try {
      FileWriter myWriter2 = new FileWriter("src\\output.txt");
      System.out.println("-------------------------");
      System.out.println("THE COST WILL BE " + cost);
      myWriter2.write(String.valueOf(cost));
      myWriter2.close();
    }
    catch (IOException e)
    {
      System.out.println("An error occurred.");
    }



  }
}