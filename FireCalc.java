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
    public static void calculator(){
        getInputs();
        calculate();
    }

    public static void getInputs(){
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
        System.out.println("Planned spending when retired(to maintain current lifestyle: "+(Math.round((income-incomeSaved)*100)/100 )+"): ");
        retirementSpending = scanner.nextDouble();
        scanner.close();
    }

    public static void calculate() {
        double totalSaved = compoundLoop(yearsToGrow,income,incomeSaved);
        double neededToRetire = retirementSpending*25.0;
        if(totalSaved > neededToRetire){
            System.out.println("Congrats! You are on course to retire at "+retireAge+".");       
        }
        else{
            System.out.println("You are not on course to retire at "+retireAge+"! You need to either:");
            int additionalYears = 0;
            double additionalSavings = 0.0;
            double tempFund = 0;
            while(tempFund < neededToRetire){
                additionalYears++;
                tempFund = compoundLoop(yearsToGrow+additionalYears,income,incomeSaved);
            }
            System.out.println("Work an additional "+additionalYears+" years(To the age of "+(additionalYears+retireAge)+")");
            tempFund = 0;
            while(tempFund < neededToRetire){
                additionalSavings += .01;
                tempFund = compoundLoop(yearsToGrow,income,(incomeSaved+additionalSavings));
            }
            System.out.println("Save "+((incomeSaved+additionalSavings)*100)+"% instead of "+(incomeSaved*100)+"%");
        }
    }

    public static double compoundLoop(int totalYears, double tempIncome, double savingRate){
        investmentGrowthRate = .07;

        double cashTotal = principal;
        double incomeInvested = tempIncome*(savingRate);
        for(int year = 0 ; year < (totalYears) ; year++){
            for(int month = 0 ; month < 12 ; month++){
                cashTotal = cashTotal * (1+(investmentGrowthRate)/12);
                cashTotal += (incomeInvested/12);
            }
            tempIncome *= 1+(incomeGrowth);
            incomeInvested = tempIncome*(savingRate);
        }
        return cashTotal;
    }
}
