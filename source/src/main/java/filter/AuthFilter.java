package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.UserDTO;

@WebFilter("/Controller")
public class AuthFilter implements Filter {
	
	@Override
	public void doFilter(
			javax.servlet.ServletRequest req, javax.servlet.ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		System.out.println("ログインチェック");
		
		request.setCharacterEncoding("UTF-8");
		String pageId = request.getParameter("page_id");
		String buttonId = request.getParameter("button_id");
		
		// ログイン処理だけ認証チェック対象外
		if (pageId == null && buttonId == null) {
			System.out.println("ログインチェックスキップ:null");
			chain.doFilter(request, response);
			return;
		}
		if ("L001".equals(pageId) && "ログイン".equals(buttonId)) {
			System.out.println("ログインチェックスキップ:ログイン");
			chain.doFilter(request, response);
			return;
		}
		
		// セッションスコープにuser情報が格納されているかのチェック
		HttpSession session = request.getSession();
		UserDTO user = (UserDTO) session.getAttribute("user");
		
		// userがnullの場合、ログイン画面に戻す
		if (user == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		// ログイン済みなら次の処理へ
		chain.doFilter(request, response);
	}
}