package Feature1;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

import java.io.FileInputStream;



public class F1Class {


	public static void main(String[] args) {
		String trusted = "trusted";
		String unverified = "unverified: You've never had a transaction with this user before. Are you sure you would like to proceed with this payment?";

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
			batchInput = baReader.readLine();

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
				friends.get(id1).add(id2); //get the key and add to the list
			
				}

				else {
				friends.put(id1, new ArrayList<Integer>());  //create a new key and new list
				friends.get(id1).add(id2); //get the new key and add to the list
				
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

System.out.println("done with batch reading");
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
	
	File outputOne =new File("output1.txt");
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

