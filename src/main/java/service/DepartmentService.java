package service;


import dao.DepartmentDAO;
import model.Department;

import java.util.List;

public class DepartmentService {
    DepartmentDAO departmentDAO = new DepartmentDAO();

    public List<Department> findAll() {
        return departmentDAO.findAll();
    }
}
