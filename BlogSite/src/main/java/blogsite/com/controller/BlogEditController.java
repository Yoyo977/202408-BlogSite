package blogsite.com.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blogsite.com.models.entity.Admin;
import blogsite.com.models.entity.Blog;
import blogsite.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

//@Controllerアノテーションの主な役割は、主にHTMLページなどのビューを生成すること(司令塔)
@Controller
public class BlogEditController {
	// @Autowiredアノテーションをつけて、自動的にインターフェースを実装して、インスタンス化させて、ControllerでBlogServiceを使えるようにします
	@Autowired
	private BlogService blogService;

	@Autowired
	/**
	 * sessionは、ユーザー情報を、一時的にサーバー側で保持される、どのページも共通もデータを使いたい時にもちいる機能、（名前、値）という形式で保管される
	 * sessionを使わない：（重複Loginしないと、ブログ登録画面を表示しません） Login → ブログ一覧 → Login →ブログ登録
	 * sessionを使う：（何度もLoginせずに、直接ブログ登録画面を表示すること） Login → ブログ一覧 → ブログ登録
	 **/
	private HttpSession session;

	// 編集画面の表示処理(編集画面から送信されたデータを受け取る)
	@GetMapping("/blog/edit/{blogId}")
	// @PathVariable ブログIDを使ってデータベースからブログ情報を取得
	public String getBlogEditPage(@PathVariable Long blogId, Model model) {
		// セッションからログインしている人の情報をadminという変数に格納
		Admin admin = (Admin) session.getAttribute("loginAdminInfo");
		// もしadmin==null ログイン画面にリダイレクトする
		if (admin == null) {
			return "redirect:/admin/login";
		} else {
			// 編集画面に表示させる情報を変数に格納
			Blog blog = blogService.blogEditCheck(blogId);
			// もしblog==nullだったら、商品一覧ページにリダイレクトする
			// そうでない場合、編集画面に編集する内容を渡す
			// 編集画面を表示
			if (blog == null) {
				return "redirect:/blog/list";
			} else {
				model.addAttribute("adminName", admin.getAdminName());
				model.addAttribute("blog", blog);
				return "blog_edit.html";
			}
		}
	}

	// 更新処理をする
	@PostMapping("/blog/edit/process")
	public String blogUpdate(@RequestParam String blogName, @RequestParam String blogCategory,
			@RequestParam MultipartFile blogImage, @RequestParam String blodDescription, @RequestParam Long blogId) {
		// セッションからログインしている人の情報をadminという変数に格納
		Admin admin = (Admin) session.getAttribute("loginAdminInfo");
		// もし、admin == nullだったら、ログイン画面にリダイレクトする
		// そうでない場合、ファイルの保存
		/**
		 * 現在の日時情報を元に、ファイル名を作成しています。SimpleDateFormatクラスを使用して、日時のフォーマットを指定している。
		 * 具体的には、"yyyy-MM-dd-HH-mm-ss-"の形式でフォーマットされた文字列を取得している。
		 * その後、blogImageオブジェクトから元のファイル名を取得し、フォーマットされた日時文字列と連結して、fileName変数に代入
		 **/
		// もし、blogUpdateの結果がtrueの場合は、商品一覧にリダイレクト
		// そうでない場合、商品編集画面にリダイレクトする
		if (admin == null) {
			return "redirect:/admin/login";
		} else {
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())
					+ blogImage.getOriginalFilename();
			try {
				Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/blog-img/" + fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (blogService.blogUpdate(blogId, blogName, blogCategory, fileName, blodDescription, admin.getAdminId())) {
				return "redirect:/blog/list";
			} else {
				return "redirect:/blog/edit" + blogId;

			}
		}
	}
}
