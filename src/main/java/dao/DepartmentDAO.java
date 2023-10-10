package dao;


import converter.DaoToModel;
import model.Department;
import util.ConnectionUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO extends ConnectionUtil {
    DaoToModel converter = DaoToModel.getInstance();

    public Department findById(int id) {
        Department department = new Department();
        String sql = "select * from department where id = ?";
        try {
            open();
            mPreparedStatement = mConnection.prepareStatement(sql);
            mPreparedStatement.setInt(1, id);
            mResultSet = mPreparedStatement.executeQuery();
            while (mResultSet.next()) {
                department = converter.departmentDaoToModel(mResultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return department;
    }

    public List<Department> findAll() {
        List<Department> departmentList = new ArrayList<>();
        try {
            open();
            String sql = "Select * from department where status = 1";
            mPreparedStatement = mConnection.prepareStatement(sql);
            mResultSet = mPreparedStatement.executeQuery();
            while (mResultSet.next()) {
                Department department = converter.departmentDaoToModel(mResultSet);
                departmentList.add(department);
            }
            close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return departmentList;
    }
}
