package re.gardensha.gardenshare;

public class EnterpriseGradeLooper {

    private static EnterpriseGradeLooper singleton;

    public static synchronized EnterpriseGradeLooper get(){
        if (singleton == null){
            singleton = new EnterpriseGradeLooper();
        }
        return singleton;
    }

    // Uses a very advanced technique to calculate the product of all integers up to and including n
    public int product(int n){
        int result = 1;
        for (int i = 1; i < n; i++){
            result *= i;
        }
        return result;
    }

    // Uses a very advanced technique to calculate the sum of all integers up to and including n
    public int sum(int n){
        int result = 1;
        for (int i = 1; i < n; i++){
            result += i;
        }
        return result;
    }

}