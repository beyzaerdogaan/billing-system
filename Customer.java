import java.util.ArrayList;
import java.time.LocalDate;

public class Customer {
    private String customerName, membershipType;
    private LocalDate shoppingDate;
    private String[] shoppingList;
    private ArrayList<Product> shoppingListArray= new ArrayList<>();
    public Customer(String customerName, String membershipType, LocalDate shoppingDate, String[] shoppingList) {
        this.customerName = customerName;
        this.membershipType = membershipType;
        this.shoppingDate = shoppingDate;
        this.shoppingList = shoppingList;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getShoppingDate() {
        return shoppingDate;
    }

    public void setShoppingDate(LocalDate shoppingDate) {
        this.shoppingDate = shoppingDate;
    }

    public void setShoppingList(String[] shoppingList) {
        this.shoppingList = shoppingList;
    }

    public void setShoppingListArray(ArrayList<Product> shoppingListArray) {
        this.shoppingListArray = shoppingListArray;
    }

    public ArrayList<Product> getShoppingListArray() {
        return shoppingListArray;
    }

    public String[] getShoppingList() {
        return shoppingList;
    }

    //find count of a product in customer's shopping list
    public int frequency(Product product) {
        int initialCount = 0;
        for (Product p : shoppingListArray) {
            if (product.getProductName().equals(p.getProductName())) {
                initialCount = initialCount + 1;
            }
        }
        return initialCount;
    }

    //create products for customer and add to customer's shopping list
    public void buyProduct(String productName, String membershipType, LocalDate shoppingDate, double price, int count) {
        for (int i = 0 ; i < count ; i++) {
            shoppingListArray.add(new Product(productName, membershipType, shoppingDate, price));
        }
        for (Product product : shoppingListArray) {
            int frequencyOfProduct = frequency(product);
            product.setCount(frequencyOfProduct);
        }
    }

    //determine the type of items belonging to the customer
    public ArrayList<Product> kindOfOwnedProducts() {
        ArrayList<String> productNameList= new ArrayList<>();
        ArrayList<Product> productList= new ArrayList<>();
        for (Product product : shoppingListArray) {
            if (!productNameList.contains(product.getProductName())) {
                productNameList.add(product.getProductName());
                productList.add(product);
            }
        }
        return productList;
    }
}
