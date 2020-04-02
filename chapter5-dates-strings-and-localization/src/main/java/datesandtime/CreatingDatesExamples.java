package datesandtime;

import java.time.*;

public class CreatingDatesExamples {

    public static void main(String[] args) {
        now();
        basicDateTimeCreation();
        aBitMoreComplex();
        findTheTimeZones();

    }

    private static void findTheTimeZones() {
        System.out.println();
        System.out.println();
        System.out.println("*****************findTheTimeZones*****************");
        System.out.println();
        System.out.println();
        ZoneId.getAvailableZoneIds()
                .stream()
                .filter(zone -> zone.contains("Europe"))
                .forEach(System.out::println);
    }

    private static void aBitMoreComplex() {
        System.out.println();
        System.out.println();
        System.out.println("***************getAvailableZoneIds*******************");
        System.out.println();
        System.out.println();

        ZoneOffset localZoneOffset = OffsetDateTime.now().getOffset();


        Instant instant1 = ZonedDateTime.now().toInstant();
        Instant instant2 = LocalDateTime.now().toInstant(localZoneOffset);
        // my local offset is +3, so this gets my LocalDate, which is 3 hours ahead of UTC
        // after this, instead of readjusting to the UTC time by substracting my current offset, I substract 8 hours
        // basically this sets the time to 5 hours before UTC (UTC-03:00)
        ZoneOffset zoneOffsetAheadFiveHoursFromLocal = ZoneOffset.ofHours(8) ;
        Instant instant3 = LocalDateTime.now().toInstant(zoneOffsetAheadFiveHoursFromLocal);

        System.out.println(ZonedDateTime.ofInstant(instant1, localZoneOffset));
        System.out.println(ZonedDateTime.ofInstant(instant2, localZoneOffset));
        System.out.println(ZonedDateTime.ofInstant(instant3, localZoneOffset));
    }

    private static void basicDateTimeCreation() {
        System.out.println();
        System.out.println();
        System.out.println("****************basicDateTimeCreation******************");
        System.out.println();
        System.out.println();

        LocalDate date1 = LocalDate.of(1982, Month.NOVEMBER, 27);
        LocalDate date2 = LocalDate.of(1980, 7, 10);

        LocalTime time1 = LocalTime.of(1, 10);
        LocalTime time2 = LocalTime.of(1, 10, 12);
        LocalTime time3 = LocalTime.of(1, 10, 12, 22);

        LocalDateTime dateTime1 = LocalDateTime.of(1982, Month.NOVEMBER, 27, 1, 10, 12, 22);
        LocalDateTime dateTime2 = LocalDateTime.of(date1, time3);

        ZoneId zone = ZoneId.of("US/Eastern");
        ZonedDateTime zoned1 = ZonedDateTime.of(1982, Month.NOVEMBER.getValue(), 27, 1, 10, 12, 22, zone);
        ZonedDateTime zoned2 = ZonedDateTime.of(date1, time1, zone);
        ZonedDateTime zoned3 = ZonedDateTime.of(dateTime1, zone);
    }

    private static void now() {
        System.out.println("****************now******************");
        System.out.println();
        System.out.println();
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());
        System.out.println(ZonedDateTime.now());
    }
}
