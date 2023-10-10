package dao;



import converter.DaoToModel;
import model.Department;
import model.Employees;
import util.ConnectionUtil;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeesDAO extends ConnectionUtil {
    DaoToModel converter = DaoToModel.getInstance();

    DepartmentDAO departmentDAO = new DepartmentDAO();


    public List<Employees> findAll(){
        List<Employees> employeesList = new ArrayList<>();
        String sql = "Select * " +
                "from employees  join department on employees.departmentID = department.id " +
                "where employees.status = 1";

        try {
            open();
            mPreparedStatement = mConnection.prepareStatement(sql);
            mResultSet = mPreparedStatement.executeQuery();
            while (mResultSet.next()) {
                Employees employees = converter.employeesDaoToModel(mResultSet);
                employeesList.add(employees);
            }
            close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return employeesList;
    }

    public boolean update(Employees employees) {
        boolean check;
        String sql = "update employees set name = ?, email = ?, address = ?, phone = ?, departmentID = ? where id = ?";
        try {
            open();
            mPreparedStatement = mConnection.prepareStatement(sql);
            mPreparedStatement.setString(1, employees.getName());
            mPreparedStatement.setString(2, employees.getEmail());
            mPreparedStatement.setString(3, employees.getAddress());
            mPreparedStatement.setString(4, employees.getPhone());
            mPreparedStatement.setInt(5, employees.getDepartment().getId());
            mPreparedStatement.setInt(6, employees.getId());
            check = mPreparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    public void insert(Employees employees) {
        String sql = "INSERT INTO employees (`name`, `email`, `address`, `phone`, `salary`, `departmentID`, `status`) VALUES (?, ?, ?, ?, ?, ?, 1)";

        try {
            open();
            mPreparedStatement = mConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            mPreparedStatement.setString(1, employees.getName());
            mPreparedStatement.setString(2, employees.getEmail());
            mPreparedStatement.setString(3, employees.getAddress());
            mPreparedStatement.setString(4, employees.getPhone());
            mPreparedStatement.setDouble(5, employees.getSalary());
            mPreparedStatement.setInt(6, employees.getDepartment().getId());
            mPreparedStatement.executeUpdate();
            mResultSet = mPreparedStatement.getGeneratedKeys();
            close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(int id) {
        boolean check;
        String sql = "update employees set status = 0 where id = ?";
        try {
            open();
            mPreparedStatement = mConnection.prepareStatement(sql);
            mPreparedStatement.setInt(1, id);
            check = mPreparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    public Employees findByID(int id) {
        Employees employees = new Employees();
        String sql = "SELECT * FROM employees WHERE id = ?";
        try {
            open();
            mPreparedStatement = mConnection.prepareStatement(sql);
            mPreparedStatement.setInt(1, id);
            mResultSet = mPreparedStatement.executeQuery();
            while (mResultSet.next()) {
                employees = converter.employeesDaoToModel(mResultSet);
            }
            close();

            Department department = departmentDAO.findById(employees.getDepartment().getId());
            employees.setDepartment(department);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    public List<Employees> findAllToSearch(String searchEmployees) {
        List<Employees> employeesList = new ArrayList<>();
        String sql = "Select * " +
                "from employees  join department on employees.departmentID = department.id " +
                "where employees.status = 1 and where employees.name like '%searchEmployees%' ";

        try {
            open();
            mPreparedStatement = mConnection.prepareStatement(sql);
            mResultSet = mPreparedStatement.executeQuery();
            while (mResultSet.next()) {
                Employees employees = converter.employeesDaoToModel(mResultSet);
                employeesList.add(employees);
            }
            close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return employeesList;
    }
}
