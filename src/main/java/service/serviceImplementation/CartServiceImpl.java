package service.serviceImplementation;

import repository.jdbc.CartRepositoryImpl;
import service.CartService;

public class CartServiceImpl implements CartService {
    private CartRepositoryImpl cartRepository=new CartRepositoryImpl();
    public void CartServiceImpl(CartRepositoryImpl repository) {
        this.cartRepository=repository;
    }
    @Override
    public Object save(Object entity) {
        return null;
    }
}
