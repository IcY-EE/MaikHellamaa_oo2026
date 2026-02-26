package ee.maik.veebipood.repository; // Pane tähele: "repository", mitte "respository"

import ee.maik.veebipood.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// repository --> andmebaasiga suhtlemiseks. tema sees on kõik funktsioonid, mida on võimalik
//              andmebaasiga teha.

public interface ProductRepository extends JpaRepository<Product,Long> {
}