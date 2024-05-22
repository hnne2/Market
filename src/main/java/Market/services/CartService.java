package Market.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import Market.models.Cart;
import Market.repositories.CartRepository;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class CartService {
    private final CartRepository cartRepository;
    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
    public List<Cart> findAll(){
        return cartRepository.findAll();
    }
    public Cart findOne(int id){
      Optional<Cart> foundCard = cartRepository.findById(id);
      return foundCard.orElse(null);
    }
    @Transactional
    public void save(Cart cart){
        cartRepository.save(cart);
    }
    @Transactional
    public void update(int id,Cart updatedcart){
        updatedcart.setId(id);
        cartRepository.save(updatedcart);
    }
    @Transactional
    public void delete(int id){
        cartRepository.deleteById(id);
    }

    public List<Cart> getPersonLikeBy(String chars){
        return cartRepository.findAllPersonLikeUsername(chars);
    }
}
