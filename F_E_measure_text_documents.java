import java.util.Scanner;

public class F_E_measure 
{
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		String Relevant_arr[]= {"d3", "d5", "d9", "d25", "d39", "d44", "d56", "d71", "d89", "d123"};    // R
		//System.out.println(Relevant_arr.length);
		int Relevant_size = Relevant_arr.length;		//  |R|
		String Retrieved_arr [] = {"d123", "d84", "d56", "d6", "d8", "d9", "d511", "d129", "d187", "d25", "d38", "d48", "d250", "d113", "d3"};   // A
		int Retrieved_size = Retrieved_arr.length;		//   |A|
		
		int count = 0; 
		double precision,recall;
		double[] F_arr = new double[15];  // Array for storing F - values
		
		// Arrays for storing values of E having 'b' greater than 1, zero and less than 1
		double[] E_b_greater = new double[15];
		double[] E_b_lesser = new double[15];
		double[] E_b_equal = new double[15];
		
		String Retrived_string = "";
		
		System.out.println("\nRelevant documents : ");
		for(int i=0;i<Relevant_size;i++)
		{
			System.out.print(Relevant_arr[i] + ", ");
		}
		System.out.println("\n");
		
		System.out.println("\nAnswer set : ");
		for(int i=0;i<Retrieved_size;i++)
		{
			System.out.print(Retrieved_arr[i] + ", ");
		}
		System.out.println("\n");		
		
		for(int i=0;i<Retrieved_size;++i) {
			for(int j=0;j<Relevant_size;++j) {
				if(Retrieved_arr[i]== Relevant_arr[j]) {
					count++;
				}
			}
			
			double c = count;
			precision = c/(i+1);
			recall = c/ Relevant_size;
			
			F_arr[i]= 2 / ((1 / recall) + (1 / precision));
			
			double b = 1.1;
			E_b_greater[i] = 1 -((1 + Math.pow(b, 2)) / ((Math.pow(b, 2)/recall) + (1 / precision)));
			
			b = 0;
			E_b_equal[i] =  1 -((1 + Math.pow(b, 2)) / ((Math.pow(b, 2)/recall) + (1 / precision)));
			
			b = 0.9;
			E_b_lesser[i]= 1 -((1 + Math.pow(b, 2)) / ((Math.pow(b, 2)/recall) + (1 / precision)));
			
			
			Retrived_string = Retrived_string + " " + Retrieved_arr[i];
			System.out.print(Retrived_string);
			
			for(int m=1;m<=70-Retrived_string.length();++m) {
				System.out.print(" ");
			}
			double p = precision * 100;
			double r = recall *100;
			System.out.print("Precision :"+ String.format("%.2f", p) + "% "+ "Recall : "+ String.format("%.2f", r) + " % \n");			
		}
		
		System.out.println("--------------------------------------------------------------------");
		System.out.println("Harmonic mean and E-value");
		System.out.println("--------------------------------------------------------------------");
		System.out.print("\nEnter value of j(0 - 14) to find F(j) and E(j): ");
		//taking the input from user
		Scanner scc= new Scanner(System.in); //System.in is a standard input stream  
		int ef_index= scc.nextInt();              //reads int
		
		System.out.println("\n--------------------------------------------------------------------");
		System.out.println("Harmonic Mean (F1) is : "+ String.format("%.2f", F_arr[ef_index]));
		System.out.println("--------------------------------------------------------------------");
		System.out.println("");
		System.out.println("-------------------------------E-Value------------------------------");
		System.out.println("      b > 1             b = 0              b < 1   ");
		System.out.println("--------------------------------------------------------------------");
		System.out.println("      " + String.format("%.2f", E_b_greater[ef_index]) + "              " + String.format("%.2f", E_b_equal[ef_index]) + "               " + String.format("%.2f", E_b_lesser[ef_index]) + "     ");
		System.out.println("--------------------------------------------------------------------");
		
	}
}