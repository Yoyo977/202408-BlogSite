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

@Controller
public class BlogEditController {
	@Autowired
	private BlogService blogService;

	@Autowired
	private HttpSession session;

	// 編集画面の表示
	@GetMapping("/blog/edit/{blogId}")
	public String getBlogEditPage(@PathVariable Long blogId, Model model) {
		// セッションからログインしている人の情報をadminという変数に格納
		Admin admin = (Admin) session.getAttribute("loginAdminInfo");
		// もしadmin==null ログイン画面にリダイレクトする
		if(admin == null) {
			return "redirect:/admin/login";
		}else {
			// 編集画面に表示させる情報を変数に格納
			Blog blog =blogService.blogEditCheck(blogId);
			// もしblog==nullだったら、商品一覧ページにリダイレクトする
			// そうでない場合、編集画面に編集する内容を渡す
			// 編集画面を表示
			if(blog == null) {
				return "redirect:/blog/list";
			}else {
				model.addAttribute("adminName", admin.getAdminName());
				model.addAttribute("blog", blog);
				return "blog_edit.html";	
			}
		}
		}
	// 更新処理をする
	@PostMapping("/blog/edit/process")
	public String blogUpdate(@RequestParam String blogNmae,
							 @RequestParam String blogCategory,
							 @RequestParam MultipartFile blogImage,
							 @RequestParam String blogDescription,
							 @RequestParam Long blogId) {
		// セッションからログインしている人の情報をadminという変数に格納
		Admin admin = (Admin) session.getAttribute("loginAdminInfo");
		// もし、admin == nullだったら、ログイン画面にリダイレクトする
		// そうでない場合、ファイルの保存
		// もし、blogUpdateの結果がtrueの場合は、商品一覧にリダイレクト
		// そうでない場合、商品編集画面にリダイレクトする
		if(admin == null) {
			return "redirect:/admin/login";
		}else {
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())
					+ blogImage.getOriginalFilename();
			try {
				Files.copy( blogImage.getInputStream(), Path.of("src/main/resources/static/blog-img/"+fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
				if(blogService.blogUpdate(blogId, blogNmae, blogCategory, blogDescription, fileName, admin.getAdminId())) {
					return "redirect:/blog/list";
				}else {
					return "redirect:/blog/edit"+blogId;
				
			}
		}
	}
}







































