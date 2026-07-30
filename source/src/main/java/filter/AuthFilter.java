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

@WebFilter("/Controller")
public class AuthFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("ログインチェック");
		
		request.setCharacterEncoding("UTF-8");

		String pageId = request.getParameter("pageId");
		String buttonId = request.getParameter("buttonId");
		// ログイン処理だけ認証チェック対象外
		if (pageId==null && buttonId==null) {
			System.out.println("ログインチェックスキップnull");
			chain.doFilter(request, response);
			return;
		}
		if (pageId.equals("L001") && buttonId.equals("ログイン")) {
			System.out.println("ログインチェックスキップ:ログイン");
			chain.doFilter(request, response);
			return;
		}
		
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