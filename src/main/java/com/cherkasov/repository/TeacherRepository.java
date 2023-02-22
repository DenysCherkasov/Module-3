package com.cherkasov.repository;

import com.cherkasov.model.Teacher;
import com.cherkasov.utils.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class TeacherRepository {

    private static TeacherRepository instance;

    private TeacherRepository() {
    }

    public static TeacherRepository getInstance() {
        if (instance == null) {
            instance = new TeacherRepository();
        }
        return instance;
    }

    public List<Teacher> findTeacherByName(String name) {
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        return entityManager.createQuery(
                        "SELECT c FROM Teacher c WHERE c.firstName LIKE " +
                                ":custName OR c.surname LIKE :custName", Teacher.class)
                .setParameter("custName", name)
                .getResultList();
    }
}
