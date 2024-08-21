package blogsite.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blogsite.com.models.dao.BlogDao;
import blogsite.com.models.entity.Blog;

@Service
public class BlogService {
	@Autowired
	BlogDao blogDao;

	// 商品一覧のチェック
	// もしadminId==null 戻り値としてnull
	// findAll内容をコントローラークラスに渡す
	public List<Blog> selectAllBlogList(Long adminId) {
		if (adminId == null) {
			return null;
		} else {
			return blogDao.findAll();

		}
	}

	// 商品の登録処理チェック
	// もし、findByProductNameが==nullだったら、
	// 保存処理 true
	// そうでない場合 false
	public boolean createBlog(String blogTitle, String categoryName, String blogImage, String article, Long adminId) {
		if (blogDao.findByCategoryName(categoryName) == null) {
			blogDao.save(new Blog(blogTitle, categoryName, blogImage, article, adminId));
			return true;
		} else {
			return false;
		}
	}
	//編集画面を表示するときのチェック
	//もし、blogId == null  null
	//そうでない場合、
	//findByBlogIdの情報をコントローラークラスに渡す
	public Blog blogEditCheck(Long blogId) {
		if(blogId == null) {
			return null;
		}else {
			return blogDao.findByBlogId(blogId);
		}
	}
	//更新処理のチェックの
	//もし、blogId==nullだったら、更新処理はしない
	//false
	//そうでない場合、
	//更新処理をする
	//コントローラークラスからもらった、blogIdを使って、編集する前の、データを取得
	//変更するべきところだけ、セッターを使用してデータの更新をする。
	//trueを返す
	public boolean blogUpdate(Long blogId,
							  String blogNmae,
							  String blogCategory,
							  String blogImage,
							  String blogDescription,
							  Long adminId) {
		if(blogId == null) {
			return false;
		}else {
			Blog blog = blogDao.findByBlogId(blogId);
			blog.setBlogTitle(blogNmae);
			blog.setCategoryName(blogCategory);
			blog.setBlogImage(blogImage);
			blog.setArticle(blogDescription);
			blog.setAdminId(adminId);
			blogDao.save(blog);
			return true;
		}
	}
	//削除処理のチェック
	//もし、コントローラークラスから受け取ったblogIdがnull
	//false
	//そうでない場合、deleteByBlogIdを使って削除処理
	//true
	public boolean deleteblog(Long blogId) {
		if(blogId == null) {
			return false;
		}else {
			blogDao.deleteByBlogId(blogId);
			return true;
		}
	}
}




