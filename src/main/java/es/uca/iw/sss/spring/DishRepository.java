package es.uca.iw.sss.spring;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DishRepository extends JpaRepository<Dish,Long> {
    List<Dish> findByNameDishStartsWithIgnoreCase(String dish);
}