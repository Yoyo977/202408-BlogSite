package blogsite.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import blogsite.com.models.entity.Admin;
import blogsite.com.models.entity.Blog;
import blogsite.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogListController {
	@Autowired
	/**
	 * sessionは、ユーザー情報を、一時的にサーバー側で保持される、どのページも共通もデータを使いたい時にもちいる機能、（名前、値）という形式で保管される
	 * sessionを使わない：（重複Loginしないと、ブログ登録画面を表示しません） Login → ブログ一覧 → Login →ブログ登録
	 * sessionを使う：（何度もLoginせずに、直接ブログ登録画面を表示すること） Login → ブログ一覧 → ブログ登録
	 **/
	private HttpSession session;

	@Autowired
	private BlogService blogService;

	// 商品一覧画面を表示する
	@GetMapping("/blog/list")
	public String getBlogList(Model model) {
		// セッションからログインしている人の情報を取得
		Admin admin = (Admin) session.getAttribute("loginAdminInfo");
		// もし、admin==null ログイン画面にリダイレクトする
		// そうでない場合
		// ログインしている人の名前の情報を画面に渡して商品一覧のhtmlを表示。
		if (admin == null) {
			return "redirect:/admin/login";
		} else {
			// 商品の情報を取得する。
			List<Blog> blogList = blogService.selectAllBlogList(admin.getAdminId());
			model.addAttribute("adminName", admin.getAdminName());
			model.addAttribute("blogList", blogList);
			return "blog_list.html";
		}
	}
}
