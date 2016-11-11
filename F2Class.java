package Feature2;

import java.util.*;
import java.io.*;
import javax.swing.*;


public class F2Class {

		public static void main(String[] args) {
			String trusted = "trusted";
			String unverified = "unverified: You've never had a transaction with this user before. Are you sure you would like to proceed with this payment?";
			int a = 0;
			HashMap<Integer, ArrayList<Integer>> friends = new HashMap<Integer, ArrayList<Integer>>();
			String batchFile = "C:\\Users\\KevFe\\Desktop\\Masters\\Java\\JavaProjects\\Fellowship\\batch_payment.csv"; //read the batch data for previous transactions
			FileReader batchReader= null;
			try{
				batchReader = new FileReader (batchFile);
			}
			catch (FileNotFoundException e){
				e.printStackTrace();
			}
			BufferedReader baReader = new BufferedReader (batchReader);
			String batchInput;
			try{
				batchInput = baReader.readLine();
				System.out.println("Printing Batch Process Input Data");
				batchInput = baReader.readLine();
				System.out.println("Read the header");

			while (batchInput!=null)
				{

				StringTokenizer st = new StringTokenizer(batchInput, ",");
				String date = st.nextToken();
				if (date.length()>19){
					batchInput = baReader.readLine(); 
				}
				else {
				int id1 = Integer.parseInt((st.nextToken()).trim());
				int id2 = Integer.parseInt((st.nextToken()).trim());		

				if (friends.containsKey(id1)) {
					friends.get(id1).add(id2); 
				}
				else {
					friends.put(id1, new ArrayList<Integer>());  
					friends.get(id1).add(id2); 
					}

					batchInput = baReader.readLine();
					
				}
				}
			try{
				batchReader.close();
			}
			catch (IOException e){
				e.printStackTrace();
			}
			}
			catch (IOException e){
				e.printStackTrace();
		}
			System.out.println("done");

			 for (Map.Entry<Integer,ArrayList<Integer>> entry: friends.entrySet()){
					int key = entry.getKey();
					ArrayList<Integer> value =entry.getValue();
						String b = value.toString();
						b=b.replaceAll("\\[", " ").replaceAll("\\]", " ");
					while (b!=null)	{
							StringTokenizer it = new StringTokenizer(b, ",[]()");
							int keyValues = Integer.parseInt((it.nextToken()).trim());
							int keyLinked = friends.get(keyValues);
				ArrayList<Integer> valueLinked = friends.getValue(keyLinked); //get the value of the new key set
							String c = valueLinked.toString();
							c=c.replaceAll("\\[", " ").replaceAll("\\]", " ");
						while(c!=null){
							StringTokenizer it2 = new StringTokenizer(c, ",[]()");
							int keyValuesLinked = Integer.parseInt((it2.nextToken()).trim());
							friends.get(key).add(keyValuesLinked);
						}
		
					}
			 }
			 String streamFile = "C:\\Users\\KevFe\\Desktop\\Masters\\Java\\JavaProjects\\Fellowship\\stream_payment.csv"; //read the batch data for previous transactions
			 FileReader streamReader= null;
			 try{
			 	streamReader = new FileReader (streamFile);
			 }
			 catch (FileNotFoundException e){
			 	e.printStackTrace();
			 }
			 BufferedReader strReader = new BufferedReader (streamReader);
			 String streamInput;
			 try{
			 	streamInput = strReader.readLine();
			 
			 	streamInput = strReader.readLine();
			 	

			 	File outputOne =new File("output2.txt");
			 	FileWriter fwOne = new FileWriter(outputOne,true);
			 	BufferedWriter bwOne = new BufferedWriter(fwOne);

			 while (streamInput!=null){
			 	StringTokenizer stst = new StringTokenizer(streamInput, ",");
			 	String stDate = stst.nextToken();
			 	if (stDate.length()>19){
			 		streamInput = strReader.readLine();
			 	}
			 	else {
			 	int stId1 = Integer.parseInt((stst.nextToken()).trim());
			 	int stId2 = Integer.parseInt((stst.nextToken()).trim());		
			 	//double stAmount = Double.parseDouble((stst.nextToken()).trim());
			 	//String stMessage = stst.nextToken();

			 		if (friends.get(stId1).contains(stId2)){  
			 		bwOne.write(trusted + " \n");
			 		}
			 		else {
			 			bwOne.write(unverified + " \n");			
			 		}
			 				streamInput = strReader.readLine();

			 	}
			 }
			 try{
			 	bwOne.close();
			 }catch (IOException e){
			 	e.printStackTrace();
			 }
			 try {
			 	streamReader.close();
			 }catch (IOException e){
			 	e.printStackTrace();
			 }
			 }catch (IOException e){
			 	e.printStackTrace();
			 }
		}
				
}


