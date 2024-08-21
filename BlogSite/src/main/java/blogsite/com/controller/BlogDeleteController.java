package blogsite.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import blogsite.com.models.entity.Admin;
import blogsite.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogDeleteController {
	@Autowired
	private HttpSession session;

	@Autowired
	private BlogService blogService;

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
