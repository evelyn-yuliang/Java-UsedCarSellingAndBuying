package guiLab.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;


public class UsedCarRandomAccess {

	
		private File carFile = null;
		private RandomAccessFile dataFile = null;
		private String FIELD_SEP = "\t";
		private final int CAR_STRING = 10;
		//one make+five model
		private final int RECORD_SIZE = 10*2*6;
		private int count;
		//creat a new file, input the data
		public UsedCarRandomAccess() {
			carFile = new File("UsedCarMake.dat");
			//addCar();
		}
		
		public void addCar() {
			
			try {
				dataFile = new RandomAccessFile(carFile,"rw");
				count = (int)dataFile.length()/RECORD_SIZE;
				dataFile.seek(count*RECORD_SIZE);
				writeString(dataFile,CAR_STRING,"Mazda");
				writeString(dataFile,CAR_STRING,"CX-3");
				writeString(dataFile,CAR_STRING,"CX-5");
				writeString(dataFile,CAR_STRING,"CX-9");
				writeString(dataFile,CAR_STRING,"Mazda3");
				writeString(dataFile,CAR_STRING,"Mazda6");
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				try {
					dataFile.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
			}
			
		}
		
		private static void writeString(DataOutput output, int size, String s) {
			// TODO Auto-generated method stub
		
					try {
						for(int i=0;i<size;i++) {
							if(i<s.length())
								output.writeChar(s.charAt(i));
							else
								output.writeChar(0);
						} 
					}
						catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

		}
		
		public ArrayList<String> getMake() {
			ArrayList<String> makes = new ArrayList<String>();
			try {
				dataFile = new RandomAccessFile(carFile,"rw");
				count = (int)dataFile.length()/RECORD_SIZE;
				for(int i=0; i<count; i++) {
					dataFile.seek(i*RECORD_SIZE);
					makes.add(readString(dataFile,CAR_STRING));
				}	
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				dataFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return makes;
			
			
		}
		
		public ArrayList<String> getAllModel(){
			ArrayList<String> models = new ArrayList<String>();
			try {
				dataFile = new RandomAccessFile(carFile,"rw");
				count = (int)dataFile.length()/RECORD_SIZE;
				for(int i=0; i<count; i++) {
					dataFile.seek(i*RECORD_SIZE+10);
					models.add(readString(dataFile,CAR_STRING));
					models.add(readString(dataFile,CAR_STRING));
					models.add(readString(dataFile,CAR_STRING));
					models.add(readString(dataFile,CAR_STRING));
					models.add(readString(dataFile,CAR_STRING));
				}	
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				dataFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return models;
			
		}
		
		public ArrayList<String> getModel(int i) {
			ArrayList<String> models = new ArrayList<String>();
			try {
				dataFile = new RandomAccessFile(carFile,"rw");
				count = (int)dataFile.length()/RECORD_SIZE;
				if(i<=count) {
					dataFile.seek((i)*RECORD_SIZE);
					models.add(readString(dataFile,CAR_STRING));
					models.add(readString(dataFile,CAR_STRING));
					models.add(readString(dataFile,CAR_STRING));
					models.add(readString(dataFile,CAR_STRING));
					models.add(readString(dataFile,CAR_STRING));
					models.add(readString(dataFile,CAR_STRING));
					
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				dataFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return models;
			
		}
		
		private static String readString(DataInput input, int size) {
			// TODO Auto-generated method stub
			StringBuilder sb = new StringBuilder();
			
				try {
					for(int i =0;i<size;i++) {
						char c;
					c = input.readChar();
					if(c!=0)
						sb.append(c);
					} 	
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//if char is not unicode zero
			return sb.toString();
		}
		
		public static void main(String[] args) {
			new UsedCarRandomAccess();

		}
	}



