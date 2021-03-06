package es.uca.iw.sss.spring.backend.services;

import es.uca.iw.sss.spring.backend.entities.Shop;
import es.uca.iw.sss.spring.backend.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {

  @Autowired
  ShopRepository shopRepository;

  public ShopService(ShopRepository repo) {
    this.shopRepository = repo;
  }

  public Optional<Shop> findById(Long id) {
    return shopRepository.findById(id);
  }

  public Shop saveShop(Shop shop) {
    return shopRepository.save(shop);
  }

  public List<Shop> listShop() {
    return shopRepository.findAll();
  }

  public Long countShops() {
    return shopRepository.count();
  }

  public void deleteShop(Shop shop) {
    shopRepository.delete(shop);
  }

  public void create(Shop shop) {
    shopRepository.save(shop);
  }
}
