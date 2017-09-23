package com.arcsoft.study.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.arcsoft.study.domain.Department;
import com.arcsoft.study.domain.Employee;
import com.arcsoft.study.domain.Role;
import com.arcsoft.study.service.IDepartmentService;
import com.arcsoft.study.service.IEmployeeService;
import com.arcsoft.study.service.IRoleService;
import com.arcsoft.study.util.Constant;
import com.arcsoft.study.util.QINIUServer;
import com.arcsoft.study.util.ReadExcel;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;

@RestController
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private MultipartConfigElement multipartConfigElement;

    private final String defaultIcon = "default.jpg";

    @RequestMapping("user/login")
    public Map<String, Object> userLogin(Employee employee, HttpSession httpSession) {
        Employee user = employeeService.getEmployeeByPhone(employee);
        Map<String, Object> result = new HashMap<>();
        if (user == null) {
            result.put("success", false);
            result.put("message", "账号不存在");
        } else if (user.getPassword().equals(employee.getPassword())) {
            httpSession.setAttribute("userId", user.getId());
            result.put("jsession", httpSession.getId());
            result.put("userName", user.getName());
            result.put("icon", user.getIcon());
            result.put("roleId", user.getRoleId());
            result.put("success", true);
            result.put("message", "登录成功");
        } else {
            result.put("success", false);
            result.put("message", "密码错误");
        }
        return result;
    }

    @RequestMapping("admin/login")
    public Map<String, Object> login(Employee employee, HttpSession httpSession) {
        Employee user = employeeService.getEmployeeByPhone(employee);
        Map<String, Object> result = new HashMap<>();
        if (user == null) {
            result.put("success", false);
            result.put("message", "账号不存在");
        } else if (user.getPassword().equals(employee.getPassword())) {
            httpSession.setAttribute("adminId", user.getId());
            result.put("jsession", httpSession.getId());
            result.put("userName", user.getName());
            result.put("icon", user.getIcon());
            result.put("roleId", user.getRoleId());
            result.put("success", true);
            result.put("message", "登录成功");
        } else {
            result.put("success", false);
            result.put("message", "密码错误");
        }
        return result;

    }

    @RequestMapping("/updatePassword")
    public String updatePassword(Employee employee) {
        int n = employeeService.updatePassword(employee);
        if (n > 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    /**
     * 
     * @param mFile
     * @param req
     * @function Excel批量插入后，分页查询
     */
    @RequestMapping("/uploadExcel")
    public int readExcel(@RequestParam("filename") MultipartFile mFile) {
        // 部门列表
        List<Department> departmentList = departmentService.getAllDepartment();
        // 角色列表
        List<Role> roleList = roleService.selectRole();
        List<Employee> employeeList = new ReadExcel(departmentList, roleList).getExcelInfo(mFile);
        employeeService.addEmployeeExcel(employeeList);
        for (Employee e : employeeList) {
            try {
                BucketManager bucketManager = new BucketManager(QINIUServer.getAuth(), new Configuration(Zone.zone2()));
                bucketManager.copy(QINIUServer.getBucket(), defaultIcon, QINIUServer.getBucket(),
                        "/icon/" + e.getId() + "/" + defaultIcon);
                employeeService.updateIcon(e.getId(), "/icon/" + e.getId() + "/" + defaultIcon);
            } catch (QiniuException ex) {
                // 如果遇到异常，说明复制失败
                System.err.println(ex.code());
            }
        }
        return 0;
    }

    /**
     * 
     * @param pageNum
     * @param model
     * @param state
     * @function 根据在职状体查询
     */
    @RequestMapping("/selectEmployeeByState")
    public String selectEmployeeByState(Integer pageNum, Model model, String state) {
        if (pageNum == null) {
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, Constant.pageSize);
        List<Map> employeeList = employeeService.findEmployeeByState(state);
        PageInfo pageInfo = new PageInfo(employeeList);
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("pageInfo", pageInfo);
        return "index.html";
    }

    /**
     * 
     * @param pageNum
     * @param model
     * @param name
     * @function 根据名字查询
     */
    @RequestMapping("/getEmployeeByName")
    public List<Map> selectEmployeeByName(Integer pageNum, String name) {
        if (pageNum != null) {
            Page<Object> page = PageHelper.startPage(pageNum, Constant.pageSize);
            List<Map> result = employeeService.findEmployeeByName("%" + name + "%");
            Map<String, Object> total = new HashMap<>();
            total.put("total", page.getTotal());
            result.add(total);
            return result;
        }
        return employeeService.findEmployeeByName("%" + name + "%");
    }

    @RequestMapping("/addEmployee")
    public int addEmployee(Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @RequestMapping("/getAllEmployee")
    public List<Map> getAllEmployee(Integer pageNum) {
        if (pageNum != null) {
            Page<Object> page = PageHelper.startPage(pageNum, Constant.pageSize);
            List<Map> result = employeeService.getAllEmployee();
            Map<String, Object> total = new HashMap<>();
            total.put("size", Constant.pageSize);
            total.put("total", page.getTotal());
            result.add(total);
            return result;
        }
        return employeeService.getAllEmployee();
    }

    @RequestMapping("/getAllTeacher")
    public List<Map> getAllTeacher(Integer pageNum) {
        if (pageNum != null) {
            Page<Object> page = PageHelper.startPage(pageNum, Constant.pageSize);
            List<Map> result = employeeService.getAllTeacher();
            Map<String, Object> total = new HashMap<>();
            total.put("size", Constant.pageSize);
            total.put("total", page.getTotal());
            result.add(total);
            return result;
        }
        return employeeService.getAllTeacher();
    }

    @RequestMapping("/updateEmployee")
    public int updateEmployee(Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @RequestMapping("/getEmployeeGroupByDepartment")
    public List<Map> getEmployeeGroupByDepartment() {
        return employeeService.getEmployeeGroupByDepartment();
    }

    @RequestMapping("/updateIcon")
    public Map updateIcon(@RequestParam(value = "filename", required = false) MultipartFile file,
            HttpSession httpSession) {
        Map result = new HashMap<>();
        Long adminId = (Long) httpSession.getAttribute("adminId");
        String key = "/icon/" + adminId + "/" + file.getOriginalFilename();
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        // ...其他参数参考类注释
        BucketManager bucketManager = new BucketManager(QINIUServer.getAuth(), cfg);
        String deleteKey = employeeService.findEmployeeById(adminId).get("icon").toString();
        try {
            bucketManager.delete("arcsoft", deleteKey);
        } catch (QiniuException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }

        try {
            UploadManager uploadManager = new UploadManager(cfg);
            byte[] uploadBytes = file.getBytes();
            String upToken = QINIUServer.getUpToken();
            // 默认不指定key的情况下，以文件内容的hash值作为文件名
            try {
                Response response = uploadManager.put(uploadBytes, key, upToken);
                // 解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    // ignore
                }
            }
        } catch (IOException ex) {
            // ignore
        }
        employeeService.updateIcon((Long) httpSession.getAttribute("adminId"), key);
        result.put("icon", key);
        return result;
    }

    @RequestMapping("/addTeacher")
    public int addTeacher(Employee employee) {
        // TODO 自动生成的方法存根
        employee.setRoleId(new Long(2));
        employeeService.addEmployee(employee);
        return employeeService.updateIcon(employee.getId(), "/icon/" + employee.getId() + defaultIcon);
    }
}
