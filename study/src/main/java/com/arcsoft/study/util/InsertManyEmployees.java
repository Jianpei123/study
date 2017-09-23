package com.arcsoft.study.util;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import com.arcsoft.study.domain.Employee;

public class InsertManyEmployees {
    public String insertAll(Map map) {
        List<Employee> users = (List<Employee>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO employee ");
        sb.append("(name,age,sex,phone,job_number,password,department_id,role_id,state,icon) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat(
                "(#'{'list[{0}].name},#'{'list[{0}].age}, #'{'list[{0}].sex},#'{'list[{0}].phone},#'{'list[{0}].jobNumber},#'{'list[{0}].password},#'{'list[{0}].departmentId},#'{'list[{0}].roleId},#'{'list[{0}].state},#'{'list[{0}].icon})");
        for (int i = 0; i < users.size(); i++) {
            sb.append(mf.format(new Object[] { i }));
            if (i < users.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
