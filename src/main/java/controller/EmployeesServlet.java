
package controller;



import model.Department;
import model.Employees;
import service.DepartmentService;
import service.EmployeesService;
import service.IEmployeesService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "employeesServlet", urlPatterns = {"/employees-servlet"})
public class EmployeesServlet extends HttpServlet {

    private final DepartmentService departmentService = new DepartmentService();

    private final IEmployeesService employeesService = new EmployeesService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createEmployeesForm(request, response);
                break;
            case "edit":
                editEmployeesForm(request, response);
                break;
            case "delete":
                deleteEmployees(request, response);
                break;
            case "search":
                searchEmployeesForm(request, response);
                break;
            default:
                listEmployees(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createEmployees(request,response);
                break;
            case "edit":
                editEmployees(request, response);
                break;
            case "search":
                searchEmployees(request, response);
                break;
            default:
                break;
        }
    }

    private void searchEmployees(HttpServletRequest request, HttpServletResponse response) {
        String searchEmployees = request.getParameter("searchEmployees");
        List<Employees> list = employeesService.findAllToSearch(searchEmployees);
    }

    private void searchEmployeesForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/search.jsp");
        requestDispatcher.forward(request, response);

    }

    private void deleteEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        employeesService.delete(id);
        listEmployees(request, response);
    }

    private void editEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        Double salary = Double.valueOf(request.getParameter("salary"));
        Department department = new Department();
        department.setId(Integer.parseInt(request.getParameter("departmentID")));
        Employees employees = new Employees(id, name, email, address, phone, salary, department);
        employeesService.update(employees);
        listEmployees(request, response);
    }

    private void editEmployeesForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employees employees = this.employeesService.findById(id);
        RequestDispatcher dispatcher;
        if (employees == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            List<Department> departmentList = departmentService.findAll();
            request.setAttribute("departmentList", departmentList);
            request.setAttribute("employees", employees);
            dispatcher = request.getRequestDispatcher("views/form.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void createEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        Double salary = Double.valueOf(request.getParameter("salary"));
        Department department = new Department();
        department.setId(Integer.parseInt(request.getParameter("departmentID")));
        Employees employees = new Employees(name, email, address, phone, salary, department);
        employeesService.add(employees);
        listEmployees(request, response);
    }

    private void createEmployeesForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/form.jsp");
        List<Department> departmentList = departmentService.findAll();
        request.setAttribute("departmentList", departmentList);
        requestDispatcher.forward(request, response);
    }

    private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/list.jsp");
        List<Employees> employeesList = employeesService.findAll();
        request.setAttribute("employeesList", employeesList);
        requestDispatcher.forward(request, response);
    }
}
