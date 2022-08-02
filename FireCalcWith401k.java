public class FireCalcWith401k {
    public static void main(String[] args) {
        calculate(40,100000);
    }

    public static double calculate(int ageYouRetire, double plannedAnnualSpending) {
        double amountToSave = 0;
        double realAnnualSpending = 0;
        int yearsToLast = 60 - ageYouRetire;
        while(realAnnualSpending < plannedAnnualSpending){
            amountToSave += 100000;
            double testSavings = amountToSave;
            for (int year = 0 ; year < yearsToLast ; year++){
                for (int month = 0 ; month < 4 ; month++){
                    testSavings *= 1.03;
                    
                }
            }
        }


        return amountToSave;
    }
}
