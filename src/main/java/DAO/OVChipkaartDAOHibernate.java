package DAO;

import Model.Adres;
import Model.OVChipkaart;
import Model.Reiziger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.PersistenceException;
import java.sql.SQLException;
import java.util.List;

public class OVChipkaartDAOHibernate implements OVChipkaartDAO {
    private SessionFactory sessionfactory;

    @Override
    public boolean save(OVChipkaart ovchipkaart) throws SQLException {
        Session session = sessionfactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(ovchipkaart);
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
    public boolean delete(OVChipkaart ovchipkaart) throws SQLException {
        Session session = sessionfactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(ovchipkaart);
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
    public boolean update(OVChipkaart ovchipkaart) throws SQLException {
        Session session = sessionfactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(ovchipkaart);
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
    public OVChipkaart findByReiziger(Reiziger reiziger) throws SQLException {
        Session session = sessionfactory.openSession();
        OVChipkaart ovchipkaart = session.get(OVChipkaart.class, reiziger.getId());
        session.close();
        return ovchipkaart;
    }

    @Override
    public List<OVChipkaart> findAll() throws SQLException {
        Session session = sessionfactory.openSession();
        List<OVChipkaart> ovchipkaart = session.createQuery("from OVChipkaart").list();
        session.close();

        return ovchipkaart;
    }

    @Override
    public OVChipkaart findByKaartnummer(int kaartnummer) throws SQLException {
        Session session = sessionfactory.openSession();
        OVChipkaart ovchipkaart = session.get(OVChipkaart.class, kaartnummer);
        session.close();
        return ovchipkaart;
    }
}
