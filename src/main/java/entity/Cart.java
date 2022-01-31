package entity;

import java.util.Set;

public class Cart extends BaseEntity {
    private String address;
    private Long phoneNumber;
    private boolean done;
    private Customer costumer;

    public Cart(String address, Long phoneNumber, boolean done, Customer costumer) {
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.done = done;
        this.costumer = costumer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Customer getCostumer() {
        return costumer;
    }

    public void setCostumer(Customer costumer) {
        this.costumer = costumer;
    }
}
