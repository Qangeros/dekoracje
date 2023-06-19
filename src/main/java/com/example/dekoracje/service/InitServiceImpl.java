package com.example.dekoracje.service;

import com.example.dekoracje.authentication.AuthenticationResponse;
import com.example.dekoracje.authentication.AuthenticationService;
import com.example.dekoracje.authentication.RegisterRequest;
import com.example.dekoracje.model.entity.*;
import com.example.dekoracje.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InitServiceImpl implements InitService {
    @Autowired
    UserRepository ur;
    @Autowired
    AddressRepository ar;
    @Autowired
    SupplierRepository sr;
    @Autowired
    ProductRepository pr;
    @Autowired
    StockRepository str;
    @Autowired
    CustomerRepository cr;
    @Autowired
    AuthenticationService as;

    @PostConstruct
    public void init() {
        System.out.println();
        System.out.println("InitService");
        System.out.println("-----------");
        System.out.println();

//        initUsers();
//        initAddress();
//        initSupplier();
//        initProduct();
//        initStock();
//        initCustomer();
        //initAdminAccount();
    }

    public void initAdminAccount() {
        RegisterRequest request = new RegisterRequest();
        request.setUsername("Admin");
        request.setPassword("Admin");
        request.setEmail("admin@admin.pl");
        request.setRole(21376669);
        as.register(request);
        System.out.println("Konto admina utworzone chyba lol");
    }

    @Override
    public void initUsers() {
        System.out.println("initUsers");
        System.out.println("---------");
        System.out.println();

        List<UserTable> users = new ArrayList<>();
        users.add(new UserTable(0L,"Admin", "Admin", "admin@admin.com", UserRole.ADMIN));
        users.add(new UserTable(0L,"Suplerek", "Sup", "sklepmiesny@gmail.com", UserRole.SUPPLIER));
        users.add(new UserTable(0L,"Customer1", "Cust", "cust@cust.com", UserRole.CUSTOMER));
        ur.saveAll(users);
    }

    @Override
    public void initAddress() {
        System.out.println("initAddress");
        System.out.println("-----------");
        System.out.println();

        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address(0L, "Kolejowa 1", "Warszawa", "01-001", false));
        addresses.add(new Address(0L, "Szkolna 17", "Białystok", "01-002", false));
        addresses.add(new Address(0L, "Białobłocka 1/2", "Białe Błota", "86-222", true));
        addresses.add(new Address(0L, "Gdańska 213", "Bydgoszcz", "85-332", false));
        addresses.add(new Address(0L, "Toruńska 7", "Bydgoszcz", "85-222", true));
        ar.saveAll(addresses);
    }

    @Override
    public void initSupplier() {
        System.out.println("initSupplier");
        System.out.println("------------");
        System.out.println();

        List<Supplier> suppliers = new ArrayList<>();
        Optional<UserTable> ut1 = ur.findById(2L);
        Optional<Address> a1 = ar.findById(1L);

        suppliers.add(new Supplier(0L, "Sklep z rzeczami", a1.get(), "sklepik@wp.pl",
                "123456789", "222222", ut1.get()));
        sr.saveAll(suppliers);
    }

    @Override
    public void initProduct() {
        System.out.println("initProduct");
        System.out.println("-----------");
        System.out.println();

        List<Product> products = new ArrayList<>();
        Optional<Supplier> s1 = sr.findById(1L);

        products.add(new Product(0L, s1.get(), "Róża czerwona", 5.0, "Kwiaty"));
        products.add(new Product(0L, s1.get(), "Róża biała", 6.50, "Kwiaty"));
        products.add(new Product(0L, s1.get(), "Skrzynka drewniana 15x20x30 cm", 24.99, "Drewno"));
        products.add(new Product(0L, s1.get(), "Wstążka czerwona 50m", 20.0, "Dekoracje"));

        pr.saveAll(products);

    }

    @Override
    public void initStock(){
        System.out.println("initStock");
        System.out.println("-----------");
        System.out.println();

        List<Stock> stocks = new ArrayList<>();
        Optional<Product> p1 = pr.findById(1L);
        Optional<Product> p2 = pr.findById(2L);
        Optional<Product> p3 = pr.findById(3L);
        Optional<Product> p4 = pr.findById(4L);

        stocks.add(new Stock(0L, p1.get(), 200));
        stocks.add(new Stock(0L, p2.get(), 150));
        stocks.add(new Stock(0L, p3.get(), 25));
        //stocks.add(new Stock(0L, p4.get(), 2));

        str.saveAll(stocks);
    }

    @Override
    public void initCustomer(){
        System.out.println("initCustomer");
        System.out.println("-----------");
        System.out.println();

        List<Customer> customers = new ArrayList<>();
        Optional<UserTable> ut1 = ur.findById(3L);
        Optional<Address> a1 = ar.findById(2L);

        customers.add(new Customer(0L, "Jan", "Kowalski",
                "test@gmail.com", "123123723", a1.get(), ut1.get()));

        cr.saveAll(customers);
    }

}
