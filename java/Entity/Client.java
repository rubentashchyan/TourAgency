package Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
public class Client {
    private String clientID;
    private String name;
    private String email;
    private String phone;
    private  ArrayList <Booking> bookings;

    public Client(String clientID, String name, String email, String phone, ArrayList<Booking> bookings) {
        this.clientID = clientID;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.bookings = bookings;
    }


    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        return Objects.equals(getClientID(), client.getClientID()) && Objects.equals(getName(), client.getName()) && Objects.equals(getEmail(), client.getEmail()) && Objects.equals(getPhone(), client.getPhone()) && Objects.equals(getBookings(), client.getBookings());
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientID='" + clientID + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", bookings=" + bookings +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClientID(), getName(), getEmail(), getPhone(), getBookings());
    }
}
