package entity;

import java.util.Set;

public class Customer extends User {

    private String firstName;
    private String lastName;
    private Long nationalCode;
    private String email;
    private Long phoneNumber;
    private Cart cart;

    public Customer(String username, String password, String firstName, String lastName, Long nationalCode, String email, Long phoneNumber, Cart cart) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cart = cart;
    }

    public Customer(String username, String password) {
        super(username, password);
    }
}
