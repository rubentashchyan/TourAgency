package Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.Objects;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tour {
    private String tourID;
    @JsonProperty("name")
    private String tourName;
    private String destination;
    private int price;
    private int duration;
    private Date dateStart;
    private Date dateEnd;
    private Guide guide;

    @Override
    public String toString() {
        return "Tour{" +
                "tourID='" + tourID + '\'' +
                ", tourName='" + tourName + '\'' +
                ", destination='" + destination + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", guide=" + guide +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tour tour)) return false;
        return getPrice() == tour.getPrice() && getDuration() == tour.getDuration() && Objects.equals(getTourID(), tour.getTourID()) && Objects.equals(getTourName(), tour.getTourName()) && Objects.equals(getDestination(), tour.getDestination()) && Objects.equals(getDateStart(), tour.getDateStart()) && Objects.equals(getDateEnd(), tour.getDateEnd()) && Objects.equals(getGuide(), tour.getGuide());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTourID(), getTourName(), getDestination(), getPrice(), getDuration(), getDateStart(), getDateEnd(), getGuide());
    }
}
