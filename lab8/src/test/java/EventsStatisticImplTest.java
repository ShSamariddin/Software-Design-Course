import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

class EventsStatisticImplTest {
    private MockableClock clock;
    private EventsStatistic eventStat;

    @BeforeEach
    void setup() {
        clock = new MockableClock(Instant.now());
        eventStat = new EventsStatisticImpl(clock);
    }

    @Test
    void testStatisticByName() {
        eventStat.incEvent("second");
        eventStat.incEvent("first");
        eventStat.incEvent("first");
        eventStat.incEvent("first");

        assertThat(eventStat.getEventStatisticByName("first")).isEqualTo(3.0 / 60);
    }

    @Test
    void testAfterHour() {
        eventStat.incEvent("a");
        eventStat.incEvent("b");
        clock.setTime(Instant.now().plus(1, ChronoUnit.HOURS));

        eventStat.incEvent("a");

        assertThat(eventStat.getEventStatisticByName("a")).isEqualTo(1.0 / 60);
    }

    @Test
    void testEmptyAfterHour() {
        eventStat.incEvent("a");
        clock.setTime(Instant.now().plus(1, ChronoUnit.HOURS));

        assertThat(eventStat.getEventStatisticByName("a")).isZero();
    }


    @Test
    void testBigData() {
        eventStat.incEvent("a");
        clock.setTime(Instant.now().plus(30, ChronoUnit.MINUTES));
        eventStat.incEvent("a");
        eventStat.incEvent("b");
        eventStat.incEvent("e");
        eventStat.incEvent("b");
        clock.setTime(Instant.now().plus(60, ChronoUnit.MINUTES));
        eventStat.incEvent("c");
        eventStat.incEvent("d");

        Map<String, Double> stats = eventStat.getAllEventStatistic();
        assertThat(stats).containsEntry("a", 1.0 / 60);
        assertThat(stats).containsEntry("b", 2.0 / 60);
        assertThat(stats).containsEntry("c", 1.0 / 60);
    }

    @Test
    void testNoneExciting() {
        eventStat.incEvent("a");
        eventStat.incEvent("b");
        clock.setTime(Instant.now().plus(1, ChronoUnit.HOURS));

        eventStat.incEvent("c");
        Map<String, Double> stats = eventStat.getAllEventStatistic();
        assertThat(stats).doesNotContainKey("a");
        assertThat(stats).containsEntry("c", 1.0 / 60);
    }

    @Test
    void testNotAddedElement() {
        assertThat(eventStat.getEventStatisticByName("empty")).isZero();
    }
}
