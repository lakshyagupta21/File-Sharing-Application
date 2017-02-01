package src;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cjc.SqlConnection;
import cjc.UserName;

/**
 * Servlet implementation class Search
 */

public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SqlConnection s = new SqlConnection();
            String pid = request.getParameter("pid");
            ResultSet rs;
			try {
				rs = s.searchUser(pid);
				ArrayList<String> pid_list = new ArrayList();
				   while(rs.next()){
				   pid_list.add(rs.getString("USERNAME"));
				   }
				   pid_list.remove(UserName.USERNAME);
				   rs = s.getFriendlist();
				   ArrayList<String> friends = new ArrayList();
				   while(rs.next()){
					  friends.add(rs.getString("FRIEND"));
					  System.out.println("DELETED"+rs.getString("FRIEND"));
					  pid_list.remove(rs.getString("FRIEND"));
					   }
				   request.setAttribute("friendList",friends);
				   request.setAttribute("piList", pid_list);
				   RequestDispatcher view = request.getRequestDispatcher("/Searchview.jsp");
				   view.forward(request, response);
		         
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
           finally{
				try {
					s.closeConnection();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
           }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}