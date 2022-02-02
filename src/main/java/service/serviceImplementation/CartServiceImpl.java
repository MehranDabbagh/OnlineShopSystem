package service.serviceImplementation;

import entity.Cart;
import repository.jdbc.CartRepositoryImpl;
import service.CartService;

public class CartServiceImpl implements CartService {
    private CartRepositoryImpl cartRepository=new CartRepositoryImpl();
    public void CartServiceImpl(CartRepositoryImpl repository) {
        this.cartRepository=repository;
    }

    @Override
    public Integer save(Cart entity) {
      return   cartRepository.save(entity);
    }

    @Override
    public void update(Cart cart) {
        cartRepository.update(cart);
    }
}
