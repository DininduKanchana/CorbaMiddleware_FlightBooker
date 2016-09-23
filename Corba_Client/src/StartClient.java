
import FlightBookingApp.*;
 
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.io.*;
import java.util.*;
 
public class StartClient {
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      try {
    	  
	    ORB orb = ORB.init(args, null);
	    org.omg.CORBA.Object objRef =   orb.resolve_initial_references("NameService");
	    NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
	    FlightBooking addobj = (FlightBooking) FlightBookingHelper.narrow(ncRef.resolve_str("ABC"));
	    Scanner c=new Scanner(System.in);
            
            //addobj.search("colombo", "Laknow");
           // addobj.book(2, "");
            //addobj.search("colombo", "Laknow");
            
		    for(;;){
		    	System.out.println("-----------------------------------");
		    	System.out.print("Enter 1 to search flights,Enter 2 to book flights ,Enter 3 to cancle booking :");
		    	String aa = c.nextLine();
		      
		      //System.out.println("Enter b:");
		      //String bb = c.nextLine();
		      int a=Integer.parseInt(aa);
		      switch(a){
		      	case 1:
		      		System.out.print("From : ");
		      		String bb = c.nextLine();
		      		
		      		System.out.print("To : ");
		      		String cc = c.nextLine();
		      		System.out.print(addobj.search(bb, cc));
		      		
		      		//addobj.search("colombo", "Laknow");
		      		//System.out.println("came here");
		    	  break;
		      	case 2:
		      		System.out.print("Enter full name : ");
		      		String name = c.nextLine();
		      		
		      		System.out.print("Enter date(year/month/date) : ");
		      		String date = c.nextLine();
		      		
		      		System.out.print("Enter Flight id : ");
		      		String Id = c.nextLine();
		      		int id = Integer.parseInt(Id);
		      		
		      		System.out.println(addobj.book(id,name, date));
		      		break;
		      	case 3:
		      		System.out.print("Enter booking id : ");
		      		String bookingId = c.nextLine();
		      		int bookId = Integer.parseInt(bookingId);
		      		
		      		System.out.print("Enter Your name : ");
		      		String bookName = c.nextLine();
		      		
		      		System.out.print("Enter booked date(year/month/date) : ");
		      		String bookDate = c.nextLine();
		      		
		      		
		      		System.out.println(addobj.cancleBooking(bookId, bookName, bookDate));
		      		break;
		      	default:
		      		System.out.println("Your input is wrong!\n");
		    	  
		      }
		      //int b=Integer.parseInt(bb);
		      //int r=addobj.add(a,b);
		      //System.out.println("The result for addition is : "+r);
		      System.out.println("-----------------------------------");
            }
       }
       catch (Exception e) {
          System.out.println("Hello Client exception: " + e);
	  e.printStackTrace();
       }
 
    }
 
}