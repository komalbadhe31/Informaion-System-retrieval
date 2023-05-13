import java.io.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;
public class singlepassclustering 
{

	static Hashtable[] documents=new Hashtable[5];
	static Hashtable[] cluster=new Hashtable[5];
	static int[][] clusters=new int[5][5];
	static int noOfClusters=1; 
     
	public static void main(String[] args) throws IOException
	{  
		int loop;
		String[] doc=new String[6];
		BufferedReader fin;
		String line="";
		for(loop=0;loop<5;loop++)
		{
			documents[loop]=new Hashtable<String, Float>();
			cluster[loop]=new Hashtable<String, Float>();
		}
		
		Enumeration<?> temp;
		BufferedReader stdInpt = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the no of Documents:"); 
		int noOfDocuments=Integer.parseInt(stdInpt.readLine());  
		           System.out.println("Enter the threshold:");  
		           float threshhold=Float.parseFloat(stdInpt.readLine()); 
		           for(loop=0;loop<noOfDocuments;loop++)
		           {
		        	   System.out.println("Enter the Document Name:");
		        	   doc[loop]=stdInpt.readLine(); 
		           }
		           for(loop=0;loop<noOfDocuments;loop++)
		           {
					   fin=new BufferedReader(new FileReader("D:\\BE\\ISR\\Assign_1\\DocRep\\"+doc[loop]+".txt"));
					   while((line=fin.readLine())!=null)
					   {
						   StringTokenizer st = new StringTokenizer(line);
							 while (st.hasMoreElements()) 
							 {
								 String str = st.nextToken();
								 String ptr=st.nextToken();
								 float i=Float.parseFloat(ptr);
									documents[loop].put(str,i);
							 }
					   }
		           }
		          SinglePassAlgorithm(noOfDocuments, threshhold); 
		           
	}


	private static void SinglePassAlgorithm(int noOfDocuments,float threshhold) 
	     { 
	          cluster[0]=(Hashtable<String, Float>) documents[0].clone();
	          clusters[0][0]=1;
	          clusters[0][1]=0;
	          
	                      for(int i=1;i<noOfDocuments;++i)  
	                     {  
	        	                int clusterId=-1;   
	        	                for(int j=0;j<noOfClusters;++j) 
	        	                {  
	        	                     float similarity=calculateSimilarity(documents[i],cluster[j]); 
	        	                     if(similarity>=threshhold) 
	        	                     {  
 
	        	                          clusterId=j; 
	        			              } 
									  else
										  addtocluster(documents[i],noOfClusters);
	        			         } 
	        	                
	        	          }  
						  
						  if(cluster[noOfDocuments]>=2)
						  {
							  calculateClusterRepresentative(documents[i],cluster[clusterId]); 
						  }

	                      for(int i=0;i<noOfClusters;++i)             
	                      {                
	                    	  System.out.print("\n"+i+"\t");       
	                    	  for(int j=1;j<=clusters[i][0];++j) 
	                    	  {                     
	                    		  System.out.print(" "+clusters[i][j]);  
	                    	  }
	                      }
	                      
	                      
	                      
	        	      }  
					  
			private static void addtocluster(Hashtable<String, Float> doc, int noOfClusters) 
				{
				
	        	  cluster[noOfClusters-1]=(Hashtable<String, Float>) doc.clone();
		
				}
					  
	          private static void calculateClusterRepresentative(Hashtable<String, Float> doc, Hashtable<String, Float> clust) 
			  {
					int flag=0;
					float freq1,freq2 = 0;
					Enumeration<String> temp1,temp2;
					temp1=doc.keys();
					temp2=clust.keys();
					while(temp1.hasMoreElements())
	               {
	            	   flag=0;
	            	   String str = temp1.nextElement();
	            	   freq1= (Float) doc.get(str);
	            	   temp2=clust.keys();
	            	   while(temp2.hasMoreElements())
	            	   {
	            		   String str1 =temp2.nextElement();
	            		   freq2=(float) clust.get(str1);
	            		   if(str.equals(str1))
	            		   {
	            			flag=1;   
	            		   }
	            		}
	            	   if(flag==0)
	            	   {
	            		   clust.put(str,(Float) doc.get(str));
	            	   }
	            	   else if(flag==1)
	            	   {
	            		   freq1=freq1+freq2;
	            		   freq1=freq1/2;
	            		   clust.put(str, freq1);
	            	   }
	               }
	               
	        	  
	        	  
			}

			private static float calculateSimilarity(Hashtable<String, Float> doc,Hashtable<String, Float> clust)  
	          {
	               double answer=0; 
	               Enumeration<?> temp1,temp2;
	               temp1=doc.keys();
	               temp2=clust.keys();
				   int clustId =2;
	               while(temp1.hasMoreElements())
	               {
	            	   String str = (String) temp1.nextElement();
	            	   temp2=clust.keys();
		                  while(temp2.hasMoreElements())
	            	   {
	            		   String str1 = (String) temp2.nextElement();
	            		   if(str.equals(str1))
	            		   {
	            			   answer++;
	            		   }
	            	   }
					   answer=clustId.nextElement()*answer;
	               } 
	               
	               return (float) answer; 
	          }  
} 