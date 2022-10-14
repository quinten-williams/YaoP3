/*
   Authors: Ashley Pell, Quinten Williams, and Juan Guzman
   Date: 10/02/2022
   
   Write a program (in C++ or Java) that can input from an input file 
   (named "FDs") that contains a set of functional dependencies (F) and 
   a set of all attributes (R) for a relation, and output all possible superkeys 
   for the relation to an output file (named "superkeys").
   
*/
import java.util.*; //Scanner Collection Comp Iter and Set
import java.io.*; //IOException FileReader FileWriter

public class project3_PellWilliamsGuzman
{
   static void subsets(char[] arr)
   {
      int count = arr.length;
      
      for(int i = 0; i < (1 << count); i++)
      {
         for(int j = 0; j < count; j++) 
         {
            if((i & (1 << j)) > 0) 
            {
               System.out.print(arr[j] + "");  
            }
         }
         
         System.out.print("\n");
      }
   
   }
  
   public static void main(String[] args) throws FileNotFoundException
   {
      File file = new File("FDs.txt");
      //PrintWriter output = new PrinterWriter("superkeys.txt");
      
      Scanner scan = new Scanner(file);
      
      String infile = scan.nextLine();
      //System.out.println(infile); //checked if the line is within infile
      String fdOne = scan.nextLine();
      //System.out.println(infileTwo);
      String fdTwo = scan.nextLine();
      String fdThree = scan.nextLine();
      
      infile = infile.replaceAll("\\s+","");
      char[] set;
      set = infile.toCharArray();
      subsets(set);
      /*
         Algorithm:
         
         closure = {k}; /*  K is one of the possible superkeys, which 
         are output of hw1. You must run each entry of the output of hw1 
         against this algorithm to determine which entry is a superkey
         
         do {
            for(each FD U -> V in FD) do {
               
                     if(U is a subset of closure) then
                        add V to closure;
            } //  end of the for-loop
      */
      
      //input.close();
      //output.close();
   }
}
