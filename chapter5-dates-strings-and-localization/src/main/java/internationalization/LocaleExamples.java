package internationalization;

import java.util.Locale;

public class LocaleExamples {

    public static void main(String[] args) {
        System.out.println(Locale.getDefault());

        System.out.println(Locale.GERMAN);
        System.out.println(Locale.GERMANY);

        System.out.println(new Locale("my_language", "my country"));
        System.out.println(new Locale("MY_LANGUAGE", "my country"));

        Locale newLocale = new Locale.Builder()
                .setLanguage("LANGUAGE")
                .setRegion("es")
                .build();
        System.out.println(newLocale);

        System.out.println();
        System.out.println("********** change the default locale *********");
        System.out.println();

        Locale romanianLocale = new Locale.Builder()
                .setLanguage("ro")
                .setRegion("RO")
                .build();
        Locale.setDefault(romanianLocale);
        System.out.println(Locale.getDefault());
    }
}
