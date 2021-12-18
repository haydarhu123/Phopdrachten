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
    private Session ssn;

    public  AdresDAOHibernate(Session ssn){
        this.ssn = ssn;
    }


    @Override
    public boolean save(Adres adres) throws SQLException {
        Session ssn = sessionfactory.openSession();
        Transaction transaction = ssn.beginTransaction();

        try {
            ssn.save(adres);
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
    public boolean delete(Adres adres) throws SQLException {
        Session ssn = sessionfactory.openSession();
        Transaction transaction = ssn.beginTransaction();

        try {
            ssn.delete(adres);
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
    public boolean update(Adres adres) throws SQLException {
        Session ssn = sessionfactory.openSession();
        Transaction transaction = ssn.beginTransaction();

        try {
            ssn.update(adres);
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
    public Adres findByReiziger(Reiziger reiziger) throws SQLException {
        Session ssn = sessionfactory.openSession();
        Adres adres = ssn.get(Adres.class, reiziger.getId());
        ssn.close();
        return adres;
    }

    @Override
    public List<Adres> findAll() throws SQLException {
        Session ssn = sessionfactory.openSession();
        List<Adres> adres = ssn.createQuery("from Adres").list();
        ssn.close();

        return adres;
    }
}
