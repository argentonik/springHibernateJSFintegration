package br.example.db.DAO;

import br.example.db.OrderPositionDAO;
import br.example.model.OrderFull;
import br.example.model.OrderPosition;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
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

    @Override
    @Transactional
    public int getNumberOfOrders() throws DataAccessException {
        return DataAccessUtils.intResult(hibernateTemplate.find("select count(*) from OrderPosition"));
    }

    @Override
    @Transactional
    public List<OrderPosition> getAllByPage(final int pageSize, final int first) {
        return (List<OrderPosition>) hibernateTemplate.executeWithNativeSession(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from OrderPosition");
                query.setMaxResults(pageSize);
                query.setFirstResult(first);
                return query.list();
            }
        });
    }
}
