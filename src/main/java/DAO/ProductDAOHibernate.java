package DAO;

import Model.OVChipkaart;
import Model.Product;
import Model.Reiziger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;
import java.sql.SQLException;
import java.util.List;

public class ProductDAOHibernate implements ProductDAO {
    private SessionFactory sessionfactory;
    private Session ssn;

    public ProductDAOHibernate(Session ssn){
        this.ssn = ssn;
    }

    @Override
    public boolean save(Product product) {
        Session ssn = sessionfactory.openSession();
        Transaction transaction = ssn.beginTransaction();

        try {
            ssn.save(product);
            transaction.commit();

            return true;
        } catch (PersistenceException exception) {
            transaction.rollback();
            Throwable cause = exception.getCause();
            while (cause != null) {
                if (cause instanceof ConstraintViolationException)
                    return false;
                cause = cause.getCause();
            }
            throw exception;
        } finally {
            ssn.close();
        }
    }

    @Override
    public boolean delete(Product product) {
        Session ssn = sessionfactory.openSession();
        Transaction transaction = ssn.beginTransaction();

        try {
            ssn.delete(product);
            transaction.commit();

            return true;
        } catch (PersistenceException exception) {
            transaction.rollback();
            Throwable cause = exception.getCause();
            while (cause != null) {
                if (cause instanceof ConstraintViolationException)
                    return false;
                cause = cause.getCause();
            }
            throw exception;
        } finally {
            ssn.close();
        }
    }

    @Override
    public boolean update(Product product) {
        Session ssn = sessionfactory.openSession();
        Transaction transaction = ssn.beginTransaction();

        try {
            ssn.update(product);
            transaction.commit();

            return true;
        } catch (PersistenceException exception) {
            transaction.rollback();
            Throwable cause = exception.getCause();
            while (cause != null) {
                if (cause instanceof ConstraintViolationException)
                    return false;
                cause = cause.getCause();
            }
            throw exception;
        } finally {
            ssn.close();
        }
    }

    @Override
    public List<Product> findByOVChipkaart(OVChipkaart ovchipkaart) {
        Session ssn = sessionfactory.openSession();
        Query query = ssn.createQuery("FROM Product where OVChipkaart = : reiziger");
        query.setParameter("reiziger", ovchipkaart);
        List<Product> product = query.list();
        ssn.close();
        return product;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        Session ssn = sessionfactory.openSession();
        List<Product> product = ssn.createQuery("from Product").list();
        ssn.close();

        return product;
    }
}
