package DAO;

import Model.OVChipkaart;
import Model.Product;


import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {
    public boolean save(Product product);

    public boolean delete(Product product);

    public boolean update(Product product);

    public List<Product> findByOVChipkaart(OVChipkaart ovchipkaart);

    public List<Product> findAll() throws SQLException;
}
