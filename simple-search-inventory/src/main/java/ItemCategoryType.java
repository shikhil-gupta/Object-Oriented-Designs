
public enum ItemCategoryType {
    MOBILE("mobile"),COMPUTER("computer");

    private String itemCategoryType;

    ItemCategoryType(String itemCategoryType) {
        this.itemCategoryType=itemCategoryType;
    }

    public String getItemCategoryType() {
        return itemCategoryType;
    }
}
