package es.uca.iw.sss.spring.backend.services;

import es.uca.iw.sss.spring.backend.entities.Ship;
import es.uca.iw.sss.spring.backend.repositories.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class ShipService {
    private ShipRepository repoShip;
    private final HashMap<Long, Ship> ships = new HashMap<>();
    private static ShipService shipService;

    @Autowired
    public ShipService(ShipRepository repoShip)
    {
        this.repoShip = repoShip;
    }

    public synchronized void saveShip(Ship ship){
        repoShip.save(ship);
    }
    public synchronized List<Ship> findAll() {
        return repoShip.findAll();
    }

    public Ship findById(long id) {
        return repoShip.findById(id);
    }

    public Ship findByLicensePlate(String licensePlate)
    {
        return repoShip.findByLicensePlate(licensePlate);
    }

    public static ShipService getInstance(ShipRepository repoShip) {
        if (shipService == null) {
            shipService = new ShipService(repoShip);
            shipService.findAll();
        }
        return shipService;
    }

    public Ship getLicensePlate(String licensePlate) { return repoShip.findByLicensePlate(licensePlate); }

    public void create(Ship ship)
    {
        repoShip.save(ship);
    }
}
