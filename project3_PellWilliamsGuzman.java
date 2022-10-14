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

public class project3_PellWilliamsGuzmanTestTwo
{
   
   static void findSubsets(List<List<Character>> subset, ArrayList<Character> nums, ArrayList<Character> output, int index)
   {
      if (index == nums.size())
      {
         subset.add(output);
         return;
      }
      
        // Not Including Value which is at Index
      findSubsets(subset, nums, new ArrayList<>(output), index + 1);
   
        // Including Value which is at Index
      output.add(nums.get(index));
      findSubsets(subset, nums, new ArrayList<>(output), index + 1);
   }
  
   public static void main(String[] args) throws FileNotFoundException
   {
      File file = new File("FDs.txt");
      //PrintWriter output = new PrinterWriter("superkeys.txt");
      
      Scanner scan = new Scanner(file);
     //Main List for storing all subsets
     
      List<List<Character>> subset = new ArrayList<>();      
      String infile = scan.nextLine();
      
      //System.out.println(infile); //checked if the line is within infile
      String fdOne = scan.nextLine();
      //System.out.println(infileTwo);
      String fdTwo = scan.nextLine();
      String fdThree = scan.nextLine();
      String infileTwo = infile;
      infile = infile.replaceAll("\\s+","");
      char[] set;
      set = infile.toCharArray();
      subsets(set);
      ArrayList<Character> input = new ArrayList<>();
      for(int x = 0; x < set.length; x++) 
      {
         input.add(set[x]);
      }
      
      findSubsets(subset, input, new ArrayList<>(), 0);
      
      for(int i = 0; i < subset.size(); i++){
         for(int j = 0; j < subset.get(i).size(); j++){
            System.out.print(subset.get(i).get(j) + " ");
         }
         System.out.println();
      }
      
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
