import java.util.Scanner;

public class FireCalc {

    static int startAge=0;
    static int retireAge=0;
    static double principal=0;
    static double income=0;
    static double incomeGrowth=0;
    static double incomeSaved=0;
    static double retirementSpending=0;
    static double investmentGrowthRate=0;
    static double costOfLivingGrowthRate=0;
    static double match401k=0;
    static final double deathAge = 80;
    static int yearsToGrow;;

    public static void main(String[] args) {
        calculator();
    }
    private static void calculator(){
        getInputs();
        calculate();
    }

    private static void getInputs(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Start Age: ");
        startAge = scanner.nextInt();
        System.out.println("Retirement Age: ");
        retireAge = scanner.nextInt();
        yearsToGrow = retireAge - startAge;
        System.out.println("Initial Savings: ");
        principal = scanner.nextDouble();
        System.out.println("Current Income: ");
        income = scanner.nextDouble();
        System.out.println("Income Growth(Average is 6%): ");
        incomeGrowth = (scanner.nextDouble()/100);
        System.out.println("Income saved(%): ");
        incomeSaved = (scanner.nextDouble()/100);
        System.out.println("Planned spending when retired(to maintain current lifestyle: "+(Math.round(((income-(income*incomeSaved))*100)/100))+"): ");
        retirementSpending = scanner.nextDouble();
        System.out.println("Excepted investment growth rate(Market average is 10%): ");
        investmentGrowthRate = (scanner.nextDouble()/100);
        System.out.println("Expected Cost of Living growth rate(Average is 3%): ");
        costOfLivingGrowthRate = (scanner.nextDouble()/100);
        scanner.close();
    }

    private static void calculate() {
        double[] loopResponse = compoundLoop(yearsToGrow,income,incomeSaved,retirementSpending);
        double totalSaved = loopResponse[0];
        double neededToRetire = loopResponse[1]*25.0;
        if(totalSaved > neededToRetire){
            System.out.println("Congrats! You are on course to retire at "+retireAge+".");       
        }
        else{
            double adjustedRetirementSpending = (totalSaved/25)/(Math.pow(1+costOfLivingGrowthRate, yearsToGrow));
            System.out.println("You are not on course to retire at "+retireAge+"! You need to either:");
            System.out.println("Live off of $"+Math.round(((totalSaved/25.0)*100)/100)+" a year(the equivalent of $"+Math.round(((adjustedRetirementSpending)*100)/100 )+" today)");
            int additionalYears = 0;
            double additionalSavings = 0.0;
            double tempFund = 0;
            while(tempFund < neededToRetire){
                additionalYears++;
                loopResponse = compoundLoop((yearsToGrow+additionalYears),income,incomeSaved,retirementSpending);
                tempFund = loopResponse[0];
                neededToRetire = loopResponse[1]*25.0;
            }
            System.out.println("Work an additional "+additionalYears+" years(To the age of "+(additionalYears+retireAge)+")");
            tempFund = 0;
            while(tempFund < neededToRetire){
                additionalSavings += .01;
                loopResponse = compoundLoop(yearsToGrow,income,(incomeSaved+additionalSavings),retirementSpending);
                tempFund = loopResponse[0];
                neededToRetire = loopResponse[1]*25.0;
            }
            System.out.println("Save "+(double)(Math.round(((incomeSaved+additionalSavings)*100)*10))/10+"% instead of "+(incomeSaved*100)+"%");
        }
    }

    private static double[] compoundLoop(int totalYears, double tempIncome, double savingRate, double plannedRetirementSpending){
        double cashTotal = principal;
        double incomeInvested = tempIncome*(savingRate);
        for(int year = 0 ; year < (totalYears) ; year++){
            for(int month = 0 ; month < 12 ; month++){
                cashTotal = cashTotal * (1+(investmentGrowthRate)/12);
                cashTotal += (incomeInvested/12);
            }
            tempIncome *= 1+(incomeGrowth);
            incomeInvested = tempIncome*(savingRate);
            plannedRetirementSpending *= (1+costOfLivingGrowthRate);
        }
        double[] loopData = {cashTotal,plannedRetirementSpending};
        return loopData;
    }


}
