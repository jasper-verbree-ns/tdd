import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class StubEventSourceRepository implements EventSourceRepository {
  private final List<Event> eventList;

  public StubEventSourceRepository() {
    this(new ArrayList<Event>());
  }
  
  public StubEventSourceRepository(final List<Event> events) {
      this.eventList = events;
  }

  @Override
  public void load(final AggregateRoot aggregateRoot) {
      for (final Event event : this.eventList)
          aggregateRoot.apply(event);  
  }
  
  @Override
  public void save(final UUID aggregateRootId, final Event event) {
      this.eventList.add(event);
  }

  public List<Event> getEventsFor(final UUID aggregateRootId) {
      return Collections.unmodifiableList(this.eventList);
  }
}