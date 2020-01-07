package es.uca.iw.sss.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.logging.Logger;

@Service
public class RestaurantService {


    private RestaurantRepository restaurantRepository;
    private static RestaurantService stance;
    private static final Logger LOGGER = Logger.getLogger(RestaurantService.class.getName());

    @Autowired
    public RestaurantService(RestaurantRepository repo) {
        super();
        this.restaurantRepository = repo;
    }

    /**
     * @return a reference to an example facade for Customer objects.
     */
    public static RestaurantService getInstance() {

        return stance;
    }

}


