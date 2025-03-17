import java.util.*;
import java.util.stream.Collectors;
class Product {
    String name;
    String category;
    double price;
    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }
}
public class ProductProcessor {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", "Electronics", 1000),
            new Product("Phone", "Electronics", 700),
            new Product("TV", "Electronics", 1200),
            new Product("Shirt", "Clothing", 50),
            new Product("Jeans", "Clothing", 80),
            new Product("Refrigerator", "Appliances", 900)
        );
        Map<String, List<Product>> groupedByCategory = products.stream()
            .collect(Collectors.groupingBy(p -> p.category));
        Map<String, Optional<Product>> mostExpensiveByCategory = products.stream()
            .collect(Collectors.groupingBy(
                p -> p.category, 
                Collectors.maxBy(Comparator.comparingDouble(p -> p.price))
            ));
        double averagePrice = products.stream()
            .mapToDouble(p -> p.price)
            .average()
            .orElse(0);
        System.out.println("Products grouped by category:");
        groupedByCategory.forEach((category, productList) -> {
            System.out.println(category + ": " + productList.stream()
                .map(p -> p.name)
                .collect(Collectors.joining(", ")));
        });
        System.out.println("\nMost expensive product in each category:");
        mostExpensiveByCategory.forEach((category, product) -> 
            System.out.println(category + ": " + product.map(p -> p.name + " ($" + p.price + ")").orElse("None")));
        System.out.println("\nAverage price of all products: $" + averagePrice);
    }
}
