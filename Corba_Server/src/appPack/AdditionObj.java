package appPack;

import FlightBookingApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

import java.util.ArrayList;
import java.util.Properties;
 
class AdditionObj extends FlightBookingPOA {
  private ORB orb;
 
  public void setORB(ORB orb_val) {
    orb = orb_val; 
  }
 
  // implement add() method
  
  public String search(String From,String To){
	  ArrayList<flight> array =flight.listA;
	  //ArrayList<String> flightList = new ArrayList();
	  String flightlist[][] = new String[10][5];
	  String searchResult = "";
	  From = From.toLowerCase();
	  To = To.toLowerCase();
	  int j = array.size();
	  int found = 0;
	  System.out.println("search");
	  
 
	  for(int i=0;i<j;i++){
		  	if((array.get(i).from).equals(From) && (array.get(i).to).equals(To)){
			  found = 1;
			  searchResult = "Flight Id: "+array.get(i).id + "\t Flight Name: "+ 
			  array.get(i).name + "\t Avalable seats: "+array.get(i).avalableSeats +
			  "\t Depart From "+ From  + " at " + array.get(i).departureTime +
			  "\t Arrive To "+ To  + " at " + array.get(i).arrivalTime;
			  
			  
			  
		  }
		  
		  
	  }
	  if(found == 0){
		  searchResult = "Sorry!! No Flights Found \n";
		  
	  }
	  return searchResult;
  }
  
 
  public int add(int a, int b) {
    int r=a+b;
    return r;
  }
  
 
  // implement shutdown() method
  public void shutdown() {
    orb.shutdown(false);
  }

@Override
public String book(int id, String name,String date) {
	booking b = new booking();
	b.bookid = booking.bookingId++;
	b.flightId = id;
	b.bookDate = date;
	b.cusName = name;
	
	booking.bookingArray.add(b);
	
	ArrayList<flight> array =flight.listA;
	String bookResult ="";
	int j = array.size();
	int found = 0;

	  for(int i=0;i<j;i++){
		  	if(array.get(i).id == id && (array.get(i).avalableSeats > 0)){
		  		-- array.get(i).avalableSeats;
		  		bookResult = "Succesfully booked Flight number "+id + " on "+ date + "\n your booking id is "+b.bookid;
		  		
		  		found = 1;
		  		break;
		  	}
	  }
	  if(found == 0){
		  bookResult = "Sorry you can not book this flight!!!";
		  System.out.println("Sorry you can not book this flight!!!");
	  }
	// TODO Auto-generated method stub
	return bookResult;
}

public String cancleBooking(int id, String name,String date){
	int found = 0;
	String cancleStr = "";
	ArrayList<flight> array = flight.listA;
	int j = array.size();
	int flightID = 0;
	for(int i = 0;i < booking.bookingArray.size(); i++){
		booking b1 = new booking();
		b1 = (booking) booking.bookingArray.get(i);
		//cancleStr = b1.bookid +" " + b1.bookDate +" "+b1.cusName;
		
		if(b1.bookid == id && b1.bookDate.equals(date) && b1.cusName.equals(name)){
			found = 1;
			flightID = b1.flightId;
			
			
			
		}
	}
	if(found == 1){
		for(int i=0;i<j;i++){
		  	if(array.get(i).id == flightID ){
		  		++ array.get(i).avalableSeats;
		  		cancleStr = "Your booking is cancled...";
		  		}
	  }
	}
	
	if(found == 0){
		cancleStr = "Sorry you dont have a booking on "+ date + " with id " + id;
	}
	return cancleStr;
	
}



}