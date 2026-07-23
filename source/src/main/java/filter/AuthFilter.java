package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dto.UserDTO;

@WebFilter("/servlet")
public class AuthFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("ログインチェック");
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		//セッションスコープにuser_idが格納されているかのチェック
		HttpSession session = httpRequest.getSession();
		
		//セッションスコープからUserDTOを取り出す
		UserDTO user = (UserDTO) session.getAttribute("user");
		
		//userがnullの場合、ログイン画面に戻す
		if (user == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
			return;
		}
	}
}