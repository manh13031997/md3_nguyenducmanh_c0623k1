package service;


import model.Employees;

import java.util.List;

public interface IEmployeesService {

    public void add(Employees employees);

    public boolean delete(int id);

    public boolean update(Employees employees);

    public List<Employees> findAll();

    public Employees findById(int id);

    List<Employees> findAllToSearch(String searchEmployees);
}
