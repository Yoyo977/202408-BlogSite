package blogsite.com.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

//@Controllerアノテーションの主な役割は、主にHTMLページなどのビューを生成すること(司令塔)
@Controller
public class AdminLogoutController {
	// @Autowiredアノテーションをつけて、自動的にインターフェースを実装して、インスタンス化させて、ControllerでHttpSessionを使えるようにします
	@Autowired
	/**
	 * sessionは、ユーザー情報を、一時的にサーバー側で保持される、どのページも共通もデータを使いたい時にもちいる機能、（名前、値）という形式で保管される
	 * sessionを使わない：（重複Loginしないと、ブログ登録画面を表示しません） Login → ブログ一覧 → Login →ブログ登録
	 * sessionを使う：（何度もLoginせずに、直接ブログ登録画面を表示すること） Login → ブログ一覧 → ブログ登録
	 **/
	private HttpSession session;

	// ログアウト処理ログアウト画面の表示
	@GetMapping("/admin/logout")
	public String adminLogout() {
		// ログアウトの時Loginの情報を残らないように、セッションの無効化します
		session.invalidate();
		return "redirect:/admin/login";
	}
}
