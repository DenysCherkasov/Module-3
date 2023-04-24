package com.cherkasov.repository;

import com.cherkasov.DTO.StringLongDTO;
import com.cherkasov.DTO.StudentsMarksDTO;
import com.cherkasov.model.Student;
import com.cherkasov.utils.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class StudentRepository {
    private static StudentRepository instance;

    private StudentRepository() {
    }

    public static StudentRepository getInstance() {
        if (instance == null) {
            instance = new StudentRepository();
        }
        return instance;
    }

    public void save(Student student) {
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        entityManager.close();
    }


    public List<StringLongDTO> countStudentsEveryGroup() {
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        return entityManager.createQuery(
                        "SELECT new com.cherkasov.DTO.StringLongDTO (d.name, COUNT(*)) " +
                                "FROM Group d JOIN d.students e GROUP BY d.name",
                        StringLongDTO.class)
                .getResultList();
    }

    public List<StudentsMarksDTO> findStudentByAvgMark(double value) {
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        return entityManager.createQuery(
                        "SELECT new com.cherkasov.DTO.StudentsMarksDTO " +
                                "(d.id, d.firstName, d.surname, AVG (k.value)) " +
                                "FROM Student d JOIN d.marks k GROUP BY d.id " +
                                "HAVING AVG (k.value) > " + value, StudentsMarksDTO.class)
                .getResultList();
    }
}
