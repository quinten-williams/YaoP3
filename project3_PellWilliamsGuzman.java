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
      Scanner scan = new Scanner(file);
      int count = 0;
      
      
      List<List<Character>> subset = new ArrayList<>();            
      String relation = scan.nextLine();
      
      ArrayList<String> U = new ArrayList<String>();
      ArrayList<String> V = new ArrayList<String>();
      String temp ="";   
      
      while(scan.hasNextLine())
      {
         
         temp = scan.nextLine();
         String[] strArray = temp.split("->",-1);
         U.add(strArray[0]);
         V.add(strArray[1]);
         count++;
         
      }
      //System.out.println(count);
      System.out.println(U);
      System.out.println(V);
      
      relation = relation.replaceAll("\\s+","");
      
      char[] set;
      set = relation.toCharArray();
   
      ArrayList<Character> input = new ArrayList<>();
      
      for(int x = 0; x < set.length; x++) 
      {
         input.add(set[x]);
      }
      
      findSubsets(subset, input, new ArrayList<>(), 0);
      PrintWriter output = new PrintWriter("closures.txt");
      
      for(int i = 0; i < subset.size(); i++){
         for(int j = 0; j < subset.get(i).size(); j++){
            
            output.print(subset.get(i).get(j));
         }
         if(i != 0) 
         {
            output.print("\n");
         }
      }
      output.close();
      
      /*
         Algorithm:
         
         closure = {k}; /*  K is one of the possible superkeys, which 
         are output of hw1. You must run each entry of the output of hw1 
         against this algorithm to determine which entry is a superkey
      */
         File keys = new File("closures.txt");
         Scanner scanTwo = new Scanner(keys);
         String tempTwo = "";
         boolean verify = true;
           
         while(scanTwo.hasNextLine())
         {   
            tempTwo = scanTwo.nextLine();
            //System.out.println(tempTwo);
            do
            {
               for(int i = 0; i < count; i++)
               {
                  
               }
            
            }
         }
     /*    
         while(    
         do {
            for(each FD U -> V in FD) do {
               
                     if(U is a subset of closure) then
                        add V to closure;
            } //  end of the for-loop
      
      */
      scan.close();
      scanTwo.close();
   }
}
