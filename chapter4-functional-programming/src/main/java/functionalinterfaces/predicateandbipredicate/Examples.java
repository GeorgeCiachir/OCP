package functionalinterfaces.predicateandbipredicate;

import java.util.function.Predicate;

public class Examples {

    private static final String EGG = "egg";
    private static final String BROWN = "brown";
    private static final String SMALL = "small";

    public static void main(String[] args) {

        Predicate<String> isEgg = aValue -> aValue.contains(EGG);
        Predicate<String> isBrown = aValue -> aValue.contains(BROWN);
        Predicate<String> isSmall = aValue -> aValue.contains(SMALL);

        Predicate<String> smallBrownEggCondition = isEgg.and(isBrown).and(isSmall);

        String testedValue = "this is a small brown egg";
        System.out.println(smallBrownEggCondition.test(testedValue));

        String other = "this is a small egg";
        System.out.println(smallBrownEggCondition.test(other));

        Predicate<String> atLeastASmallEggCondition = smallBrownEggCondition.or(isEgg.and(isSmall));
        System.out.println(atLeastASmallEggCondition.test(other));

    }

}
