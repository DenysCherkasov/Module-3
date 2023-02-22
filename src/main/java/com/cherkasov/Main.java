package com.cherkasov;

import com.cherkasov.action.Actions;
import com.cherkasov.utils.UserInput;
import com.cherkasov.utils.Util;

public class Main {
    public static void main(String[] args) {
        final Actions[] values = Actions.values();
        final String[] names = Util.mapActionToName(values);

        while (true) {
            final int userChoice = UserInput.menu(names);
            values[userChoice].execute();
        }
    }


}

