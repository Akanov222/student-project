package edu.javacourse.studentorder.domain.register;

import java.util.List;
import java.util.ArrayList;

public class AnswerCityRegister {

    private List<AnswerCityRegisterItem> items;

    public void addItem (AnswerCityRegisterItem item) {
        if (items == null) {
            items = new ArrayList<>(10);
        }
        items.add(item);
    }

    public List<AnswerCityRegisterItem> getItems() {
        return items;
    }
}
