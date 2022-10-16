/*
   Authors: Ashley Pell, Quinten Williams, and Juan Guzman
   Date: 10/02/2022
   This program takes input from an input file 
   (named "FDs") that contains a set of functional dependencies (F) and 
   a set of all attributes (R) for a relation, and output all possible superkeys 
   for the relation to an output file (named "superkeys").
   
   The program takes an input file of functional dependencies. Using the
   method findSubsets we add the values into subsets. These values are then written into a textfile
   called closures.txt from which our superkeys.txt is derived from.
   
   For the test case the program doesn't iterate back through if the closure begins with 
   the relation C. 
   The initial printwriter needs to be closed after the closures.txt is created or we can not implement it
   through the remainder of the code.
   Index could get out of bounds if the relations are substantial.
    
 */
import java.util.*; //Scanner Collection, Arraylist, 
import java.io.*; //IOException Printfile FileWriter

public class project3_PellWilliamsGuzman
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
   
   	// Not Including Value which is at Index
      findSubsets(subset, nums, new ArrayList<>(output), index + 1);
   
   	// Including Value which is at Index
      output.add(nums.get(index));
      findSubsets(subset, nums, new ArrayList<>(output), index + 1);
   }

   /*
     Gets rid of any excess copies of all potential superkeys
   */
   static String removeDuplicates(String str)
   {
      String result = "";
   	//Create LinkedHashSet of type character
      LinkedHashSet<Character> set = new LinkedHashSet<>();
   	//Add each character of the string into LinkedHashSet
      for(int i=0;i<str.length();i++)
      
         set.add(str.charAt(i));
         
      for(Character ch : set)
      {
         result = result + ch;
      }
        
      return result;
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
      PrintWriter output = new PrintWriter("closures.txt");
   
      //writes the subsets to a new file called closure
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
      PrintWriter super_keys = new PrintWriter("superkeys.txt");
     
      //This loop will test to see if each potential superkey actually is a superkey
      while(scanTwo.hasNextLine())
      {   
         tempTwo = scanTwo.nextLine();
         String cpyStr = "";
         char[] charOfkey = tempTwo.toCharArray();
         char[] copy = new char[charOfkey.length * 5];
      
         for(int i = 0; i < count; i++) // loop for each FD
         {
            int doCount = 0;
            char[] Uchar = U.get(i).toCharArray();
            char[] Vchar = V.get(i).toCharArray();
            String copyAtTime = "";
                     
            do
            {
               int ulen = 0;
               int Utest = 0;
            
               if(doCount == 0)
               {
                  for(int j = 0; j < charOfkey.length ; j++)  // loop for characters in current closure  ABC
                  {
                     if(ulen < Uchar.length)
                     {
                        if(charOfkey[j] == Uchar[ulen]) // when a match occurs a counter is incremented AB
                        {
                           Utest++;
                           ulen++;
                        }
                     }
                  
                  } // end of current closure loop if all the attributes of U are present in closure 
               	// then Utest will equal the length of Uchar
                            
                  if(Utest == Uchar.length) // U is a subset of Relation 
                  {
                  
                     for(int j = 0; j < charOfkey.length ; j++)  // loop for characters in the relation relation is : CDF
                     {
                        copy[j] = charOfkey[j];
                     
                        for(int k = 0; k < Vchar.length; k++) // loop for characters in the V (Right) of the FD which is AE
                        { 
                           copy[charOfkey.length + k] = Vchar[k]; 
                           copyAtTime = new String(copy);                                                
                        }
                     }
                  }              
               }
               else
               {
                  for(int j = 0; j < copy.length ; j++)  // loop for characters in current closure  ABC
                  {
                     if(ulen < Uchar.length)
                     {
                        if(copy[j] == Uchar[ulen]) // when a match occurs a counter is incremented AB
                        {
                           Utest++;
                           ulen++;
                        }
                     }
                  
                  } // end of current closure loop if all the attributes of U are present in closure 
               	// then Utest will equal the length of Uchar
                                
                  if(Utest == Uchar.length) // U is a subset of Relation 
                  {                  
                     for(int j = 0; j < copy.length ; j++) 
                     {
                        if(copy[j] == 0) 
                        {                        
                           for(int k = 0; k < Vchar.length; k++) // loop for characters in the V (Right) of the FD which is AE
                           { 
                              copy[j + k] = Vchar[k];
                              copyAtTime = new String(copy);
                              break;    
                           }  	
                        }                     	 
                     }                 	
                  }               	
               }
               
               Arrays.sort(copy);
               cpyStr = new String(copy);
               cpyStr = cpyStr.replaceAll("\\s+","");
               cpyStr = removeDuplicates(cpyStr);
               doCount++;
            
            }while(doCount < 2);         
         }  // end FD loop 
         
         // If we get here, that means that the potential superkey actually is a superkey
         // thus, we'll add it to the output file
         if(relation.equalsIgnoreCase(cpyStr)) 
         {
            super_keys.println(tempTwo);
         }
      
      } // end while hasnext
      scan.close();
      scanTwo.close();
      super_keys.close();
   }
}
