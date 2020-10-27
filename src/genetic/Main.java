package genetic;

import java.util.*;
import java.io.*;

public class Main{

  static int abs(int a) {
    if (a > 0) {
      return a;
    }

    return -1 * a;
  }

  public static void populate(int m, int n){
      //todo
      // this function must be implemented


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
    System.out.println("The population of " + m + " Rows "+ n + " Columns\n" + populationsize + " individuals Has been generated");
   
    int population[][] = new int[m][n];
    try {
      FileWriter myWriter = new FileWriter("src\\input.txt");
      System.out.println("Generating Population size: " + populationsize );
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
    for (int i = 0; i < m; ++i) {
      for (int j = 0; j < n - 1; ++j) {
        cost = cost + abs(population[i][j + 1] - population[i][j]);
      }
    }
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < m - 1; ++j) {
        cost = cost + abs(population[j + 1][i] - population[j][i]);
      }
    }
    try {
      FileWriter myWriter2 = new FileWriter("src\\output.txt");
      System.out.println("THE COST WILL BE " + cost);
      myWriter2.write(String.valueOf(cost));
      myWriter2.close();

    } catch (IOException e) {
      System.out.println("An error occurred.");
    }

  }
}