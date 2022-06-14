package myTask;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner; 
import java.util.regex.*;  



public class myFirstTask { 


	public static void main(String[] args) {
		int no_of_terms=0;
		System.out.println("Enter the Polynomial Equation1 : ");
		Scanner scanner=new Scanner(System.in);
		String input=scanner.next();//(2x+y)*(3x-5y)||(2x^2y+3xy^2z-xz^3)*(5xyz+3y^2z-2z)||(2xy+4x^2y)*(2x^2y+6xy);
		String eq[]=input.split("\\)\\*\\(");	
		String temp="";
		System.out.println(input.length());
//Finding variable in the given equation
		for (int i = 0; i < input.length(); i++) {
      if((temp.indexOf(input.charAt(i)) == -1) && Character.isLetter(input.charAt(i)) ){
          temp = temp + input.charAt(i);

      }}
		System.out.println(temp);
     char[] variables=temp.toCharArray();// variables in array
		
		int no_of_variables= temp.length();
		int no_of_expressions= eq.length;
		System.out.println(Arrays.asList(eq));
		int no_of_terms_each_eq[]=new int [no_of_expressions];
		
		for(int i=0;i<eq.length;i++)
			{eq[i]=eq[i].replace("(", "").replace(")", "");
			System.out.println(eq[i]+" ");
			}
// split the equation into terms		
		String terms="";
		for(int i=0;i<eq.length;i++)
			{terms += eq[i].replace("+", " ").replace("-", " ");
			terms+=" ";
			
			}
	
	//Count the term in each equation
		for (int i = 0; i < eq.length; i++) {
			for (int j = 0; j < eq[i].length(); j++){
				if (eq[i].charAt(j) == '+'||eq[i].charAt(j) == '-') {
					no_of_terms_each_eq[i]++;
          }
          
		}
		no_of_terms_each_eq[i]++;
//		System.out.println(no_of_terms_each_eq[i]);
         }
	    
			String terms_arr[]=terms.split(" ");
			no_of_terms = terms_arr.length;
			
			System.out.println(terms);
			System.out.println("Va:"+no_of_variables);
			System.out.println("exp: "+no_of_expressions);
			System.out.println("terms: "+no_of_terms);
			
			
			int expo[][][]=new int [no_of_expressions][][];
			int coef[][] = new int[no_of_expressions][];
			int coef_signs[][]=new int[no_of_expressions][];
			
			for(int i=0;i<no_of_expressions;i++)
			{
			   expo[i]=new int [no_of_terms_each_eq[i]][no_of_variables];
			   coef[i]=new int [no_of_terms_each_eq[i]];
			   coef_signs[i]=new int [no_of_terms_each_eq[i]];
			}
			
//finding coefficients
int coef_idx=0;
        

//finding signs

char[] sign_arr=input.replaceAll("[^+-]","").toCharArray();
//    System.out.println(sign_arr);
     for(int i=0;i<no_of_expressions;i++)
			{
			    for(int j=0;j<no_of_terms_each_eq[i];j++)
			    { 
			        //Sign of first term
			        if(eq[i].charAt(0)!='-'&&j==0)
			        {coef_signs[i][j]=1;continue;
			         }
			        else
			        {//coef_signs[i][0]=-1;}
			        
			        //sign of other terms
			        if(sign_arr[coef_idx]=='-')
			        {coef_signs[i][j]=-1;}
			        if(sign_arr[coef_idx]=='+')
			        {coef_signs[i][j]=1;}
			        }
			        coef_idx++;
//			        System.out.println(coef_signs[i][j]);
			    }
			    
			}
     
//finding coefficients
     		coef_idx=0;String tempv;
			for(int i=0;i<no_of_expressions;i++)
			{
			    for(int j=0;j<no_of_terms_each_eq[i];j++)
			    {  if(Character.isDigit(terms_arr[coef_idx].charAt(0)))
			        {  
			            coef[i][j]=Character.getNumericValue(terms_arr[coef_idx].charAt(0))*coef_signs[i][j];
			        }
			        else
			        {
			            coef[i][j]=1*coef_signs[i][j];
			        }
			        coef_idx++;
			        //System.out.println(coef[i][j]); 
			    }
			    
			}
			
//finding exponents
			int terms_idx=0;
			for(int i=0;i<no_of_expressions;i++)
			{
			    for(int j=0;j<no_of_terms_each_eq[i];j++)
			    {  
			        for(int k=0;k<no_of_variables;k++)
			        {
			            
			        		Pattern pattern = Pattern.compile("["+variables[k]+"][\\^][0-9]" );
			                Matcher m = pattern.matcher(terms_arr[terms_idx]);
			                boolean matchFound = m.find();
//			                System.out.println(terms_arr[terms_idx]+" "+variables[k]);
							 if (matchFound ) {
							expo[i][j][k]=Integer.parseInt(m.group(0).replaceAll("[^0-9]",""));}
							
							else if(terms_arr[terms_idx].contains(Character.toString(variables[k])))
							{
							    expo[i][j][k]=1;
							}
							else 
							{
							    expo[i][j][k]=0;
							}
//							System.out.println("exp "+expo[i][j][k]);
			 
			            
			     
			        }
			                terms_idx++;  }
			}
		

							
				int total_op_terms=1;
				
				for(int i=0;i<no_of_expressions;i++)			
				{
				 total_op_terms*=no_of_terms_each_eq[i];//no of terms in output
				}
				
				int output_expo[][]=new int[total_op_terms][no_of_variables];
				int output_coef[]=new int[total_op_terms];
				int out_idx=0;
				
				for(int i=0;i<no_of_expressions/2;i++)
				{
				 for(int j=0; j<no_of_terms_each_eq[i];j++)
				 {  
				     for(int k=0; k<no_of_terms_each_eq[i+1];k++)
				     {
				    	 output_coef[out_idx]=coef[i][j]*coef[i+1][k];
				     //	System.out.println(output_coef[out_idx]);
				         for(int l=0;l<no_of_variables&&out_idx<total_op_terms;l++)
				         	{
				        	   output_expo[out_idx][l]= expo[i][j][l]+expo[i+1][k][l];
				        	 }
				         out_idx++;
				     }
				     
				 }
				}
				
				
			String op="";out_idx=0;int out_idx_expo=0;
			
			boolean isSame=true;
			for(int i=0;i+1<total_op_terms;i++)
			    {
				for(int j=i+1;j<total_op_terms;j++) 
				{
					
					for(int k=0;k<no_of_variables;k++)
					{
					//System.out.println(i+" "+j+" "+k+output_expo.length);
					if(output_expo[i][k]==output_expo[j][k])
					{isSame=isSame&&true;}
					else
                    {isSame=isSame&&false;}
                    //System.out.println(output_expo[i][k]+" "+output_expo[j][k]+" ");
		            }
		            
		            if(isSame)
		            {
		            //System.out.println(output_coef[i]+"+"+output_coef[j]);
		            output_coef[j]=output_coef[i] + output_coef[j];
		            output_coef[i]=0;
		            }
		           //System.out.println(output_coef[i]); 
		            isSame=true;
			    }
			}
				
			
			
			
			    
				for(int j=0;j<total_op_terms;j++) {
				    
					if(output_coef[out_idx]>0&&out_idx!=0) 
					{op=op+"+"+String.valueOf(output_coef[out_idx]);}
					else if(output_coef[out_idx]==0)
					{
					    out_idx++;out_idx_expo++;continue;
					} 
					else
                      {op=op+String.valueOf(output_coef[out_idx]);}
					
					for(int k=0;k<no_of_variables;k++) {
					    
						if(output_expo[out_idx_expo][k]>1) {
							op=op+String.valueOf(variables[k])+"^"+String.valueOf(output_expo[out_idx_expo][k]);
							
						}
						if(output_expo[out_idx_expo][k]==1) {
							op=op+String.valueOf(variables[k]);
						}
						//System.out.println(j+" "+k);
						//System.out.println(out_idx+" "+out_idx_expo);
				}
					out_idx++;out_idx_expo++;
					
			}
				
	      
	      
	      System.out.println(op);
}
}

