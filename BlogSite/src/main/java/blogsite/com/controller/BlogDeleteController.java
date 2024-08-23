package blogsite.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import blogsite.com.models.entity.Admin;
import blogsite.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

//@Controllerアノテーションの主な役割は、主にHTMLページなどのビューを生成すること(司令塔)
@Controller
public class BlogDeleteController {
	//@Autowiredアノテーションをつけて、自動的にインターフェースを実装して、インスタンス化させて、ControllerでHttpSessionを使えるようにします
	@Autowired
	private HttpSession session;

	@Autowired
	private BlogService blogService;

	// 削除処理(削除画面から送信されたデータを受け取る)
	@PostMapping("/blog/delete")
	public String blogDelete(Long blogId) {
		// セッションからログインしている人の情報をadminという変数に格納
		Admin admin = (Admin) session.getAttribute("loginAdminInfo");
		// もしadmin==null ログイン画面にリダイレクトする
		if (admin == null) {
			return "redirect:/admin/login";
		} else {
			// もしdeleteblogの結果がtrueの時に、商品一覧にリダイレクトする
			// そうでない場合、 編集画面にリダイレクトする
			if (blogService.deleteblog(blogId)) {
				return "redirect:/blog/list";
			} else {
				return "redirect:/blog/edit"+blogId;
			}
		}

	}
}
