package com.example.dekoracje.service;

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
    UserTypeRepository utr;
    @Autowired
    TypeRepository tr;
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

    @PostConstruct
    public void init() {
        System.out.println();
        System.out.println("InitService");
        System.out.println("-----------");
        System.out.println();

        initUserType();
        initType();
        initUsers();
        initAddress();
        initSupplier();
        initProduct();
        initStock();
    }

    @Override
    public void initType() {
        System.out.println("initType");
        System.out.println("--------");
        System.out.println();

        List<Type> types = new ArrayList<>();
        types.add(new Type(0L, "Kwiaciarnia", false));
        types.add(new Type(0L, "Cukiernia", false));
        types.add(new Type(0L, "Sklep drewniany", false));
        types.add(new Type(0L, "Kwiaty", true));
        types.add(new Type(0L, "Wstążki", true));
        types.add(new Type(0L, "Papierowe", true));
        types.add(new Type(0L, "Drewniane", true));
        types.add(new Type(0L, "Metalowe", true));
        tr.saveAll(types);
    }

    @Override
    public void initUserType() {
        System.out.println("initUserType");
        System.out.println("------------");
        System.out.println();

        List<UserType> userTypes = new ArrayList<>();
        userTypes.add(new UserType(0L,"admin"));

        userTypes.add(new UserType(0L,"supplier"));
        userTypes.add(new UserType(0L,"customer"));
        utr.saveAll(userTypes);
    }

    @Override
    public void initUsers() {
        System.out.println("initUsers");
        System.out.println("---------");
        System.out.println();

        List<UserTable> users = new ArrayList<>();
        Optional<UserType> userType1 = utr.findById(1L);
        Optional<UserType> userType2 = utr.findById(2L);
        Optional<UserType> userType3 = utr.findById(3L);
        UserType admin = userType1.get();
        UserType supplier = userType2.get();
        UserType customer = userType3.get();
        users.add(new UserTable(0L,"Admin", "Admin", "admin@admin.com", admin));
        users.add(new UserTable(0L,"Suplerek", "Sup", "sklepmiesny@gmail.com", supplier));
        users.add(new UserTable(0L,"Customer1", "Cust", "cust@cust.com", customer));
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
                "123456789", "123456789", ut1.get()));
        sr.saveAll(suppliers);
    }

    @Override
    public void initProduct() {
        System.out.println("initProduct");
        System.out.println("-----------");
        System.out.println();

        List<Product> products = new ArrayList<>();
        Optional<Supplier> s1 = sr.findById(1L);
        Optional<Type> t1 = tr.findById(4L);
        Optional<Type> t2 = tr.findById(7L);
        Optional<Type> t3 = tr.findById(5L);

        products.add(new Product(0L, s1.get(), "Róża czerwona", 5.0, t1.get()));
        products.add(new Product(0L, s1.get(), "Róża biała", 6.50, t1.get()));
        products.add(new Product(0L, s1.get(), "Skrzynka drewniana 15x20x30 cm", 24.99, t2.get()));
        products.add(new Product(0L, s1.get(), "Wstążka czerwona 50m", 20.0, t3.get()));

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
        stocks.add(new Stock(0L, p4.get(), 2));

        str.saveAll(stocks);
    }

}
