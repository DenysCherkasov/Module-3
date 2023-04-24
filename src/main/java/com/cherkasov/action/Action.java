package com.cherkasov.action;

import com.cherkasov.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Action {
    Service service = Service.getInstance();
    Logger logger = LoggerFactory.getLogger(Action.class) ;

    void execute();
}
