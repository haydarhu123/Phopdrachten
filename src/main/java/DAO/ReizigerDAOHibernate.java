package DAO;

import Model.Adres;
import Model.OVChipkaart;
import Model.Reiziger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;
import java.sql.SQLException;
import java.util.List;

public class ReizigerDAOHibernate implements ReizigerDAO{
    private SessionFactory sessionfactory;

    @Override
    public boolean save(Reiziger reiziger) throws SQLException {
        Session session = sessionfactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(reiziger);
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
    public boolean delete(Reiziger reiziger) throws SQLException {
        Session session = sessionfactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(reiziger);
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
    public boolean update(Reiziger reiziger) throws SQLException {
        Session session = sessionfactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(reiziger);
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
    public Reiziger findById(int id) throws SQLException {
        Session session = sessionfactory.openSession();
        Reiziger reiziger = session.get(Reiziger.class, id);
        session.close();
        return reiziger;
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) throws SQLException {
        Session session = sessionfactory.openSession();
        Query query = session.createQuery("FROM Reiziger where geboortedatum = :datum");
        query.setParameter("datum", datum);
        List<Reiziger> reiziger = query.list();
        session.close();
        return reiziger;
    }

    @Override
    public List<Reiziger> findAll() throws SQLException {
        Session session = sessionfactory.openSession();
        List<Reiziger> reiziger = session.createQuery("from Reiziger ").list();
        session.close();

        return reiziger;
    }
}
