import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class Market {
    private String [] shoppingListArray;
    private String[] priceListArray;
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();

    public Market(String shoppingList, String priceList) {
        Path filePath = Paths.get(shoppingList);
        String[] lines = ReadFromFile.readFile(filePath.toAbsolutePath().toString());
        Path filePath1 = Paths.get(priceList);
        String[] lines1 = ReadFromFile.readFile(filePath1.toAbsolutePath().toString());
        shoppingListArray = lines;
        priceListArray = lines1;
    }

    public String[] getPriceListArray() {
        return priceListArray;
    }

    public String[] getShoppingListArray() {
        return shoppingListArray;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public void setPriceListArray(String[] priceListArray) {
        this.priceListArray = priceListArray;
    }

    public void setShoppingListArray(String[] shoppingListArray) {
        this.shoppingListArray = shoppingListArray;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    //convert string to date object
    public LocalDate dateConverter(String date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
        LocalDate newDate = LocalDate.parse(date, format);
        return newDate;
    }

    //create new customers according to shopping list
    public void createCustomers() {
        for (String line : shoppingListArray) {
            String[] customerInfo = line.split("\t");
            String date = customerInfo[2];
            LocalDate shoppingDate = dateConverter(date);
            String[] shoppingList = Arrays.copyOfRange(customerInfo, 3, customerInfo.length);
            customers.add(new Customer(customerInfo[0], customerInfo[1], shoppingDate, shoppingList));
        }
    }

    //create sample objects according to price list
    public void createProducts() {
        for (String line : priceListArray) {
            String[] priceList = line.split("\t");
            LocalDate startDate = dateConverter(priceList[2]);
            LocalDate endDate = dateConverter(priceList[3]);
            double price = Double.parseDouble(priceList[4]);
            products.add(new Product(priceList[0], priceList[1], startDate, endDate, price));
        }
    }

    //create products for customer according to price list
    public void shop() {
        createCustomers();
        createProducts();
        for (Customer customer : customers) {
            for (int i = 0 ; i < customer.getShoppingList().length ; i++) {
                for (Product product : products) {
                    if (customer.getShoppingList()[i].equals(product.getProductName()) &&
                            customer.getMembershipType().equals(product.getMembershipType()) &&
                            customer.getShoppingDate().isAfter(product.getStartDate()) &&
                            customer.getShoppingDate().isBefore(product.getEndDate())) {
                        customer.buyProduct(product.getProductName(), product.getMembershipType(),
                                customer.getShoppingDate(),product.getPrice(),
                                Integer.parseInt(customer.getShoppingList()[i+1]));
                    }
                }
            }
        }
    }

    //calculate total amount and display the bill
    public void bill() {
        shop();
        for (Customer customer : customers) {
            System.out.println("---" + customer.getCustomerName() + "---");
            double total = 0;
            for (Product product : customer.kindOfOwnedProducts()) {
                System.out.println(product.getProductName() + "\t" + product.getPrice() + "\t" + product.getCount()
                        + "\t" + (product.getPrice() * product.getCount()));
                total = total + (product.getPrice() * product.getCount());
            }
            System.out.println("Total" + "\t" + total);
        }
    }
}
