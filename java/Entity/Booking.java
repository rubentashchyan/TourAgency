package Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Booking {

    private String tourID;
    private Date date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking booking)) return false;
        return Objects.equals(getTourID(), booking.getTourID()) && Objects.equals(getDate(), booking.getDate());
    }

    @Override
    public String toString() {
        return "Booking{" +
                "tourID='" + tourID + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTourID(), getDate());
    }
}
