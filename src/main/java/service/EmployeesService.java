package service;


import dao.EmployeesDAO;
import model.Employees;

import java.util.List;

public class EmployeesService implements IEmployeesService{
    EmployeesDAO employeesDAO = new EmployeesDAO();


    @Override
    public void add(Employees employees) {
        employeesDAO.insert(employees);
    }

    @Override
    public boolean delete(int id) {
        return employeesDAO.delete(id);
    }

    @Override
    public boolean update(Employees employees) {
        return employeesDAO.update(employees);
    }

    @Override
    public List<Employees> findAll() {
        return employeesDAO.findAll();
    }

    @Override
    public Employees findById(int id) {
        return employeesDAO.findByID(id);
    }

    @Override
    public List<Employees> findAllToSearch(String searchEmployees) {
        return employeesDAO.findAllToSearch(searchEmployees);
    }
}
