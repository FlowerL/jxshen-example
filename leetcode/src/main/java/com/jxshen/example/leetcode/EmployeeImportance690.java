package com.jxshen.example.leetcode;

import java.util.*;

public class EmployeeImportance690 {
    public int getImportance(List<Employee> employees, int id) {
        int res = 0;
        if (employees == null || employees.isEmpty()) {
            return res;
        }

        Map<Integer, Employee> employeeMap = new HashMap<>();
        Employee target = null;
        for (Employee employee : employees) {
            employeeMap.put(employee.id, employee);
            if (employee.id == id) {
                target = employee;
            }
        }

        if (target == null) {
            return res;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(target.id);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer employeeId = queue.remove();
                Employee employee = employeeMap.get(employeeId);
                res += employee == null ? 0 : employee.importance;
                if (employee.subordinates != null && !employee.subordinates.isEmpty()) {
                    queue.addAll(employee.subordinates);
                }
            }
        }
        return res;
    }

    private static class Employee {
        // It's the unique id of each node;
        // unique id of this employee
        public int id;
        // the importance value of this employee
        public int importance;
        // the id of direct subordinates
        public List<Integer> subordinates;
    };
}
