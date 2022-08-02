public class FireCalcWith401k {
    public static void main(String[] args) {
        System.out.println(calculate(40,100000));
    }

    public static double calculate(int ageYouRetire, double plannedAnnualSpending) {
        double amountToSave = 0;
        int yearsWillLast = 0;
        int yearsToLast = 60 - ageYouRetire;
        while(yearsWillLast < yearsToLast){
            yearsWillLast = 0;
            amountToSave += 100000;
            double testSavings = amountToSave;
            while(testSavings > 0){
                for (int month = 0 ; month < 12 ; month++){
                    testSavings *= 1+(.10/12);
                    testSavings -= plannedAnnualSpending/12;
                }
                yearsWillLast++;
            }
            
        }


        return amountToSave;
    }
}
