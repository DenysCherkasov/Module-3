package com.cherkasov.action;

import lombok.Getter;

@Getter
public enum Actions {
    FIND_GROUP("Find group by name", new FindGroupAction()),
    COUNT_STUDENTS("Count students in every group", new CountStudentsAction()),
    AVERAGE_MARK("Find an average mark of every group", new AverageMarkAction()),
    FIND_TEACHER("Find the teacher by name or surname", new FindTeacherAction()),
    FIND_SUBJECT("Find the subject with the lowest and highest average mark",
            new FindSubjectAction()),
    FIND_STUDENT("Find students whose average score is higher than the specified value",
            new FindStudentAction()),
    CREATE_RANDOM_STUDENT("Create random student", new CreateStudentAction()),
    EXIT("Finish program", new ExitAction());

    private final String name;
    private final Action action;

    Actions(final String name, final Action action) {
        this.name = name;
        this.action = action;
    }

    public void execute() {
        action.execute();
    }
}
