package br.example.db.DAO;

import br.example.model.Coffee;
import br.example.db.CoffeeDAO;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CoffeeDAOImpl implements CoffeeDAO {
    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    @Override
    @Transactional
    public Coffee findById(Long id) {
        return hibernateTemplate.get(Coffee.class, id);
    }

    @Override
    @Transactional
    public List<Coffee> findAll() {

        List<Coffee> coffee = hibernateTemplate.loadAll(Coffee.class);

        for(int i = 0; i < coffee.size(); i++) {
            if(!coffee.get(i).isDisabled()) {
                coffee.remove(i);
            }
        }

        return coffee;

    }
}
