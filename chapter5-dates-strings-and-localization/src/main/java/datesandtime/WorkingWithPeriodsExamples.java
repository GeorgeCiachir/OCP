package datesandtime;

import java.time.*;

public class WorkingWithPeriodsExamples {

    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime.toEpochSecond(ZoneOffset.ofHours(3)));
        System.out.println(dateTime.toEpochSecond(ZoneOffset.UTC));
        System.out.println(3600*3);
        System.out.println(dateTime.toEpochSecond(ZoneOffset.ofHours(3)) - dateTime.toEpochSecond(ZoneOffset.UTC));

        System.out.println();
        System.out.println("****************");
        System.out.println();

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dateTime2 = LocalDateTime.of(date, time);
        System.out.println(dateTime2.toEpochSecond(ZoneOffset.UTC));
        System.out.println(date.toEpochDay() * 24 * 3600 + time.getHour()*3600 + time.getMinute()*60 + time.getSecond());

        System.out.println();
        System.out.println("****************");
        System.out.println();

        Period period = Period.of(1, 1, 0);
        System.out.println(LocalDate.now().plus(period));
        System.out.println(LocalDateTime.now().plus(period));

        System.out.println();
        System.out.println("****************");
        System.out.println();

        System.out.println(LocalDateTime.now().plus(Period.ofDays(2)));
        System.out.println(LocalDateTime.now().plus(Duration.ofDays(2)));
        //This means that the Duration is a TemporalAmount that stores it's value in seconds and it cannot be applied on LocalDate
        // because it doesn't contain a time
//        System.out.println(LocalDate.now().plus(Duration.ofDays(2))); //UnsupportedTemporalTypeException: Unsupported unit: Seconds
//        System.out.println(LocalDate.now().plus(Duration.ofHours(1))); // UnsupportedTemporalTypeException: Unsupported unit: Seconds

        // Similarly a period of day can't be added to a LocalTime
//        System.out.println(LocalTime.now().plus(Period.ofMonths(1))); // UnsupportedTemporalTypeException: Unsupported unit: Months

        System.out.println();
        System.out.println("****************");
        System.out.println();
        System.out.println("Period");

        System.out.println(Period.ofYears(1).plusDays(33));
        System.out.println(Period.ofWeeks(49));

        System.out.println();
        System.out.println("****************");
        System.out.println();
        System.out.println("Duration");

        System.out.println(Duration.ofDays(2));
        System.out.println(Duration.ofHours(9));
        System.out.println(Duration.ofMinutes(8));
        System.out.println(Duration.ofSeconds(22));
        System.out.println(Duration.ofMillis(2));

        System.out.println(ZonedDateTime.now().toInstant());
        System.out.println(LocalDateTime.now().toInstant(OffsetDateTime.now().getOffset()));
        System.out.println(LocalDateTime.now().toInstant(ZoneOffset.ofHours(4)));
    }
}
