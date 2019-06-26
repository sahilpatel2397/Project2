

import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import PersistLayer.PersistImp;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_23);
	 Configuration cfg = null;
	 SimpleHash root = new SimpleHash(df.build());
	 private String templateDir = "/WEB-INF/templates";
    /**
     * Default constructor. 
     */
    public Servlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		df.setExposeFields(true);
		cfg = new Configuration(Configuration.VERSION_2_3_23);
	    cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);
	    cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String button = request.getParameter("submit");
		System.out.println(button);
		HttpSession session = request.getSession();
		if(button.equals("Submit")){
			try {
				String first = request.getParameter("query1");
				System.out.println(first);
				String second = request.getParameter("query2");
				System.out.println(second);
				String third = request.getParameter("query3");
				System.out.println(third);
				String four = request.getParameter("query4");
				System.out.println(four);
				String five = request.getParameter("query5");
				System.out.println(five);
				String input = request.getParameter("theQuery");
				
				int f = 0;
				if (first!=null && first.equalsIgnoreCase("query1")){
					first = "Human Resources";
					root.put("firsts",first);
					System.out.println("ENTERS");
					f = 1;
				}
				root.put("f", f);
				
				int s = 0;
				if (second!=null && second.equalsIgnoreCase("query2")){
					ResultSet rs = PersistImp.managersLongest();
					second = null;
					while (rs.next()){
						second = rs.getString("Name");
					}
					System.out.println("ENTERS");
					root.put("second",second);
					s = 1;
				}
				root.put("s", s);
				
				int t = 0;
				if (third!=null &&third.equalsIgnoreCase("query3")){
					ResultSet rs = PersistImp.three();
					ResultSetMetaData rsmd = rs.getMetaData();
					int columnsNumber = rsmd.getColumnCount();
					root.put("colNums", columnsNumber);
					third = null;
					ArrayList<String> al = new ArrayList<String>();
					while(rs.next()){
						String dept_no = rs.getString("dept_no");
						String employees_born_in_50s = rs.getString("employees_born_in_50s");
						String employees_born_in_60s = rs.getString("employees_born_in_60s");
						String avg_salary = rs.getString("avg_salary");
						al.add(dept_no);
						al.add(employees_born_in_50s);
						al.add(employees_born_in_60s);
						al.add(avg_salary);
					}
					root.put("thirds", al);
					int rowsNumber = al.size()/columnsNumber;
					System.out.println(rowsNumber);
					if(rowsNumber>100)
						rowsNumber=100;
					root.put("rowInputs", rowsNumber);
					t = 1;
				}
				root.put("t", t);
				
				int fo = 0;
				if (four!=null &&four.equalsIgnoreCase("query4")){
					ResultSet rs = PersistImp.femaleAge();
					ArrayList<String> al = new ArrayList<String>();
					while (rs.next()){
						four = rs.getString("Name");
						al.add(four);
					}
					root.put("fours", al);
					fo = 1;
				}
				root.put("fo", fo);
				
				int fi = 0;
				if (five!=null &&five.equalsIgnoreCase("query5")){
					ResultSet rs = PersistImp.five();
					five = null;
					ArrayList<String> al = new ArrayList<String>();
					while (rs.next()){
						five = rs.getString("Name");
						al.add(five);
					}
					root.put("fives", al);
					fi = 1;
				}
				root.put("fi", fi);
				
				int i = 0;
				if (input!=null && !input.equalsIgnoreCase("")){
					ResultSet rs = PersistImp.inputField(input);
					ResultSetMetaData rsmd = rs.getMetaData();
					int columnsNumber = rsmd.getColumnCount();
					ArrayList<String> alh = new ArrayList<String>();
					root.put("colsNums", columnsNumber);
					for (int index = 1; index <columnsNumber; index++){
						String sp = rsmd.getColumnLabel(index);
						alh.add(sp);
					}
					System.out.println("HAPPENS");
					root.put("inputFHS", alh);
					int temp = columnsNumber;
					ArrayList<String> al = new ArrayList<String>();
					int countt = 1;
					while(rs.next()){
						if (countt==100){
							break;
						}
						String sp = rs.getString((temp%columnsNumber)+1);
						al.add(sp);
						temp++;
						countt++;
					}
					int rowsNumber = al.size()/columnsNumber;
					System.out.println(rowsNumber);
					if(rowsNumber>100)
						rowsNumber=100;
					root.put("rowsInputs", rowsNumber);
					root.put("inputFS", al);
					i = 1;
				}
				root.put("i", i);
				
				
				runTemplate(request,response,"result"); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void runTemplate(HttpServletRequest request, HttpServletResponse response, String name) throws SQLException
    {
        // You can use this structure for all of your objects to be sent to browser
	 Template template = null;
     String templateName = name + ".ftl";     
     try
     {
        template = cfg.getTemplate(templateName);
        response.setContentType("text/html");
        Writer out = response.getWriter();
        template.process(root, out);
          
     }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (TemplateException e)
        {
            e.printStackTrace();
        }
       // DatabaseAccess.closeConnection(con);
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
