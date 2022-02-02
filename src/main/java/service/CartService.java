package service;

import entity.Cart;
import entity.Category;

import java.util.Set;

public interface CartService extends Service<Cart,Integer> {

void update(Cart cart);
}
