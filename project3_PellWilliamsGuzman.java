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
      scan.close();
      
      File keys = new File("closures.txt");
      Scanner scanTwo = new Scanner(keys);
      String tempChange = "";
      boolean verify = false;
      PrintWriter super_keys = new PrintWriter("superkeys.txt");
           
      while(scanTwo.hasNextLine())
      {   
         String tempTwo = scanTwo.nextLine();
         Set<String> closures = new HashSet<>();
         
         for (char c: tempTwo.toCharArray())
         {
            closures.add("" + c);
            //System.out.println(closures); CHECK
         }
         
         //System.out.println(closures);
         Set<String> closures_Two = new HashSet<>(closures);
         //System.out.println(closures_Two); CHECK
         do
         {
            //System.out.println(closures);
            for(int i = 0; i < count; i++)
            {  
               //System.out.println(closures);
               String leftFD = U.get(i);
               //System.out.println(U.get(i)); CHECK
               
               String rightFD = V.get(i);
               //System.out.println(V.get(i));  CHECK
               
               boolean verifySub = true;
               //System.out.println(closures); CHECK
               for(char c: leftFD.toCharArray())
               {
                  //System.out.println(closures); CHECK
                  if(!closures.contains("" + c));
                  {
                     //System.out.println(closures);CHECK
                     verifySub = false;
                  }
                  System.out.println(closures);
               }
               
               if(verifySub)
               {
                  //System.out.println(closures);
                  //closures_Two = new HashSet<>(closures);
                  //System.out.print(closures_Two);
                  for(char c: rightFD.toCharArray())
                  {
                     //System.out.println(closures);
                     closures.add("" + c);
                     //System.out.println(closures);
                  }
               }
            }  
           //System.out.println(tempTwo); CHECK
         
         } while(!closures.equals(closures_Two));
         
         boolean printClosures = true;
         for(char c: relation.toCharArray())
         {
            if(!closures.contains("" + c))
            {
               printClosures = false;
            }
         }
         
         if(printClosures)
         {
            //System.out.println(tempTwo);
            super_keys.println(tempTwo);
         } 
      } 
           
      
      scanTwo.close();
      super_keys.close();
   }
}
