package blogsite.com.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
//@Controllerアノテーションの主な役割は、主にHTMLページなどのビューを生成すること(司令塔)
@Controller
public class AdminLogoutController {
	//@Autowiredアノテーションをつけて、自動的にインターフェースを実装して、インスタンス化させて、ControllerでHttpSessionを使えるようにします
	@Autowired
	private HttpSession session;

	// ログアウト処理
	@GetMapping("/admin/logout")
	public String adminLogout() {
		// セッションの無効化
		session.invalidate();
		return "redirect:/admin/login";
	}
}
