/***************************************************
 * Author: Apurva Gandhi
 * Email: ahgand22@g.holycross.edu
 * Written:09/07/2018
 * Updated: 09/13/2018
 * Prints the distance from home to destination in the
 * console using longitude and latitude of the destination and home through a command-line 
 * argument provided by user. In addition, it provides URL to the map of the trip entered using command line arguments
 *
 * Example: java Flight 42.2381 N 71.8109 W 37.362517 N 122.034760 W 
 ****************************************************/
public class Flight 
{

	public static void main(String[] args)
	{
		//Conversion of Home latitude and longitude from argument to variables
        //Earth Radius
		double earthRadius = 3963.1676;//Miles
        //Taking input from command line argument for home latitude
		double homeLatitude = Double.parseDouble(args[0]);
        //Taking input from command line argument for home latitue hamisphere
		char homeNorthSouth = args[1].charAt(0);
        //Taking input from command line argument for destination latitude
		double homeLongitude = Double.parseDouble(args[2]);
        //Taking input from command line argument for destination hamishphere
		char homeEastWest = args[3].charAt(0);

		//Conversion of destination latitude and longitude from argument to variables
		double destinationLatitude = Double.parseDouble(args[4]);//Stores the destination latitude
		char destinationNorthSouth = args[5].charAt(0); //Stores the north/south hamisphere from the argument
		double destinationLongitude = Double.parseDouble(args[6]);//Stores the destination Longitude
		char destinationEastWest = args[7].charAt(0);//Stores the East/West hamisphere from the argument

		//Prints out home coordinates
		System.out.println("Home Latitude is " + homeLatitude + " " + homeNorthSouth);
		System.out.println("Home Longitude is " + homeLongitude + " " + homeEastWest);

		//Prints out destination coordinates
		System.out.println("Destination Latitude: " + destinationLatitude + " " + destinationNorthSouth);
		System.out.println("Destination Longitude: " + destinationLongitude + " " + destinationEastWest);

		//checking if there is an error in the input of the coordinates
		//checking if there is an error in Home Latitude
        // || 
		if (homeLatitude>90 || homeLatitude<0 || (!(homeNorthSouth == 'N' ||  homeNorthSouth == 'S' || homeNorthSouth == 'n' ||  homeNorthSouth == 's')))
		{
			System.out.println("Sorry, the home coordinates you entered are not valid");
			System.out.println("the destination N/S hamisphere you entered is not valid.");
			System.out.println("Because of invalid coordinated, the program will terminate");
			System.exit(1);
		}
		//checking if there is an error in Destination Latitude
        // 
		if (destinationLatitude>90 || destinationLatitude<0 || (!(destinationNorthSouth == 'N' ||destinationNorthSouth == 'S' || destinationNorthSouth == 'n' ||destinationNorthSouth == 's')))
		{
			System.out.println("Sorry, the destination coordinates you entered are not valid");
			System.out.println("the destination N/S hamisphere you entered is not valid.");
			System.out.println("Because of invalid coordinated, the program will terminate");
			System.exit(1);
		}
		//checking if there is an error in Home Longitude
		if (homeLongitude>180 || homeLongitude<0 || (!(homeEastWest == 'E' || homeEastWest == 'W' || homeEastWest == 'e' || homeEastWest == 'w')))
		{
			System.out.println("Sorry, the Home coordinates you entered are not valid");
			System.out.println("the destination E/W hamisphere you entered is not valid.");
			System.out.println("Because of invalid coordinated, the program will terminate");
			System.exit(1);
		}
		//checking if there is an error in Destination Longitude
		if (destinationLongitude>180 || destinationLongitude<0 || (!(destinationEastWest == 'E' || destinationEastWest == 'W' || destinationEastWest == 'e' || destinationEastWest == 'w')))
        {
            System.out.println("Sorry, the destination coordinates you entered are not valid");
            System.out.println("the destination E/W hamisphere you entered is not valid.");
            System.out.println("Because of invalid coordinated, the program will terminate");
            System.exit(1);
        }
        //Converting all the HOME coordinates to signed decimal degrees format
        //Home Latitude
        if (homeNorthSouth == 'S' || homeNorthSouth == 's')
        {
            homeLatitude *= -1;	
        }
        //Home Longitude
        if (homeEastWest == 'W' || homeEastWest == 'w')
        {
            homeLongitude *= -1;
        }

        //Converting all the DESTINATION coordinates to signed decimal degrees format
        //Destination Latitude
        if (destinationNorthSouth == 'S' || destinationNorthSouth == 's')
        {
            destinationLatitude *= -1;	
        }
        //Destination Longitude
        if (destinationEastWest == 'W' || destinationEastWest == 'w')
        {
            destinationLongitude *= -1;
        }	

        /*Converting all coordinates to signed radians format by using a function from Math library (Math.Radians)*/
        //Home coordinates conversion of Latitude and Longitude to radians
        double homeLatitudeRadians = Math.toRadians(homeLatitude);
        double homeLongitudeRadians = Math.toRadians(homeLongitude);
        //Destinationcoordinates conversion of Latitude and Longitude to radius
        double destinationLatitudeRadians = Math.toRadians(destinationLatitude);
        double destinationLongitudeRadians = Math.toRadians(destinationLongitude);

        /*First part of the velocity equation*/
        //subtracting the two radian latitude coordinates
        double latitudeDiff = destinationLatitudeRadians - homeLatitudeRadians;
        //dividing latitude difference by 2 and taking sin of that value
        double sinLatitudeDiff = Math.sin(latitudeDiff/2);
        //taking square of the sin value of the latitude difference divided by 2
        double sinLatitudeDiffSquare = Math.pow(sinLatitudeDiff,2.0);

        /*Second part of the velocity equation*/
        //subtracting the two radians Longitude coordinates 
        double LongitudeDiff = destinationLongitudeRadians - homeLongitudeRadians;
        //dividing Longitude difference by 2 and taking sin of that value
        double sinLongitudeDiff = Math.sin(LongitudeDiff/2);
        //taking square of the sin value of the Longitude difference divide by 2
        double sinLongitudeDiffSquare = Math.pow(sinLongitudeDiff,2.0);

        //Finding the velocity using formula
        double velocity = sinLatitudeDiffSquare + sinLongitudeDiffSquare * Math.cos(homeLatitudeRadians) * Math.cos(destinationLatitudeRadians);

        //Finding the distance using distance formula
        double distance = 2 * earthRadius * Math.atan2(Math.sqrt(velocity), Math.sqrt(1-velocity));

        //prints out the distance from home to destination after calculation
        System.out.println("");
        System.out.println("The distance from home to destination is " + distance + " miles");
        System.out.println("");

        //Asking user for whether they like to see the map
        System.out.println("Would you like to see a map of your trip?");

        //Reading the user provided response from the console and storing it into String variable
        String answer =  StdIn.readString();
        System.out.println("");
        
        //If answer is yes, it will provide link otherwise exit out of the program. 
        if (answer.equalsIgnoreCase("Yes"))
        {
            String url ="http://maps.googleapis.com/maps/api/staticmap?size=600x300&maptype=roadmap&markers=color:blue%7Clabel:A%7C" + homeLatitude + "," + homeLongitude + "&markers=color:red%7Clabel:B%7C" + destinationLatitude + "," + destinationLongitude + "&path=color:0x0000ff%7Cweight:5%7Cgeodesic:true%7C" + homeLatitude + "," + homeLongitude + "%7C" + destinationLatitude + "," + destinationLongitude;
            System.out.println("Right-click this link or copy it to your browser:");
            System.out.println("");
            System.out.println(url);
            //Displays the image in a drawing window.
            StdDraw.picture(0.5, 0.5,url);
        }
        else if (answer.equalsIgnoreCase("No"))
        {
            System.out.println("Thank you for using my program");
        }
        else
        {
            System.out.println("Sorry, your answer should be either 'YES' or 'NO'. Because of invalid response, the program will terminate.");
        }
    }//End of method Main
}//End of class Flight
