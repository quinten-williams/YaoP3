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

	static String removeDuplicates(String str)
	{
		String result = "";
		//Create LinkedHashSet of type character
		LinkedHashSet<Character> set = new LinkedHashSet<>();
		//Add each character of the string into LinkedHashSet
		for(int i=0;i<str.length();i++)
			set.add(str.charAt(i));

		//
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

		PrintWriter super_keys = new PrintWriter("superkeys.txt");


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
									System.out.println("Copy  : " + copyAtTime + "  when j is :" + j + " And the closure is: " + tempTwo + "when fd is: " + i );



								}
							}
						}

					}
					else
					{
						//String str = new String(copy);
						//str = removeDuplicates(str);
						//copy = str.toCharArray();
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
										System.out.println("Copy  : " + copyAtTime + "  when j is :" + j + " And the closure is: " 
										+ tempTwo + "   when fd is: " + i );
										break; 

									}
									
								} 
								// break; two results 
							}
							//break; 6 results
						}
						//break; 6 results
					}
					/*char charArray[] = str.toCharArray();
            			Arrays.sort(charArray);
            			System.out.println(new String(charArray));*/
					
					Arrays.sort(copy);
					cpyStr = new String(copy);
					cpyStr = cpyStr.replaceAll("\\s+","");
					cpyStr = removeDuplicates(cpyStr);
					
					//copy = cpyStr.toCharArray();
					//System.out.println("Copy after edit : " + cpyStr +" The Relation: "+ relation + " And the closure is: " + tempTwo + "when fd is: " + count );
					doCount++;

				}while(doCount < 2);
				//Arrays.sort(copy);
				//cpyStr = new String(copy);
				//cpyStr = cpyStr.replaceAll("\\s+","");
				//cpyStr = removeDuplicates(cpyStr);

			}  // end FD loop 


			if(relation.equalsIgnoreCase(cpyStr)) 
			{
				//verify = true; 
				super_keys.println(tempTwo);
				System.out.println("Printed : " + tempTwo + "  To the file");
			}



		} // end while hasnext
		scan.close();
		scanTwo.close();
		super_keys.close();
	}
}