package PersistLayer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersistImp {
	
	/**
	 * @author sahilpatel
	 * Returns the database with female to male ratio salaries (highest one)
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet femaleRatio() throws SQLException
	{
		
		String query = "SELECT"+
    
	"DISTINCT CONCAT(employees.first_name, ' ', employees.last_name) AS 'Name',"+
     "salaries.emp_no AS 'Employee Num',"+
    "departments.dept_no AS 'Dep Numb',"+
    "departments.dept_name AS 'Dep Name',"+
    "depS.avgS AS 'Avg Salary'"+

"FROM salaries"+
"INNER JOIN employees"+
"    ON salaries.emp_no = employees.emp_no"+
"INNER JOIN dept_emp"+
"    ON salaries.emp_no = dept_emp.emp_no  "+
"INNER JOIN departments"+
"    ON dept_emp.dept_no = departments.dept_no   "+
"LEFT JOIN (SELECT"+
 "   departments.dept_no,"+
 "   departments.dept_name,"+
 "   AVG(Salaries.Salary) AS avgS"+
"FROM Salaries"+
"INNER JOIN dept_emp"+
"    ON salaries.emp_no = dept_emp.emp_no"+
"INNER JOIN departments"+
"    ON dept_emp.dept_no = departments.dept_no"+
"GROUP BY    departments.dept_no,"+
"            departments.dept_name) AS depS"+
"    ON dept_emp.dept_no = depS.dept_no;";
		System.out.println(query);
		ResultSet rs = DBAccessInterface.retrieve(query);
		return rs;
		
	}
	
	/**
	 * @author Alec
	 * Returns the name of the manager that has served the longest
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet managersLongest() throws SQLException
	{
		
		String query = "SELECT CONCAT( e.first_name,' ', e.last_name) AS 'Name' FROM employees AS e NATURAL JOIN ( SELECT d.emp_no FROM dept_manager AS d INNER JOIN ( SELECT MAX(DATEDIFF(m.to_date, m.from_date)) AS time_worked FROM dept_manager AS m) AS longest ON DATEDIFF(d.to_date, d.from_date) = longest.time_worked) AS datedata";
		ResultSet rs = DBAccessInterface.retrieve(query);
		return rs;
		
	}
	
	/**
	 * @author Jeremy
	 * Returns the female names, ages and salaries
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet femaleAge () throws SQLException
	{
		String query = "SELECT CONCAT(employees.first_name,' ', employees.last_name) AS 'Name' "+ 
		"FROM dept_manager "+ 
		"JOIN employees ON dept_manager.emp_no=employees.emp_no "+ 
		"JOIN salaries ON dept_manager.emp_no=salaries.emp_no "+ 
		"WHERE gender='F' AND birth_date < '1990-01-01' AND salary > '80000';";
		
		ResultSet rs = DBAccessInterface.retrieve(query);
		return rs;
	}
	
	/**
	 * @author Jeremy
	 * Returns the employee names of people that have worked between a certain date
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet three () throws SQLException
	{
		String query = "select d.dept_no, count(case when birth_date between '1950-01-01' and '1959-12-31' then 1 end) as employees_born_in_50s, count(case when birth_date between '1960-01-01' and '1969-01-01' then 1 end) as employees_born_in_60s, avg(s.salary) as avg_salary from employees e join dept_emp d on (e.emp_no=d.emp_no) join salaries s on (e.emp_no=s.emp_no) group by d.dept_no order by d.dept_no;";
		ResultSet rs = DBAccessInterface.retrieve(query);
		return rs;
	}
	
	/**
	 * @author Alec
	 * Returns employees that have 2 or 3 things in common
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet five() throws SQLException
	{
		String query = "SELECT CONCAT( e.first_name, ' ', e.last_name) AS 'Name' FROM employees AS e NATURAL JOIN ( SELECT emp1.dept_no, emp1.emp_no FROM dept_emp as emp1 NATURAL JOIN ( SELECT emp2.dept_no FROM dept_emp AS emp2 WHERE emp2.emp_no = 10010) AS otherguy WHERE emp1.emp_no IN (10003, 10010) AND emp1.dept_no = 'd004') AS degreedata;";
		ResultSet rs = DBAccessInterface.retrieve(query);
		return rs;
	}
	
	/**
	 * @author sahilpatel
	 * input field for sql queries to be executed
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet inputField(String query) throws SQLException
	{
		ResultSet rs = DBAccessInterface.retrieve(query);
		return rs;
	}
}
