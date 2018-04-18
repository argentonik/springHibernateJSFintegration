package br.example.db.DAO;

import br.example.db.OrderDAO;
import br.example.model.Coffee;
import br.example.model.OrderFull;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hp_laptop on 18.04.18.
 */
public class OrderDAOImpl implements OrderDAO{

    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    @Override
    @Transactional
    public OrderFull create(OrderFull order) {
        return hibernateTemplate.get(OrderFull.class, hibernateTemplate.save(order));
    }

    @Override
    @Transactional
    public OrderFull findById(Long id) {
        return hibernateTemplate.get(OrderFull.class, id);
    }

    @Override
    @Transactional
    public List<OrderFull> findAll() {
        return hibernateTemplate.loadAll(OrderFull.class);

    }
}
