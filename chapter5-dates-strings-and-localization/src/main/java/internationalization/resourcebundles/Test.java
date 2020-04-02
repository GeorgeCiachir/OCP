package internationalization.resourcebundles;

import java.util.Locale;
import java.util.ResourceBundle;

public class Test {

    public static void main(String[] args) {
        ResourceBundle rs = ResourceBundle.getBundle("resourcebundles.Tax", Locale.US);
        System.out.println(rs.keySet());
    }
}
