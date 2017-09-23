package com.arcsoft.study.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.arcsoft.study.domain.Department;
import com.arcsoft.study.domain.Employee;
import com.arcsoft.study.domain.Role;
import com.arcsoft.study.service.impl.DepartmentServiceImpl;
import com.arcsoft.study.service.impl.RoleServiceImpl;

public class ReadExcel2 {
    // 总行数
    private int totalRows = 0;
    // 总条数
    private int totalCells = 0;

    private List<Department> departmentList;

    private List<Role> roleList;
    // 错误信息接收器
    private String errorMsg;

    // 构造方法
    public ReadExcel2(List departmentList, List roleList) {
        this.departmentList = departmentList;
        this.roleList = roleList;
    }

    // 获取总行数
    public int getTotalRows() {
        return totalRows;
    }

    // 获取总列数
    public int getTotalCells() {
        return totalCells;
    }

    // 获取错误信息
    public String getErrorInfo() {
        return errorMsg;
    }

    /**
     * 读EXCEL文件，获取信息集合
     * 
     * @param fielName
     * @return
     */
    public List<Employee> getExcelInfo(InputStream is) {
        // String fileName = mFile.getOriginalFilename();
        List<Employee> employeeList = null;
        // try {
        // if (!validateExcel(fileName)) {// 验证文件名是否合格
        // return null;
        // }
        // boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
        // if (isExcel2007(fileName)) {
        // isExcel2003 = false;
        // }
        employeeList = createExcel(is, false);
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        return employeeList;
    }

    /**
     * 根据excel里面的内容读取客户信息
     * 
     * @param is
     *            输入流
     * @param isExcel2003
     *            excel是2003还是2007版本
     * @return
     * @throws IOException
     */
    public List<Employee> createExcel(InputStream is, boolean isExcel2003) {
        List<Employee> employeeList = null;
        try {
            Workbook wb = null;
            if (isExcel2003) {// 当excel是2003时,创建excel2003
                wb = new HSSFWorkbook(is);
            } else {// 当excel是2007时,创建excel2007
                wb = new XSSFWorkbook(is);
            }
            employeeList = readExcelValue(wb);// 读取Excel里面客户的信息
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    /**
     * 读取Excel里面客户的信息
     * 
     * @param wb
     * @return
     */
    private List<Employee> readExcelValue(Workbook wb) {

        // 得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();
        // 得到Excel的列数(前提是有行数)
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<Employee> employeeList = new ArrayList<Employee>();
        // 循环Excel行数
        for (int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            Employee employee = new Employee();
            // 循环Excel的列
            for (int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                if (null != cell) {
                    if (c == 0) {
                        employee.setName(cell.getStringCellValue());
                    } else if (c == 1) {
                        employee.setSex(cell.getStringCellValue());
                    } else if (c == 2) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        System.out.println(cell.getStringCellValue());
                        employee.setPhone(cell.getStringCellValue());
                    } else if (c == 3) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        System.out.println(cell.getStringCellValue());
                        employee.setJobNumber(cell.getStringCellValue());
                    } else if (c == 4) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        employee.setPassword(cell.getStringCellValue());
                        System.out.println(cell.getStringCellValue());
                    } else if (c == 5) {
                        // department_id
                        System.out.println(cell.getStringCellValue());
                        for (Department department : departmentList) {
                            if (department.getName().equals(cell.getStringCellValue())) {
                                employee.setDepartmentId(department.getId());

                                break;
                            }
                        }
                    } else if (c == 6) {
                        // role_id
                        System.out.println(cell.getStringCellValue());
                        for (Role role : roleList) {
                            if (role.getName().equals(cell.getStringCellValue())) {
                                employee.setRoleId(role.getId());
                                break;

                            }
                        }

                    } else if (c == 7) {

                        // employee.setState(cell.getStringCellValue());
                    }

                }
            }
            // 添加到list
            employeeList.add(employee);
            System.out.println(employee);
        }
        return employeeList;
    }

    /**
     * 验证EXCEL文件
     * 
     * @param filePath
     * @return
     */
    public boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            errorMsg = "文件名不是excel格式";
            return false;
        }
        return true;
    }

    // @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    // @描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

}
