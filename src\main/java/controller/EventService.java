@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    // Add event with organizer
    public void addEvent(Event event, Long organizerId) {
        User organizer = userRepository.findById(organizerId).orElseThrow(() -> new RuntimeException("Organizer not found"));
        event.setOrganizer(organizer);
        eventRepository.save(event);
    }

    // Get all events for attendees
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}

