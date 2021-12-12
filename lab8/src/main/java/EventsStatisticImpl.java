import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class EventsStatisticImpl implements EventsStatistic {
    private final Map<String, List<Instant>> stats;
    private final Clock clock;
    private static final int STATISTICS_TIMEFRAME_HOURS = 1;
    private static final int MINUTES_IN_HOUR = 60;

    public EventsStatisticImpl(Clock clock) {
        this.clock = clock;
        this.stats = new HashMap<>();
    }

    @Override
    public void incEvent(String name) {
        if (!stats.containsKey(name)) {
            stats.put(name, new ArrayList<>());
        }
        stats.get(name).add(clock.instant());
    }

    @Override
    public double getEventStatisticByName(String name) {
        removeOldStatistic();
        if (!stats.containsKey(name)) {
            return 0;
        }
        return (double) stats.get(name).size() / 60.0;
    }

    @Override
    public Map<String, Double> getAllEventStatistic() {
        removeOldStatistic();
        return stats.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> getEventStatisticByName(entry.getKey())
                ));
    }

    @Override
    public void printStatistic() {
        Map<String, Double> stats = getAllEventStatistic();
        for (String name : stats.keySet()) {
            System.out.println("Event=" + name + ", RPM=" + stats.get(name));
        }
    }

    private void removeOldStatistic() {
        Instant hourAgo = clock.instant().minus(1L, ChronoUnit.HOURS);

        for (String name : stats.keySet()) {
            List<Instant> recentInstants = stats.get(name).stream()
                    .filter(instant -> instant.isAfter(hourAgo))
                    .collect(Collectors.toList());
            stats.put(name, recentInstants);
        }
        stats.entrySet().removeIf(entry -> entry.getValue().isEmpty());
    }
}