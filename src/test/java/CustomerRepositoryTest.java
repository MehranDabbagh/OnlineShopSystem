import connection.SessionFactorySingleton;
import entity.Customer;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.impl.CustomerRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerRepositoryTest {
    private CustomerRepositoryImpl customerRepository=new CustomerRepositoryImpl();
    private Customer customer;

    @BeforeAll
    public static void setup() {
        SessionFactory sessionFactory= SessionFactorySingleton.getInstance();

    }
    @AfterEach
    public void cleanUp()  {
        List<Customer> customerList=customerRepository.findAll();
        if(customerList!=null) {
            for (Customer customer : customerList
            ) {
                customerRepository.deleteById(customer.getId());
            }
        }
    }
    @Test
    public void CreateTest() {
        // Arrange -->
        Customer customer = new Customer(0l,"s","s","s","s",5l,"s",0l,0l);

        // Act
        Long customerId = customerRepository.save(customer);

        // Assert
        Customer loadedCustomer = customerRepository.findById(customerId);
        assertAll(
                () -> assertNotNull(customerId),
                () -> assertNotNull(loadedCustomer),
                () -> assertEquals(customerId, loadedCustomer.getId()),
                () -> assertEquals("s", loadedCustomer.getFirstName()),
                () -> assertEquals("s", loadedCustomer.getLastName()),
                () -> assertEquals("s", loadedCustomer.getUsername()),
                () -> assertEquals("s", loadedCustomer.getEmail()),
                () ->  assertEquals(5l,loadedCustomer.getNationalCode()),
                () ->  assertEquals(0l,loadedCustomer.getPhoneNumber()),
                () ->  assertEquals(0l,loadedCustomer.getCartId())
        );
    }
    @Test
    public void findAllTest(){
        // Arrange
        customerRepository.save(new Customer(0l,"s","s","s","s",5l,"s",0l,0l));
        customerRepository.save(new Customer(1l,"s","s","s","s",5l,"s",0l,0l));
        customerRepository.save(new Customer(2l,"s","s","s","s",5l,"s",0l,0l));

        // Act
        List<Customer> customers = customerRepository.findAll();

        // Assert
        assertThat(customers).hasSize(3);

    }
    @Test
    public void updateTest()  {
        // Arrange
        Long customerId =customerRepository.save(new Customer(0l,"s","s","s","s",5l,"s",0l,0l));

        Customer customer= customerRepository.findById(customerId);
        // Act
        customer.setUsername("newOne");
        customerRepository.update(customer);
        // Assert
        Customer loadedCustomer = customerRepository.findById(customerId);
        assertThat(loadedCustomer.getUsername()).isEqualTo("newOne");
        assertThat(loadedCustomer.getId()).isEqualTo(customerId);
    }
    @Test
    public void deleteTest() throws SQLException {
        // Arrange
        Customer customer = new Customer(0l,"s","s","s","s",5l,"s",0l,0l);

        customerRepository.save(customer);
        // Act
        customerRepository.deleteById(customer.getId());
        // Assert
        assertThat(customerRepository.findAll()).isEmpty();
    }

}
