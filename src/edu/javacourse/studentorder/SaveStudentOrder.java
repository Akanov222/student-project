package edu.javacourse.studentorder;

import edu.javacourse.studentorder.domain.StudentOrder;

public class SaveStudentOrder {
    static void scheduleStudentOrder() {
        System.out.println("scheduleStudentOrder");
    }
    static void financeStudentOrder() {
        System.out.println("financeStudentOrder");
    }

    public static void main(String[] args) {
        StudentOrder so = new StudentOrder();
        so.hFirstName = "Alex";
        so.hLastName = "Petrov";
        so.wFirstName = "Gale";
        so.wLastName = "Petrova";

        StudentOrder so1 = new StudentOrder();
        so1.hFirstName = "Alex";
        so1.hLastName = "Sidorov";
        so1.wFirstName = "Gale";
        so1.wLastName = "Sidorova";

        long ans = saveStudentOrder(so);
        System.out.println(ans);
    }

    public static long saveStudentOrder(StudentOrder studentOrder) {
        long answer = 199;
        System.out.println("saveStudentOrder: " + studentOrder.hLastName);
        return answer;
    }
}
