@Entity
public class evententity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String date;
    private String time;
    private String venue;
    private double ticketPrice;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private User organizer;

    public Event(String name, String date, String time, String venue, double ticketPrice, User organizer) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.ticketPrice = ticketPrice;
        this.organizer = organizer;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }
    public double getTicketPrice() { return ticketPrice; }
    public void setTicketPrice(double ticketPrice) { this.ticketPrice = ticketPrice; }
    public User getOrganizer() { return organizer; }
    public void setOrganizer(User organizer) { this.organizer = organizer; }
}
