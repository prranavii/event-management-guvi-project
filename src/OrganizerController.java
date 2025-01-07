@Controller
public class OrganizerController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/organizer")
    public String showDashboard(Model model, @RequestParam("userId") Long userId) {
        User organizer = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Organizer not found"));
        model.addAttribute("events", eventService.getAllEvents());
        return "organizerDashboard";
    }

    @PostMapping("/organizer/addEvent")
    public String addEvent(@RequestParam String eventName,
                           @RequestParam String eventDate,
                           @RequestParam String eventTime,
                           @RequestParam String eventVenue,
                           @RequestParam double ticketPrice,
                           @RequestParam Long organizerId) {
        User organizer = userRepository.findById(organizerId).orElseThrow(() -> new RuntimeException("Organizer not found"));
        Event event = new Event(eventName, eventDate, eventTime, eventVenue, ticketPrice, organizer);
        eventService.addEvent(event, organizerId);
        return "redirect:/organizer?userId=" + organizerId; // Redirect to show events for the organizer
    }
}
