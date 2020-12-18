package DAO;

import Model.OVChipkaart;
import Model.Product;


import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {
    boolean save(Product product);

    boolean delete(Product product);

    boolean update(Product product);

    Product findByOVChipkaart(OVChipkaart ovchipkaart);

    List<Product> findAll() throws SQLException;
}
