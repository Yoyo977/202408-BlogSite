package blogsite.com.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blogsite.com.models.entity.Blog;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface BlogDao extends JpaRepository<Blog, Long> {
	// 保存処理と更新処理 insertとupate
	Blog save(Blog blog);

	// 商品一覧を表示
	// SELECT * FROM blog (複数取得)
	List<Blog> findAll();

	// 商品の登録チェックに使用
	// SELECT * FROM blog WHERE category_name = ?
	Blog findByCategoryName(String categoryName);

	// 編集画面を表示する際に使用
	// SELECT * FROM blog WHERE blogId = ?
	Blog findByBlogId(Long blogId);

	// 削除使用します
	// DLETE FROM products WHERE product_id = ?
	void deleteByBlogId(Long blogId);

}
