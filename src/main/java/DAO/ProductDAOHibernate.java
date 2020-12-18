package DAO;

import Model.Adres;
import Model.OVChipkaart;
import Model.Product;
import Model.Reiziger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.PersistenceException;
import java.sql.SQLException;
import java.util.List;

public class ProductDAOHibernate implements ProductDAO {
    private SessionFactory sessionfactory;

    @Override
    public boolean save(Product product) {
        Session session = sessionfactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(product);
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
            session.close();
        }
    }

    @Override
    public boolean delete(Product product) {
        Session session = sessionfactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(product);
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
            session.close();
        }
    }

    @Override
    public boolean update(Product product) {
        Session session = sessionfactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(product);
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
            session.close();
        }
    }

    @Override
    public Product findByOVChipkaart(OVChipkaart ovchipkaart) {
        Session session = sessionfactory.openSession();
        Product product = session.get(OVChipkaart.class, ovchipkaart.getReiziger_id());
        session.close();
        return product;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        Session session = sessionfactory.openSession();
        List<Product> product = session.createQuery("from Product").list();
        session.close();

        return product;
    }
}
