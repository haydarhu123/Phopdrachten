package DAO;

import Model.Reiziger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;
import java.sql.SQLException;
import java.util.List;

public class ReizigerDAOHibernate implements ReizigerDAO {
    private SessionFactory sessionfactory;
    private Session ssn;

    public ReizigerDAOHibernate(Session ssn) {
        this.ssn = ssn;
    }

    @Override
    public boolean save(Reiziger reiziger) throws SQLException {
        Session ssn = sessionfactory.openSession();
        Transaction transaction = ssn.beginTransaction();

        try {
            ssn.save(reiziger);
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
    public boolean delete(Reiziger reiziger) throws SQLException {
        Session ssn = sessionfactory.openSession();
        Transaction transaction = ssn.beginTransaction();

        try {
            ssn.delete(reiziger);
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
    public boolean update(Reiziger reiziger) throws SQLException {
        Session ssn = sessionfactory.openSession();
        Transaction transaction = ssn.beginTransaction();

        try {
            ssn.update(reiziger);
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
    public Reiziger findById(int id) {
        Session ssn = sessionfactory.openSession();
        Reiziger reiziger = ssn.get(Reiziger.class, id);
        ssn.close();
        return reiziger;
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) {
        Session ssn = sessionfactory.openSession();
        Query query = ssn.createQuery("FROM Reiziger where geboortedatum = :datum");
        query.setParameter("datum", datum);
        List<Reiziger> reiziger = query.list();
        ssn.close();
        return reiziger;
    }

    @Override
    public List<Reiziger> findAll() throws SQLException {
        Session ssn = sessionfactory.openSession();
        List<Reiziger> reiziger = ssn.createQuery("from Reiziger ").list();
        ssn.close();

        return reiziger;
    }
}
