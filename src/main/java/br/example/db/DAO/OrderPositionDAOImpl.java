package br.example.db.DAO;

import br.example.db.OrderPositionDAO;
import br.example.model.OrderFull;
import br.example.model.OrderPosition;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hp_laptop on 18.04.18.
 */
public class OrderPositionDAOImpl implements OrderPositionDAO{

    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    @Override
    @Transactional
    public OrderPosition create(OrderPosition orderPosition) {
        return hibernateTemplate.get(OrderPosition.class, hibernateTemplate.save(orderPosition));
    }

    @Override
    @Transactional
    public OrderPosition findById(Long id) {
        return hibernateTemplate.get(OrderPosition.class, id);
    }

    @Override
    @Transactional
    public List<OrderPosition> findAll() {
        return hibernateTemplate.loadAll(OrderPosition.class);

    }
}
