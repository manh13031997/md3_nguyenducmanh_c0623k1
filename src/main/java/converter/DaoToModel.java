package converter;

import model.Department;
import model.Employees;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoToModel {
    static DaoToModel daoToModel;

    public static DaoToModel getInstance() {
        if (daoToModel == null) {
            daoToModel = new DaoToModel();
        }
        return daoToModel;
    }

    public Employees employeesDaoToModel(ResultSet mResultSet) throws SQLException {
        Employees employees = new Employees();
        employees.setId(mResultSet.getInt("id"));
        employees.setName(mResultSet.getString("name"));
        employees.setEmail(mResultSet.getString("email"));
        employees.setAddress(mResultSet.getString("address"));
        employees.setPhone(mResultSet.getString("phone"));
        employees.setSalary(mResultSet.getDouble("salary"));
        employees.setStatus(mResultSet.getInt("status"));
        Department department = new Department();
        employees.setId(mResultSet.getInt("departmentID"));
        try {
            department.setName(mResultSet.getString("department.name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        employees.setDepartment(department);
        return employees;
    }

    public Department departmentDaoToModel(ResultSet mResultSet) throws SQLException {
        Department department = new Department();
        department.setId(mResultSet.getInt("id"));
        department.setName(mResultSet.getString("name"));
        department.setStatus(mResultSet.getInt("status"));
        return department;
    }
}
