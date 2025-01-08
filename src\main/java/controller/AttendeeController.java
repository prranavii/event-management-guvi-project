@Controller
public class AttendeeController {

    @Autowired
    private EventService eventService;

    @GetMapping("/attendee")
    public String showEvents(Model model) {
        List<Event> events = eventService.getAllEvents(); // Retrieve all events
        model.addAttribute("events", events);
        return "attendeeDashboard"; // Display events on the attendee's dashboard
    }
}
