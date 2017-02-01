package src;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUpActivity
 */
public class SignUpActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpActivity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SimpleDateFormat k = new SimpleDateFormat("yyyy-MM-dd");
		String firstName , lastName , username , password , emailId , d ;
		Date dob  = null;
		String gender ;
		firstName = request.getParameter("fname");
		lastName = request.getParameter("lname");
		username = request.getParameter("uname");
		password = request.getParameter("pass");
		emailId = request.getParameter("email");
		gender = request.getParameter("gender");
		d = request.getParameter("dob");
		System.out.println("D : " +  d );
		try {
			dob = k.parse(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(firstName.equals("LAKSHYA")){
			request.getSession().setAttribute("userMessage", "Please Enter Again");
			response.sendRedirect(request.getHeader("Referer"));
			
		}
		System.out.println("Firstname : "+firstName  + "\nLastName : " + lastName + "\nUsername : " + username + "\nPassword : "+ password + "\nEmail ID : " + emailId + "\nGender : " + gender + "\nDOB : " + dob );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
