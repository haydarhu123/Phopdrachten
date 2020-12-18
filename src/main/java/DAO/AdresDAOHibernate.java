package DAO;

import Model.Adres;
import Model.Reiziger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.PersistenceException;
import java.sql.SQLException;
import java.util.List;

public class AdresDAOHibernate implements AdresDAO {
    private SessionFactory sessionfactory;


    @Override
    public boolean save(Adres adres) throws SQLException {
        Session session = sessionfactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(adres);
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
    public boolean delete(Adres adres) throws SQLException {
        Session session = sessionfactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(adres);
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
    public boolean update(Adres adres) throws SQLException {
        Session session = sessionfactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(adres);
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
    public Adres findByReiziger(Reiziger reiziger) throws SQLException {
        Session session = sessionfactory.openSession();
        Adres adres = session.get(Adres.class, reiziger.getId());
        session.close();
        return adres;
    }

    @Override
    public List<Adres> findAll() throws SQLException {
        Session session = sessionfactory.openSession();
        List<Adres> adres = session.createQuery("from Adres").list();
        session.close();

        return adres;
    }
}
