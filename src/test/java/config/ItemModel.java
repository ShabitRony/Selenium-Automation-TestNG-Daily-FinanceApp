package config;

public class ItemModel {
    private String itemName;
    private String amount;
    private String purchaseDate;
    private String month;
    private String remarks;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public ItemModel(){
        this.itemName = itemName;
        this.amount = amount;
        this.purchaseDate=purchaseDate;
        this.month=month;
        this.remarks=remarks;
    }
}
