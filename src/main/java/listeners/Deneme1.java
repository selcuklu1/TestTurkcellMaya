package listeners;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.List;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 19.02.2018
 * Açıklama:
 */
public class Deneme1 implements IMethodInterceptor {
    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        System.out.println("AAAAAAAAAAAAA");
        System.out.println(context.getCurrentXmlTest().toXml(""));
        System.out.println("AAAAAAAAAAAAA");

        return null;
    }
}
