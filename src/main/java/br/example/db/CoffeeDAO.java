/**
 *
 */
package br.example.db;

import java.util.List;

import br.example.model.Coffee;

public interface CoffeeDAO {
    public Coffee findById(Long id);
    public List<Coffee> findAll();
}
