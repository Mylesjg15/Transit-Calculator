/*
    Author: Myles Garcia
    Date: 6-2-2020
    Description: 
    Program takes into account 3 types of traveling fares in NYC 
    and takes your input on how many days you're staying and how 
    many rides you want to purchase and chooses the best fare option 
    based on your preferences.
*/

import java.util.Scanner;

class transitCalculator {

    protected int numberOfDays;
    protected int numberOfRides;

    //Constructor
    protected transitCalculator(int numDays, int numRides) {
        this.numberOfDays = numDays;
        this.numberOfRides = numRides;
    }

    //Calculates the price per a ride for only the sinlge ride option
    protected double unlimited1Price(int numDays, int numRides) {
        double dailyFare = 2.75;

        // System.out.println("Your overall fare per ride for the single-day package is: " + dailyFare);
        return dailyFare;
    }

    //Calculates the price per a ride for only the 7-day ride option
    protected double unlimited7Price(int numDays, int numRides) {
        double overallPrice;
        double temp;
        double weeklyFare = 33.00;
        double weeklyDays = 7.00;

        temp = numDays / weeklyDays;   //This determines the factor on how many 7-day passes it needs to apply
        overallPrice = (Math.ceil(temp) * weeklyFare) / numRides;  //Rounds up the multiplication factor producted with 7 days then divided by number of rides

        // System.out.println("Your overall fare per ride for the 7-day package is: " + overallPrice);
        return overallPrice;
    }

    //Calculates the price per a ride for only the 30-day ride option
    protected double unlimited30Price(int numDays, int numRides) {
        double overallPrice;
        double temp;
        double monthlyFare = 127.00;
        double monthlyDays = 30.00;

        temp = numDays / monthlyDays;   //This determines the factor on how many 30-day passes it needs to apply
        overallPrice = (Math.ceil(temp) * monthlyFare) / numRides;  //Rounds up the multiplication factor producted with 30 days then divided by number of rides

        // System.out.println("Your overall fare per ride for the 30-day package is: " + overallPrice);
        return overallPrice;
    }

    //Returns all the prices per a ride for all 3 packages
    protected void getRidePrices(int numDays, int numRides) {
        unlimited1Price(numDays, numRides);
        unlimited7Price(numDays, numRides);
        unlimited30Price(numDays, numRides);
    }

    //This method will determine the best option to take based on the numDays and numRides you enter
    public void getBestFare(int numDays, int numRides) {
        //Calls the price per ride methods
        double perDaily = unlimited1Price(numDays, numRides);
        double perWeekly = unlimited7Price(numDays, numRides);
        double perMonthly = unlimited30Price(numDays, numRides);
        
        //Sets the
        double bestOption = 1000000.00;
        String choice = "";

        double dailyFare = numRides * perDaily;
        double weeklyFare = numRides * perWeekly;
        double monthlyFare = numRides * perMonthly;

        double[] fares = {dailyFare, weeklyFare, monthlyFare};

        for (double fare : fares) {
            if (fare < bestOption) {
                bestOption = fare;
            } 
        }

        for (int i = 0; i < 3; i++) {
            if (fares[i] == bestOption) {
                switch (i) {
                    case 0:
                        choice = "you should get the 'Pay-per-ride' option at $" + Math.round(perDaily * 100.00) / 100.00 + " per ride.";
                        break;
                    case 1:
                        choice = "you should get the '7-day' option at $" + Math.round(perWeekly * 100.00) / 100.00 + " per ride.";
                        break;
                    case 2:
                        choice = "you should get the '30-day' option at $" + Math.round(perMonthly * 100.00) / 100.00 + " per ride.";
                        break;
                    default:
                        choice = "you should get the 'Pay-per-ride' option at $" + Math.round(perDaily * 100.00) / 100.00 + " per ride.";
                        break;
                }
            }
        }

        System.out.println("\nIf you're staying for " + numDays + " days and you plan to pay for " + numRides + " rides then " + choice + "\n");
    }

    //Main Function
    public static void main(String[] args) {
        //Create the variable that stores the users input for the fare specifications
        Scanner userEntry = new Scanner(System.in);

        System.out.println("Enter how many days you wish to rent:");
        int numDays = userEntry.nextInt();
        System.out.println("Enter how many rides you wish to take during your time:");
        int numRides = userEntry.nextInt();
        userEntry.close();
        
        //Instantiate variable that uses transitCalculator methods
        transitCalculator yourFare = new transitCalculator(numDays, numRides);
        
        //Call the methods in the class
        yourFare.getBestFare(numDays, numRides);
    }
}