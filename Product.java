import java.time.LocalDate;

public class Product {
    private String productName, membershipType;
    private LocalDate startDate, endDate, shoppingDate;
    private double price;
    private int count;

    public Product(String productName, String membershipType, LocalDate startDate, LocalDate endDate, double price) {
        this.productName = productName;
        this.membershipType = membershipType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public Product(String productName, String membershipType, LocalDate shoppingDate, double price) {
        this.productName = productName;
        this.membershipType = membershipType;
        this.shoppingDate = shoppingDate;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public String getProductName() {
        return productName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getShoppingDate() {
        return shoppingDate;
    }

    public int getCount() {
        return count;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setShoppingDate(LocalDate shoppingDate) {
        this.shoppingDate = shoppingDate;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
