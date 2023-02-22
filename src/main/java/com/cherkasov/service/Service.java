package com.cherkasov.service;

import com.cherkasov.DTO.StringDoubleDTO;
import com.cherkasov.DTO.StringLongDTO;
import com.cherkasov.DTO.StudentsMarksDTO;
import com.cherkasov.model.Group;
import com.cherkasov.model.Student;
import com.cherkasov.model.Teacher;
import com.cherkasov.repository.GroupRepository;
import com.cherkasov.repository.StudentRepository;
import com.cherkasov.repository.SubjectRepository;
import com.cherkasov.repository.TeacherRepository;
import com.cherkasov.utils.Util;
import org.flywaydb.core.Flyway;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class Service {
    private static Service instance;
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;

    private static final Random RANDOM = new Random();

    private Service() {
        doMigration();
        groupRepository = GroupRepository.getInstance();
        studentRepository = StudentRepository.getInstance();
        teacherRepository = TeacherRepository.getInstance();
        subjectRepository = SubjectRepository.getInstance();
    }

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    public Student createStudent() {
        Student.StudentBuilder studentBuilder =
                new Student.StudentBuilder();
        Student student = studentBuilder
                .withId()
                .withFirstName(Util.getRandomString())
                .withSurname(Util.getRandomString())
                .withAge(RANDOM.nextInt(18, 25))
                .withReceiptDate(LocalDate.now())
                .build();
        studentRepository.save(student);
        return student;
    }

    public List<Group> findGroupByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Invalid name");
        } else {
            return groupRepository.findGroupByName(name);
        }
    }

    public List<StringLongDTO> countStudentsEveryGroup() {
        return studentRepository.countStudentsEveryGroup();
    }

    public List<StringDoubleDTO> averageMarkEveryGroup() {
        return groupRepository.averageMarkEveryGroup();
    }

    public List<Teacher> findTeacherByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Invalid name");
        } else {
            return teacherRepository.findTeacherByName(name);
        }
    }

    public List<StringDoubleDTO> findSubjectWithAverageMark() {
        return subjectRepository.findSubjectWithAverageMark();
    }

    public List<StudentsMarksDTO> findStudentByAvgMark(double value) {
        if (value < 0 || value >= 100) {
            throw new IllegalArgumentException("Invalid value");
        } else {
            return studentRepository.findStudentByAvgMark(value);
        }
    }

    private static void doMigration() {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:postgresql://localhost:5432/module3",
                        "postgres", "root")
                .baselineOnMigrate(true)
                .locations("db.migration")
                .load();
        flyway.migrate();
    }
}

