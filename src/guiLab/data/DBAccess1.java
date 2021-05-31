package guiLab.data;
import java.sql.*;
import java.util.ArrayList;

import guiLab.business.Car;
import guiLab.business.Person;


public class DBAccess1 {
	protected Connection con = null;
	protected ResultSet rs = null;
	protected ResultSet rs1 = null;
	protected Statement stmt = null;
	protected Statement stmt1 = null;
	protected String sql = null;
	
	public DBAccess1() throws SQLException, ClassNotFoundException{
		this.connect();
	}
	
	protected void connect() throws ClassNotFoundException,SQLException {
		
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//create connection twice
			con=DriverManager.getConnection(  
					"jdbc:oracle:thin:@calvin.humber.ca:1521:grok","N01353747","oracle");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=stmt.executeQuery("select * from USEDCAR_CARINFO");  
			rs1=stmt1.executeQuery("select * from USEDCAR_USER");  
		
		
	}
	
	public void disconnect() throws SQLException{
		if(!rs.isClosed() || !rs1.isClosed()) {
			rs.close();
			rs1.close();
			con.close();
		}
	}
	
	protected void refreshUser() throws SQLException{
		sql="select * from USEDCAR_USER";
		//create a scrollable,updatable resultset
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		rs1=stmt.executeQuery(sql);
		rs1.first();
	}
	
	protected void refreshCar() throws SQLException{
		sql="select * from USEDCAR_CARINFO";
		//create a scrollable,updatable resultset
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		rs=stmt.executeQuery(sql);
		rs.first();
	}
	
	public boolean addUser(Person person)  {
		PreparedStatement stm = null;
		
		String sql = "Insert into USEDCAR_USER(USERID,PASSWORD)VALUES(?,?)";
		try {

			stm = con.prepareStatement(sql);
			stm.setString(1, person.getUserName());
			stm.setString(2, person.getPassword());
			
			stm.executeUpdate();
			refreshUser();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
		
		
		
	}
	

		
	
	  public Person getUser(String userName, String password) throws SQLException {
		  Person person = null;
	      if(rs1 != null) {
	    	   while(rs1.next()) {
	    		  
	    		   if(rs1.getString(1).equals(userName) && rs1.getString(2).equals(password)) {
	    			  
	    			   person = new Person(userName, password);
	    			   
	    			   return person;
	    		   }
	    		   
	    	   } 
	    	  
	        }
	      rs1.first();
	        return null;
	    }

	
	  public boolean isValidNewUserName(String userName){
		 
			  try {
				  // while rs!= null?? get into infinite loop as rs!=null is always true
				 if(rs1 != null) {
					  while(rs1.next()) {
					
						  if(rs1.getString(1).equals(userName)) {
							  System.out.print(rs1.getString(1));
							  rs1.first();
							  return false;
						  }
							 
					
					  }
				  }
				 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
		  
		  return true;
	    }
	  


	
	public boolean addUsedCar(Car car){
		PreparedStatement stm = null;

		String sql="Insert into USEDCAR_CARINFO(CARID,CARYEAR,CARMAKE,CARMODEL,CARCOLOR,CARCONDITION,CARMILE,UPDATEDATE,CAROWNER)values(seq_usedcar.nextval,?,?,?,?,?,?,SYSDATE,?)";
		try {
			stm = con.prepareStatement(sql);
			stm.setInt(1,car.getYear());
			stm.setString(2,car.getMake());
			stm.setString(3,car.getModel());
			stm.setString(4,car.getColor());
			stm.setString(5,car.getDescription());
			stm.setInt(6,car.getMileage());
			stm.setString(7,car.getowner());
			stm.executeUpdate();
			refreshCar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	
		return true;
		
	}
	
	
	
//	public int deleteProgram(CProgram program) throws SQLException{
//		String sql = "delete from programs where PROGRAMIID=?";
//		PreparedStatement p = con.prepareStatement(sql);
//		p.setString(1, program.getpID());
//		p.execute();
//		refresh();
//		return 1;
//	}
//	
//	public int updateProgram(CProgram program) throws SQLException{
//		PreparedStatement stm = null;
//		String sql = "update programs set LASTNAME=?,SEMESTER=?,MAXSEATS=? where programiid=?";
//		stm=con.prepareStatement(sql);
//		stm.setString(1, program.getpName());
//		stm.setInt(2,program.getpSemesters());
//		stm.setInt(3,program.getpSeats());
//		stm.setString(4,program.getpID());
//		stm.executeUpdate();
//		refresh();
////	
//		return 1;
//	}
	
	
	public ResultSet getUsedCar() {
		ResultSet rs = null;
		Statement stmt;
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=stmt.executeQuery("select * from USEDCAR_CARINFO");  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return rs;
		
	}
	
	public ResultSet getUser() {
		ResultSet rs1 = null;
		Statement stmt;
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs1=stmt.executeQuery("select * from USEDCAR_USER");  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return rs1;
	}

}
