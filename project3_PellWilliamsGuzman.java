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
   /*
     A recursive method that produces all of the subsets of a given set.
     The subset parameter is a list containing mutiple character lists of subsets.
     The nums parameter is a list containing the original set whose subsets we wish to produce.
     The output parameter is each individual subset produced, that will be added to the subset list.
     We use the index parameter to specify which element of the nums list needs to be added to the output list.
     This method doesn't return anything, but instead fills an empty list with all of the subsets of the specified set.
   */
   static void findSubsets(List<List<Character>> subset, ArrayList<Character> nums, ArrayList<Character> output, int index)
   {
      
      if (index == nums.size())
      {
         subset.add(output);
         return;
      }
      
      //Not including value which is at index
      findSubsets(subset, nums, new ArrayList<>(output), index + 1);
   
      //including value which is at index
      output.add(nums.get(index));
      findSubsets(subset, nums, new ArrayList<>(output), index + 1);
   }
  
   public static void main(String[] args) throws FileNotFoundException
   {
      File file = new File("FDs.txt");
      Scanner scan = new Scanner(file);
      int count = 0;
      //A list that will be used to store the subsets of the original relation    
      List<List<Character>> subset = new ArrayList<>();  
      //saves the attributes of the original relation into a String
      String relation = scan.nextLine();
      
      //List for the U values of any functional dependency U -> V
      ArrayList<String> U = new ArrayList<String>();
      //List for the V values of any functional dependency U -> V
      ArrayList<String> V = new ArrayList<String>();
      String temp ="";   
      
      //Adds each part of each functional dependency to its own seperate list
      while(scan.hasNextLine())
      {     
         temp = scan.nextLine();
         String[] strArray = temp.split("->",-1);
         U.add(strArray[0]);
         V.add(strArray[1]);
         count++;
      }
      
      relation = relation.replaceAll("\\s+","");
      
      //First turn the relation string into a char array, so that we can then convert it to an array list
      char[] set;
      set = relation.toCharArray();
   
      ArrayList<Character> input = new ArrayList<>();
      
      for(int x = 0; x < set.length; x++) 
      {
         input.add(set[x]);
      }
      
      //this will produce all of the subsets of the relation
      findSubsets(subset, input, new ArrayList<>(), 0);
      
      //Next we'll add all of the subsets to its own file
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
      PrintWriter super_keys = new PrintWriter("superkeys.txt");
           
      while(scanTwo.hasNextLine())
      {   
         String tempTwo = scanTwo.nextLine();
         
         Set<String> closures = new HashSet<String>();
         
         for (char c: tempTwo.toCharArray())
         {
            closures.add("" + c);
            
         }
         
         
         Set<String> closures_Two = new HashSet<String>(closures);
         
         do
         {
            
            for(int i = 0; i < count; i++)
            {  
               
               String leftFD = U.get(i); 
               String rightFD = V.get(i);
               
               
               boolean verifySub = true;
               for(char c: leftFD.toCharArray())
               {
                  
                  if(!closures.contains("" + c));
                  {
                  
                     verifySub = false;
                  }
               
               }
               //System.out.println(closures);
               if(verifySub)
               {
                  
                  //System.out.println(closures); //////////////////////
                  closures_Two = new HashSet<String>(closures);
                  //System.out.print(closures_Two);
                  for(char c: rightFD.toCharArray())
                  {
                     //System.out.println(closures);
                     closures.add("" + c);
                     //System.out.println(closures);
                  }
               }
            }  
         
         
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
