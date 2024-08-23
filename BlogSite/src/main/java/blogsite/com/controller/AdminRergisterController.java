package blogsite.com.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blogsite.com.services.AdminService;
//@Controllerアノテーションの主な役割は、主にHTMLページなどのビューを生成すること(司令塔)
@Controller
public class AdminRergisterController {
	//@Autowiredアノテーションをつけて、自動的にインターフェースを実装して、インスタンス化させて、ControllerでAdminServiceを使えるようにします
	@Autowired 
	private AdminService adminService;

	// 登録画面の表示
	@GetMapping("/admin/register")
	public String getAdminRegisterPage() {
		return "admin_register.html";
	}

	// 登録処理(登録画面から送信されたデータを受け取る)
	@PostMapping("/admin/register/process")
	//@RequestParamは、ブラウザからのリクエストの値（パラメータ）を取得することができるアノテーション。
	public String adminegisterProcess(@RequestParam String adminName, @RequestParam String adminEmail,
			@RequestParam String password) {
		// もし、createAdminがtrue admin_login.htmlに遷移
		// そうでない場合、admin_register.htmlにとどまります。
		if (adminService.createAdmin(adminEmail, adminName, password)) {
			return "admin_login.html";
		} else {
			return "admin_register.html";
		}
	}
}
