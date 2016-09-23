package appPack;

import FlightBookingApp.*;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

import java.awt.List;
import java.util.ArrayList;
import java.util.Properties;
 
public class StartServer extends flight{
	
 
  public static void main(String args[]) {
    try{
      // create and initialize the ORB //// get reference to rootpoa &amp; activate the POAManager
      ORB orb = ORB.init(args, null);      
      POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      rootpoa.the_POAManager().activate();
 
      // create servant and register it with the ORB
      AdditionObj addobj = new AdditionObj();
      addobj.setORB(orb); 
 
      // get object reference from the servant
      org.omg.CORBA.Object ref = rootpoa.servant_to_reference(addobj);
      FlightBooking href = FlightBookingHelper.narrow(ref);
 
      org.omg.CORBA.Object objRef =  orb.resolve_initial_references("NameService");
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
 
      NameComponent path[] = ncRef.to_name( "ABC" );
      ncRef.rebind(path, href);
 
      System.out.println("Welcome to Flightbooker ...");
      
      
      
      
      flight f1 = new flight();
      f1.id = 1;
      f1.name = "boaing";
      f1.from = "colombo";
      f1.to = "new delhi";
      f1.Date = "2017/07/02";
      f1.avalableSeats = 100;
      f1.arrivalTime = "00:00";
      f1.departureTime = "05:33";
      listA.add(f1);
      
      flight f2 = new flight();
      f2.id = 2;
      f2.name = "boaing";
      f2.from = "colombo";
      f2.to = "laknow";
      f2.Date = "2017/07/02";
      f2.avalableSeats = 150;
      f2.arrivalTime = "00:00";
      f2.departureTime = "05:33";
      listA.add(f2);
      
      flight f3 = new flight();
      f3.id = 3;
      f3.name = "boaing";
      f3.from = "us";
      f3.to = "uk";
      f3.Date = "2017/07/02";
      f3.arrivalTime = "00:00";
      f3.departureTime = "05:33";
      f3.avalableSeats = 150;
      listA.add(f3);
    		  
      flight f4 = new flight();
      f4.id = 4;
      f4.name = "jetAirways";
      f4.from = "us";
      f4.to = "uk";
      f4.Date = "2017/07/02";
      f4.arrivalTime = "00:00";
      f4.departureTime = "05:33";
      f4.avalableSeats = 150;
      listA.add(f4);
      
      
      // wait for invocations from clients
      for (;;){
	  orb.run();
      }
    } 
 
      catch (Exception e) {
        System.err.println("ERROR: " + e);
        e.printStackTrace(System.out);
      }
 
      System.out.println("HelloServer Exiting ...");
 
  }
}