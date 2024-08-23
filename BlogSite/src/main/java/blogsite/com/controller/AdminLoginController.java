package blogsite.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blogsite.com.models.entity.Admin;
import blogsite.com.services.AdminService;
import jakarta.servlet.http.HttpSession;

//@Controllerアノテーションの主な役割は、主にHTMLページなどのビューを生成すること(司令塔)
@Controller
public class AdminLoginController {
	//@Autowiredアノテーションをつけて、自動的にインターフェースを実装して、インスタンス化させて、ControllerでAdminServiceを使えるようにします
	@Autowired
	private AdminService adminService;

	// Sessionが使えるように宣言
	@Autowired
	private HttpSession session;

	// ログイン画面の表示メソッド
	@GetMapping("/admin/login")
	public String getAdminLoginPage() {
		return "admin_login.html";
	}

	// ログイン処理(ログイン画面から送信されたデータを受け取る)
	@PostMapping("/admin/login/process")
	public String adminLoginProcess(@RequestParam String adminEmail, @RequestParam String password) {
		// loginCheckメソッドを呼び出してその結果をadminという変数に格納
		Admin admin = adminService.loginCheck(adminEmail, password);
		// もし、admin==nullログイン画面にとどまります。
		// そうでない場合は、sessionにログイン情報に保存
		// 商品一覧画面にリダイレクトする/blog/list
		if (admin == null) {
			return "admin_login.html";
		} else {
			session.setAttribute("loginAdminInfo", admin);
			return "redirect:/blog/list";
		}
	}

}
