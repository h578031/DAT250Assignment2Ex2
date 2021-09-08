import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.*;
import org.junit.Before;
import org.junit.Test;import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CreditCardTest {

    private static final String PERSISTENCE_UNIT_NAME = "creditCards";
    private EntityManagerFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        // Begin a new local transaction so that we can persist a new entity
        em.getTransaction().begin();

        // read the existing entries
        Query q = em.createQuery("select m from Person m");
        // Persons should be empty

        // do we have entries?
        boolean createNewEntries = (q.getResultList().size() == 0);

        // No, so lets create new entries
        if (createNewEntries) {
            assertTrue(q.getResultList().size() == 0);
            Address address = new Address();
            address.setStreet("Inndalsveien");
            address.setNumber(28);
            Person person = new Person();
            person.setName("Max Mustermann");


            List<Person> personList = new ArrayList<Person>();
            personList.add(person);
            address.setPersonList(personList);

            List<Address> addressList = new ArrayList<Address>();
            addressList.add(address);
            person.setAddress(addressList);

            em.persist(address);

            CreditCard creditCard1 = new CreditCard();
            CreditCard creditCard2 = new CreditCard();

            Pincode pincode = new Pincode();
            pincode.setPincode("123");
            pincode.setCount(1);

            Bank bank = new Bank();
            bank.setName("Pengebank");

            creditCard1.setNumber(12345);
            creditCard1.setBalance(5000);
            creditCard1.setLimit(10000);
            creditCard1.setPincode(pincode);
            creditCard1.setBank(bank);

            creditCard2.setNumber(123);
            creditCard2.setBalance(1);
            creditCard2.setLimit(2000);
            creditCard2.setPincode(pincode);
            creditCard2.setBank(bank);

            List<CreditCard> creditCardList = new ArrayList<CreditCard>();
            creditCardList.add(creditCard1);
            creditCardList.add(creditCard2);

            bank.setCardList(creditCardList);
            person.setCreditCards(creditCardList);

            em.persist(person);
            em.persist(creditCard1);
            em.persist(creditCard2);
            em.persist(bank);
            em.persist(pincode);
        }

        em.getTransaction().commit();
        em.close();

    }

    @Test
    public void checkPerson() {

        EntityManager em = factory.createEntityManager();

        Query query = em.createQuery("select p from Person p");

        assertTrue(query.getResultList().size() == 1);
    }
}
