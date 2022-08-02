public class test {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        double initVal = 100;
        double yearsToGrow = 10;
        double costOfLivingGrowthRate = .03;
        double finalVal;
        
        for (int i = 0 ; i < yearsToGrow ; i++){
            initVal *= (1+costOfLivingGrowthRate);
        }
        finalVal = initVal;
        System.out.println(finalVal);

        System.out.println((finalVal)/(Math.pow(1+costOfLivingGrowthRate, yearsToGrow)));
    }

    
}
