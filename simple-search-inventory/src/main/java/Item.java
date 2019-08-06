
public abstract class Item {

    private String productid;
    private String productName;
    private ItemCategory itemCategory;

    public Item(String productid, String productName, ItemCategory itemCategory) {
        this.productid=productid;
        this.productName=productName;
        this.itemCategory=itemCategory;
    }

}
