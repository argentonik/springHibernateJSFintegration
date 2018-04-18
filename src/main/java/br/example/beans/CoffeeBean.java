/**
 *
 */
package br.example.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.example.model.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.example.db.CoffeeDAO;

@ManagedBean
@RequestScoped
public class CoffeeBean extends SpringBeanAutowiringSupport {

    @Autowired
    private CoffeeDAO coffeeService;

    public List<Coffee> getItems()
    {
        return this.coffeeService.findAll();
    }

    public Coffee getItem(long id)
    {
        return this.coffeeService.findById(id);
    }
}
