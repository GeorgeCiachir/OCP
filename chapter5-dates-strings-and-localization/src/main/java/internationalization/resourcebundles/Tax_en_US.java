package internationalization.resourcebundles;

import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.ResourceBundle;

public class Tax_en_US extends ListResourceBundle {

    public static void main(String[] args) {
        ResourceBundle rs = ResourceBundle.getBundle("resourcebundles.Tax", Locale.US);
        System.out.println(rs.keySet());
    }

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"hello2", "Hello"},
                {"open2", "The zoo is open"}
        };
    }
}
