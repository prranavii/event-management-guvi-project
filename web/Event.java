
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String date;
    private String time;
    private String venue;
    private Double ticketPrice;
    private Integer seatsAvailable;

    // Getters and Setters
}
