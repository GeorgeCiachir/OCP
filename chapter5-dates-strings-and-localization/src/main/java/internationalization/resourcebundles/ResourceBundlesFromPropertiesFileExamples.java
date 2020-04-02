package internationalization.resourcebundles;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class ResourceBundlesFromPropertiesFileExamples {

    private static final String ZOO_BUNDLE = "Zoo";

    public static void main(String[] args) {
        Locale us = new Locale("en", "US");
        Locale fr = new Locale("fr", "FR");
        Locale ro = new Locale("ro", "FRO");

        ResourceBundle bundle = ResourceBundle.getBundle(ZOO_BUNDLE, us);
        System.out.println(bundle.getString("hello"));
        System.out.println(bundle.getString("open"));

        System.out.println();

        Locale.setDefault(new Locale("xx"));
        bundle = ResourceBundle.getBundle(ZOO_BUNDLE); // if the Locale is not provided, it defaults to the default Locale
        System.out.println(bundle.getString("hello"));
        System.out.println(bundle.getString("open"));

        System.out.println();

        bundle = ResourceBundle.getBundle(ZOO_BUNDLE, fr);
        System.out.println(bundle.getString("hello"));
        System.out.println(bundle.getString("open"));

        System.out.println();

        bundle = ResourceBundle.getBundle(ZOO_BUNDLE, ro);
        System.out.println(bundle.getString("hello"));
        System.out.println(bundle.getString("open"));


        System.out.println();

        ResourceBundle resourceBundle = ResourceBundle.getBundle(ZOO_BUNDLE, ro);
        Properties props = resourceBundle
                .keySet()
                .stream()
                .reduce(
                        new Properties(),
                        (properties, key) -> {
                            properties.put(key, resourceBundle.getString(key));
                            return properties;
                        },
                        (first, second) -> first
                );
        System.out.println(props);

        Properties propsAgain = new Properties();
        resourceBundle
                .keySet()
                .forEach(key -> propsAgain.put(key, resourceBundle.getString(key)));
        System.out.println(propsAgain);

        System.out.println(propsAgain.getProperty("Gogu", "Property does not exist"));


        System.out.println("******* If the requested resource bundle doesn't exist, the default one is returned  *********");

        bundle = ResourceBundle.getBundle(ZOO_BUNDLE, new Locale("eeee"));
        System.out.println(bundle.getString("hello"));

        System.out.println("******* formatting *********");
        bundle = ResourceBundle.getBundle(ZOO_BUNDLE, Locale.US);
        String unformatted = bundle.getString("hello");
        System.out.println(unformatted);
        String formatted = MessageFormat.format(unformatted, "George", "Marti");
        System.out.println(formatted);
    }


}
