package DAO;


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
    private Session ssn;

    public  OVChipkaartDAOHibernate(Session ssn){
        this.ssn = ssn;
    }

    @Override
    public boolean save(OVChipkaart ovchipkaart) throws SQLException {
        Session ssn = sessionfactory.openSession();
        Transaction transaction = ssn.beginTransaction();

        try {
            ssn.save(ovchipkaart);
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
    public boolean delete(OVChipkaart ovchipkaart) throws SQLException {
        Session ssn = sessionfactory.openSession();
        Transaction transaction = ssn.beginTransaction();

        try {
            ssn.delete(ovchipkaart);
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
    public boolean update(OVChipkaart ovchipkaart) throws SQLException {
        Session ssn = sessionfactory.openSession();
        Transaction transaction = ssn.beginTransaction();

        try {
            ssn.update(ovchipkaart);
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
    public OVChipkaart findByReiziger(Reiziger reiziger) throws SQLException {
        Session ssn = sessionfactory.openSession();
        OVChipkaart ovchipkaart = ssn.get(OVChipkaart.class, reiziger.getId());
        ssn.close();
        return ovchipkaart;
    }

    @Override
    public List<OVChipkaart> findAll() throws SQLException {
        Session ssn = sessionfactory.openSession();
        List<OVChipkaart> ovchipkaart = ssn.createQuery("from OVChipkaart").list();
        ssn.close();

        return ovchipkaart;
    }

    @Override
    public OVChipkaart findByKaartnummer(int kaartnummer) throws SQLException {
        Session ssn = sessionfactory.openSession();
        OVChipkaart ovchipkaart = ssn.get(OVChipkaart.class, kaartnummer);
        ssn.close();
        return ovchipkaart;
    }
}
