package com.cherkasov.action;

import com.cherkasov.DTO.StringDoubleDTO;

import java.time.LocalDateTime;
import java.util.List;

public class AverageMarkAction implements Action {

    @Override
    public void execute() {
        logger.info("AverageMarkAction was called. Time: " + LocalDateTime.now());
        List<StringDoubleDTO> averageMarkEveryGroup = service.averageMarkEveryGroup();
        if (averageMarkEveryGroup.size() != 0) {
            averageMarkEveryGroup.forEach(group -> System.out.println("Group name: "
                    + group.getName() + ", average mark: " + group.getValue()));
        } else {
            System.out.println("There is no groups with students which have marks");
        }
    }
}
