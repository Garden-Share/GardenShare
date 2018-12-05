package re.gardensha.gardenshare;

import org.junit.Test;

public class EnterpriseGradeLooperTest {

    @Test
    public void testSum() {
        EnterpriseGradeLooper looper = EnterpriseGradeLooper.get();
        org.junit.Assert.assertEquals(10, looper.sum(4));
    }

    @Test
    public void testProduct() {
        EnterpriseGradeLooper looper = EnterpriseGradeLooper.get();
        org.junit.Assert.assertEquals(24, looper.product(4));
    }



}