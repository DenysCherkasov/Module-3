package com.cherkasov.action;

import com.cherkasov.model.Student;

import java.time.LocalDateTime;

public class CreateStudentAction implements Action {
    @Override
    public void execute() {
        logger.info("CteateStudentAction was called. Time: " + LocalDateTime.now());
        Student student = service.createStudent();
        System.out.println("Created student: " + student);
    }
}
