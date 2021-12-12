import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public class MockableClock extends Clock {

    private Instant now;

    public MockableClock(Instant now) {
        this.now = now;
    }

    @Override
    public ZoneId getZone() {
        return ZoneId.systemDefault();
    }

    @Override
    public Clock withZone(ZoneId zone) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Instant instant() {
        return now;
    }

    public void setTime(Instant now) {
        this.now = now;
    }
}
