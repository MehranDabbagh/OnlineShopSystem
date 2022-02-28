package connection;


import entity.Customer;
import lombok.var;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactorySingleton {

    private SessionFactorySingleton() {}

    private static class LazyHolder {
        static SessionFactory INSTANCE;

        static {
            var registry = new StandardServiceRegistryBuilder()
                    .configure() // goes and fetches configuration from hibernate.cfg.xml
                    .build();

            // registry is useful for creating SessionFactory
            // SessionFactory is a heavyweight object.
            // SessionFactory is thread safe.
            // SessionFactory is immutable.
            INSTANCE = new MetadataSources(registry)
                    .addAnnotatedClass(Customer.class)
                    .buildMetadata()
                    .buildSessionFactory();
        }
    }

    public static SessionFactory getInstance() {
        return LazyHolder.INSTANCE;
    }
}
