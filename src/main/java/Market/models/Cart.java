package Market.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Имя не должно быть пустым")
    @Column(name = "itemname")
    private String itemName;
    @NotEmpty(message = "Цена не должно быть пустой")
    @Column(name = "itemprice")
    private String itemPrice;
    @NotEmpty(message = "Ссылка не должна быть пустой")
    @Column(name = "piclink")
    private String PicLink;
    public Cart() {
    }
    public Cart(String itemName,String picLink, String itemPrice) {
        this.itemName=itemName;
        this.PicLink=picLink;
        this.itemPrice=itemPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getPicLink() {
        return PicLink;
    }

    public void setPicLink(String picLink) {
        PicLink = picLink;
    }
}
