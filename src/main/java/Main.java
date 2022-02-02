import CustomException.OutOfRange;
import connection.PostgresConnection;
import entity.*;
import repository.jdbc.*;
import service.AdminService;
import service.serviceImplementation.*;

import java.sql.Connection;
import java.util.*;

public class Main {
  static Scanner input=new Scanner(System.in);
  static Connection connection= PostgresConnection.getInstance().getConnection();
  static AdminRepositoryImpl adminRepository=new AdminRepositoryImpl(connection);
  static CartRepositoryImpl cartRepository=new CartRepositoryImpl(connection);
  static CategoryRepositoryImpl categoryRepository=new CategoryRepositoryImpl(connection);
  static CustomerRepositoryImpl customerRepository=new CustomerRepositoryImpl(connection);
  static ProductRepositoryImpl productRepository=new ProductRepositoryImpl(connection);
  static AdminServiceImpl adminService=new AdminServiceImpl(adminRepository);
  static CategoryServiceImpl categoryService=new CategoryServiceImpl(categoryRepository);
  static ProductServiceImpl productService=new ProductServiceImpl(productRepository);
  static CustomerServiceImpl customerService=new CustomerServiceImpl(customerRepository);
  static CartServiceImpl cartService=new CartServiceImpl(cartRepository);
  static List<Product> cartProductList=new ArrayList<>();
    public static void main(String[] args) {
        boolean condition=true;
        while(condition){
            System.out.println("1-admin menu"+"\n"+"2-customer menu"+"\n"+"3-exit");
            try{
                Integer operator=input.nextInt();
                if(operator>3 || operator<1 ){
                    throw new OutOfRange("please enter something in range!");
                }
                switch (operator){
                    case 1:adminMenu();break;
                    case 2:customerMenu();break;
                    case 3:condition=false;break;
                }
            }catch (InputMismatchException e){
                System.out.println("please enter a number!");
                input.nextLine();
            }catch (OutOfRange e){
                System.out.println(e.getMessage());
            }

        }
    }
    public static void adminMenu(){
        boolean condition=true;
        while(condition){
            System.out.println("1-login"+"\n"+"2-exit");
            try{
                Integer operator=input.nextInt();
                if(operator>2 || operator<1 ){
                    throw new OutOfRange("please enter something in range!");
                }
                switch (operator){
                    case 1:adminLogin();break;
                    case 2:condition=false;break;
                }
            }catch (InputMismatchException e){
                System.out.println("please enter a number!");
                input.nextLine();
            }catch (OutOfRange e){
                System.out.println(e.getMessage());
            }
        }
    }
    public static void adminLogin(){
        System.out.println("please enter your username:");
        String username=input.next();
        System.out.println("please enter your password:");
        String password=input.next();
        Admin admin=new Admin(username,password);
        try {
            Integer id = adminService.findByUsernameAndPassword(admin);
            if (id > 0) {
             loggedInAdmin();
            }else{
                System.out.println("there is no acc with this username and password!");
            }
        }catch (InputMismatchException e){
            System.out.println("there is no acc with this username and password!");
            input.nextLine();
        }
    }
    public static void loggedInAdmin(){
        boolean condition=true;
        System.out.println("welcome!");
        while(condition) {
            System.out.println("1-adding a new admin" + "\n" + "2-adding a product" +  "\n" + "3-exit");
            try {
                Integer operator = input.nextInt();
                if(operator>4 || operator<1){
                    throw new OutOfRange("please enter something in range!");
                }
                switch (operator){
                    case 1:makingNewAdmin();break;
                    case 2:addingProduct();break;
                    case 3:condition=false;break;
                }
            }catch (InputMismatchException e){
                System.out.println("please enter a number!");
                input.nextLine();
            }catch (OutOfRange e){
                System.out.println(e.getMessage());
            }
        }
    }
    public static void makingNewAdmin(){
        try {
            System.out.println("please enter username:");
            String username = input.next();
            System.out.println("please enter password:");
            String password = input.next();
            Admin admin = new Admin(username, password);
            System.out.println("your id is:" + adminService.save(admin));
        }catch (InputMismatchException e){
            input.nextLine();
        }catch (NullPointerException e){

        }
    }
    public static void addingProduct(){
        boolean condition=true;
        while(condition) {
            System.out.println("1-adding a new product" + "\n" + "2-charging current products"+"\n"+"3-exit");
            try {
                int operator = input.nextInt();
                if (operator > 3 || operator < 1) {
                    throw new OutOfRange("please enter something in range!");
                }
                switch (operator ){
                    case 1:addingNewProduct();break;
                    case 2:addingFromCurrentProduct();break;
                    case 3:condition=false;break;
                }
            } catch (InputMismatchException e) {
                System.out.println("please enter a number!");
                input.nextLine();
            }catch (OutOfRange e){
                System.out.println(e.getMessage());
            }
        }
    }
    public static void addingNewProduct(){
        try{
            System.out.println("please enter name of the product:");
            String name=input.next();
            Category category=choosingCategory();
            System.out.println("please enter the price:");
            Integer price=input.nextInt();
            System.out.println("please enter the amount:");
            Integer amount=input.nextInt();
            Product product=new Product(name,price,amount,category);
            Integer id=productService.save(product);
            if(id>0){
                System.out.println("id of the product:"+id);
            }else {System.out.println("something wrong!");}
        }catch (InputMismatchException e){
            System.out.println("please enter a number!");
            input.nextLine();
        }
    }
    public static Category choosingCategory(){
        boolean condition=true;
        while(condition){
            System.out.println("1-adding a new category"+"\n"+"2-choosing from current categories");Category category=new Category();
try
     {
    Integer operator=input.nextInt();

    switch (operator){
        case 1: category= addingNewCategory();break;
        case 2:category=selectingFromCurrentCategory();break;
         }

        condition=false;
        return category;

     }catch (InputMismatchException e){
    System.out.println("please enter a number!");
    input.nextLine();
      }
        }
return null;
    }
    public static Category  addingNewCategory(){
        System.out.println("please enter name of the category");
        String name=input.next();
        List<Category> categoryList=categoryService.findAll();
        Category category1=new Category();
        category1.setName(name);
        for (Category category:categoryList
             ) {

            System.out.println("id:"+category.getId()+"name:"+category.getName()+" parent id:"+category.getParentCategory().getId());
        }
        System.out.println("please enter id of the category you want to make parent to your category (0 without parent) ");
        try{
            Integer id=input.nextInt();
            if(id==0){
                Category category2=new Category();
                category2.setId(0);
               category1.setParentCategory(category2);
              Integer catId= categoryService.save(category1);
              category1.setId(catId);
               return category1;
            }
            for (Category category:categoryList
            )
            {if(Objects.equals(category.getId(), id)){
                System.out.println(category.getName());
               category1.setParentCategory(category);
                Integer catId= categoryService.save(category1);
                category1.setId(catId);
               return category1;
            }

            }
        }catch (InputMismatchException e){
            System.out.println("please enter a number!");
            input.nextLine();
        }
        System.out.println("wrong id for parent! try again!");
        return null;
    }
    public static Category selectingFromCurrentCategory(){
        List<Category> categoryList=categoryService.findAll();
        for (Category category:categoryList
        ) {

            System.out.println("id:"+category.getId()+"name:"+category.getName()+" parent id:"+category.getParentCategory().getId());
        }
        System.out.println("please enter id of the category");
        try{
            Integer id=input.nextInt();
            for (Category category:categoryList) {
                if(category.getId()==id){
                return category;
                   }

            }
        }catch (InputMismatchException e){
            System.out.println("please enter a number!");
            input.nextLine();
        }
        System.out.println("there is no category with this id!");
        return null;
    }
    public static void addingFromCurrentProduct(){
        List<Product> products=productService.findAll();
        for (Product product:products
             ) {
            System.out.println("id:"+product.getId()+"name:"+product.getName()+"price:"+product.getPrice()+"number in stock:"+product.getStock());
        }
        try{
            System.out.println("please enter id of the product for recharge!");
            Integer id=input.nextInt();
            for (Product product:products) {
                if (product.getId()==id){
                    System.out.println("please enter number you want to add:");
                   Integer stock=input.nextInt();
                   product.setStock(product.getStock()+stock);
                   productService.update(product);
                   return;
                }
            }
           System.out.println("there is no product with this id!");
        }catch (InputMismatchException e){
            System.out.println("please enter a number!");
            input.nextLine();
        }
    }
    public static void customerMenu(){
 boolean condition=true;
  while(condition){
      System.out.println("1-login"+"\n"+"2-register"+"\n"+"3-exit");
      try {
          Integer operator=input.nextInt();
          if(operator<1 || operator>3){
              throw new OutOfRange("please enter something in range!");
          }
          switch (operator){
              case 1:customerLogin();break;
              case 2:customerRegister();break;
              case 3:condition=false;break;
          }
      }catch (InputMismatchException e){
          System.out.println("please enter a number!");
          input.nextLine();
      }catch (OutOfRange e){
          System.out.println(e.getMessage());
      }
    }

    }
    public static void customerLogin(){
        try {
            System.out.println("please enter your username");
            String username=input.next();
            System.out.println("please enter your password");
            String password=input.next();
            Customer customer=new Customer();
            customer.setUsername(username);
            customer.setPassword(password);
        Customer customer1=   customerService.findByUsernameAndPassword(customer);
        if(customer1.getId()>0 && customer1!=null){
            loggedInCustomer(customer1);
        }
        }catch (InputMismatchException e){
            System.out.println("wrong format!");
            input.nextLine();
        }catch (NullPointerException e){
            System.out.println("wrong username and password!");
        }
    }
    public static void customerRegister(){
        try {
            System.out.println("please enter your firstname");
            String firstname = input.next();
            System.out.println("please enter your lastname");
            String lastname=input.next();
            System.out.println("please enter your username");
            String username = input.next();
            System.out.println("please enter your password");
            String password = input.next();
            System.out.println("please enter your national code");
            Long nationalCode=input.nextLong();
            System.out.println("please enter your email");
            String email=input.next();
            System.out.println("please enter your phone number");
            Long phoneNumber=input.nextLong();
            Customer customer=new Customer(username,password,firstname,lastname,nationalCode,email,phoneNumber);
        Integer id=   customerService.save(customer);
           if(id>0 && id!=null){
               System.out.println("your id is:"+id);
           }else System.out.println("something is wrong!");
        }catch (InputMismatchException e){
            System.out.println("please enter a number!");
            input.nextLine();
        }
    }
    public static void loggedInCustomer(Customer customer){
        cartProductList=new ArrayList<>();
        boolean condition=true;Customer customer1=new Customer();
        while(condition){
            System.out.println("1-adding product to your cart"+"\n"+"2-deleting product from your cart"+"\n"+"3-submit your cart"+"\n"+"4-showing your cart"+"\n"+"5-exit");
        try{
            Integer operator=input.nextInt();
            if(operator>5 || operator<1){
                throw new OutOfRange("please enter something in range!");
            }
            switch (operator){
                case 1:customer1=addingProductToCart(customer); break;
                case 2:Cart cart=customer.getCart();
                cart.setProductList(cartProductList);
                customer.setCart(cart);
               customer1= deletingProductFromCart(customer);break;
                case 3:submitCart(customer);cartProductList=new ArrayList<>();Cart cart1=customer.getCart();cart1.setProductList(new ArrayList<>());customer.setCart(cart1);
                ;break;
                case 4:showingCart(cartProductList);break;
                case 5:condition=false;break;
            }
            cartProductList=customer1.getCart().getProductList();
            customer=customer1;

          }catch (InputMismatchException e){
            System.out.println("please enter a number!");
            input.nextLine();
          }catch (OutOfRange e){
            System.out.println(e.getMessage());
        }catch (NullPointerException e){
            System.out.println("you do not have a cart yet !");
        }
        }

    }
    public static Customer addingProductToCart(Customer customer){
        try{
    Category category= selectingFromCurrentCategory();
    List<Product> products=productService.findAllProductByCategory(category);
        for (Product product:products
             ) {
            System.out.println("id:"+product.getId()+" name:"+product.getName()+" price:"+product.getPrice()+" stock:"+product.getStock()+" category:"+product.getCategory().getId());
        }
        System.out.println("please enter id of the product:");

            Integer productId=input.nextInt();
            for (Product product:products
                 ) {
                if(Objects.equals(product.getId(), productId)){
                    if(customer.getCart()==null){
                        Cart cart=new Cart();
                        cart.setCostumer(customer);
                        List<Product> productList=new ArrayList<>();
                        cart.setProductList(productList);
                       Integer cartId= cartService.save(cart);
                       cart.setId(cartId);
                       customer.setCart(cart);
                    }
                   List<Product> productList= customer.getCart().getProductList();
                   productList.add(product);
                   customer.getCart().setProductList(productList);
                   return customer;
                }
            }
            System.out.println("there is no product with this id!");
        }catch (InputMismatchException e){
            System.out.println("please enter a number!");input.nextLine();
        }catch (NullPointerException e){

        }
        return customer;
    }
    public static Customer deletingProductFromCart(Customer customer){
      List<Product> products=customer.getCart().getProductList();
        for (Product product:products
             ) {
            System.out.println("id:"+product.getId());
        }
        System.out.println("enter id of the product you want to delete:");
        try{
            Integer productId=input.nextInt();
            Integer counter=0;
            for (int i = 0; i <products.size() ; i++) {


                if(Objects.equals(products.get(i).getId(), productId)){
                    products.remove(i);
                   Cart cart=customer.getCart();
                   cart.setProductList(products);
                   cart.setId(customer.getCart().getId());
                   customer.setCart(cart);
                   return customer;
                }
                counter++;
            }
            return customer;
        }catch (InputMismatchException e){
            System.out.println("please enter a number!");
            input.nextLine();
        }
        return customer;
    }
    public static void submitCart(Customer customer){
        try{
        System.out.println("please enter your address:");
        String address= input.next();
        System.out.println("please enter your phone number:");
        Long phoneNumber=input.nextLong();
        Cart cart=customer.getCart();
        cart.setAddress(address);
        cart.setPhoneNumber(phoneNumber);
        cart.setDone(true);
        customer.setCart(cart);
      List<Product> products=  productService.findAll();
      int counter=0;
        for (Product product1:products
             ) {
            for (Product product2:customer.getCart().getProductList()
                 ) {
                if(Objects.equals(product2.getId(), product1.getId())){
                    counter++;
                }
                if(counter>product1.getStock()){
                    System.out.println("there is not enough in the stock");
                    System.out.println(product1.getName()+" in stock:"+product1.getStock()+" your order:"+counter);
                    return;
                }else {
                    counter=0;
                }
            }
        }
        }catch (InputMismatchException e){
            System.out.println("please enter a number!");
            input.nextLine();
        }
        cartService.update(customer.getCart());

    }
    public static void showingCart(List<Product> cartProductList){
        Integer totalPrice=0;
        for (Product product:cartProductList
             ) {
            System.out.println("id:"+product.getId()+" name:"+product.getName()+" price:"+product.getPrice());
            totalPrice=totalPrice+product.getPrice();
        }
        System.out.println("total price:"+totalPrice);
    }

}
