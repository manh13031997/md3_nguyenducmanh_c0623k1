package model;

public class Employees extends AbstractModel {
    private String email;
    private String address;
    private String phone;
    private double salary;
    private Department department;

    public Employees() {
    }

    public Employees(String name, String email, String address, String phone, double salary, Department department) {
        super.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.salary = salary;
        this.department = department;
    }
    public Employees(int id, String name, String email, String address, String phone, double salary, Department department) {
        super.id = id;
        super.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.salary = salary;
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
