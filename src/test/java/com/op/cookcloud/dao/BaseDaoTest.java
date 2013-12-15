package com.op.cookcloud.dao;

import com.op.cookcloud.dao.impl.PersonDao;
import com.op.cookcloud.dao.impl.ProductDao;
import com.op.cookcloud.dao.impl.ShopDao;
import com.op.cookcloud.dao.impl.ShopListDao;
import com.op.cookcloud.model.base.Person;
import com.op.cookcloud.model.base.Product;
import com.op.cookcloud.model.base.Shop;
import com.op.cookcloud.model.base.ShopList;
import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: alex3
 * Date: 14.12.13
 * Time: 22:27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext-test.xml", "classpath:/hibernateContext-test.xml"})
@TransactionConfiguration
@Transactional
public abstract class BaseDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    private static final Logger LOG = Logger.getLogger(BaseDaoTest.class);

    @Autowired
    protected ShopListDao shopListDao;

    @Autowired
    protected ProductDao productDao;

    @Autowired
    protected ShopDao shopDao;

    @Autowired
    protected PersonDao personDao;

    public Person createPerson(){
        LOG.debug("createPerson");
        Person person = new Person();
        person.setFirstName("Alex");
        person.setLastName("Ivanov");
        person.setEmail("TestEmail@gmail.com");
        person.setGender("M");
        personDao.saveUser(person);
        LOG.debug(person.getId());
        return person;
    }

    public ShopList createShopList() {
        Person person = createPerson();
        ShopList shopList = new ShopList();
        shopList.setNote("aaa");
        shopList.setPerson(person);
        shopListDao.save(shopList);
        return shopList;
    }

    public Shop createShop() {
        Shop shop = new Shop();
        shop.setName("testShop");
        shopDao.save(shop);
        return shop;
    }

    public Product createProduct() {
        Product product = new Product();
        product.setName("лезвия");
        product.setId(1);
        product.setShoplist(createShopList());
        productDao.saveOrUpdate(product);
        return product;
    }


}
