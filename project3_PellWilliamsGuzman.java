/*
   Authors: Ashley Pell, Quinten Williams, and Juan Guzman
   Date: 10/02/2022
   
   Write a program (in C++ or Java) that can input from an input file 
   (named "FDs") that contains a set of functional dependencies (F) and 
   a set of all attributes (R) for a relation, and output all possible superkeys 
   for the relation to an output file (named "superkeys").
   
*/
import java.util.*; //Scanner Collection, Arraylist, 
import java.io.*; //IOException Printfile FileWriter

public class project3_PellWilliamsGuzmanTest
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
      
      for(int i = 0; i < subset.size(); i++)
      {
      
         for(int j = 0; j < subset.get(i).size(); j++)
         {
            
            output.print(subset.get(i).get(j));
         }
         
         if(i != 0) 
         {
            output.print("\n");
         }
      }
      
      output.close();
      
      File keys = new File("closures.txt");
      Scanner scanTwo = new Scanner(keys);
      String tempTwo = "";
      boolean verify = false;
      PrintWriter super_keys = new PrintWriter("superkeys.txt");
           
      do
      {   
         tempTwo = scanTwo.nextLine();
         tempChange = tempTwo;
         for(int i = 0; i < count; i++)
         {  
            do
            {
               if()
               {
               
               }
            }
         }
         /*
         if(tempTwo.contains)
         {
            super_keys.println(tempTwo);
         }
         */
      }while(scanTwo.hasNextLine())      
      scan.close();
      scanTwo.close();
      super_keys.close();
   }
}
