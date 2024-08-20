package blogsite.com.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Blog {
	// blog_idの設定
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long blogId;

	// blog_titleの設定
	private String blogTitle;

	// category_nameの設定
	private String categoryName;

	// blog_imageの設定
	private String blogImage;

	// articleの設定
	private String article;

	// admin_idの設定
	private Long adminId;

	// 空のコンストラク作成
	public Blog() {
	}

	// ALLコンストラク作成
	public Blog(String blogTitle, String categoryName, String blogImage, String article, Long adminId) {
		this.blogTitle = blogTitle;
		this.categoryName = categoryName;
		this.blogImage = blogImage;
		this.article = article;
		this.adminId = adminId;
	}

	// get,set作成
	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getBlogImage() {
		return blogImage;
	}

	public void setBlogImage(String blogImage) {
		this.blogImage = blogImage;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

}
