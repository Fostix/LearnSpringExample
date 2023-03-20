package learn.springframework.spring6restmvc.repositories;

import learn.springframework.spring6restmvc.entities.Beer;
import learn.springframework.spring6restmvc.entities.BeerOrder;
import learn.springframework.spring6restmvc.entities.BeerOrderShipment;
import learn.springframework.spring6restmvc.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest // less complex test
@SpringBootTest // more complex test
class BeerOrderRepositoryTest {
    @Autowired
    BeerOrderRepository beerOrderRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    BeerRepository beerRepository;

    Customer testCustomer;

    Beer testBeer;

    @BeforeEach
    void setUp() {
        testCustomer = customerRepository.findAll().get(0);
        testBeer = beerRepository.findAll().get(0);
    }

    @Transactional // if use repository method!
    @Test
    void testBeerOrders() {
        BeerOrder beerOrder = BeerOrder.builder()
                .customerRef("Test order")
                .customer(testCustomer)
                .beerOrderShipment(BeerOrderShipment.builder()
                		.trackingNumber("1234df")
                		.build())
                .build();

        BeerOrder saveBeerOrder = beerOrderRepository.save(beerOrder);

        System.out.println(saveBeerOrder.getCustomerRef());
//        demonstration
//        System.out.println(beerOrderRepository.count());
//        System.out.println(customerRepository.count());
//        System.out.println(beerRepository.count());
//        System.out.println(testCustomer.getName());
//        System.out.println(testBeer.getBeerName());
    }
}