package primitivestreams;

import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;

public class SummarizingStatisticsExamples {

    public static void main(String[] args) {
        IntStream intStream;

        intStream = IntStream.of(0, 2, 3, 4, 5, 6);
//        intStream = IntStream.rangeClosed(0, 6);

        IntSummaryStatistics statistics = intStream
                .summaryStatistics();
        int range = statistics.getMax() - statistics.getMin();

        System.out.println(range);

    }
}
