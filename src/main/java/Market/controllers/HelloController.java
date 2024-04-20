package Market.controllers;

import Market.models.Cart;
import Market.models.Person;
import Market.security.PersonDetails;
import Market.services.CartService;
import Market.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class HelloController {
    Person curentPercon = new Person();

    private final CartService cartService;
    private final PersonDetailsService personDetailsService;
    @Autowired
    public HelloController(CartService cartService, PersonDetailsService personDetailsService) {
        this.cartService = cartService;
        this.personDetailsService = personDetailsService;
    }

    @GetMapping("/main")
    public String sayHello(Model model) {



        List<Cart> carts = cartService.findAll();
        model.addAttribute("carts",carts);
        for (int i = 0; i < carts.size(); i++) {
            System.out.println(carts.get(i).getItemName());
        }
        return "/main";
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
      curentPercon=personDetails.getPerson();
        System.out.println(personDetails.getPerson());

        return "hello";
    }
    @PostMapping("/add-to-cart/{cartId}") // путь для добавления в корзину
    public String addToCart(@PathVariable("cartId") int cartId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        curentPercon = personDetailsService.findById(personDetails.getPerson().getId());
        String newItemInbasket = String.valueOf(cartId);
        String newBasket;
        System.out.println("добавление в корзину"+curentPercon.getUsername());
        if (curentPercon.getBasket()!=null){
            if (!curentPercon.getBasket().contains(newItemInbasket)){
                newBasket = curentPercon.getBasket() +"," +String.valueOf(cartId);
                personDetailsService.update(curentPercon.getId(),new Person(curentPercon.getUsername(),curentPercon.getPassword(),newBasket));

            }

        }else { personDetailsService.update(curentPercon.getId(),new Person(curentPercon.getUsername(),curentPercon.getPassword(),newItemInbasket));}

        return "redirect:/basket";
    }
    @GetMapping("/basket")
    public String basket(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        curentPercon = personDetailsService.findById(personDetails.getPerson().getId());
        List<Cart> cartList = new ArrayList<>();
       String[] people = curentPercon.getBasket().split(",");
        for (int i = 0; i < people.length; i++) {

            cartList.add(cartService.findOne(Integer.parseInt(people[i])));
            System.out.println("добавил карточку в корзину"+cartList.get(i));
        }

        model.addAttribute("basket",cartList);
        return "/basket";
    }

}
