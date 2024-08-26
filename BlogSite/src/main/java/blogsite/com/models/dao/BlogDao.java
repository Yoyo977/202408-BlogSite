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

	// ブログ一覧を表示
	// SELECT * FROM blog (複数取得)
	// 用途：ブログ一覧を表示させるときに使用
	List<Blog> findAll();

	// SELECT * FROM blog WHERE category_name = ?
	// 用途：ブログの登録チェックに使用（同じ商品名がブログされないようにチェックに使用）
	Blog findByCategoryName(String categoryName);

	// SELECT * FROM blog WHERE blogId = ?
	// 用途：編集画面を表示する際に使用
	Blog findByBlogId(Long blogId);

	// DLETE FROM products WHERE product_id = ?
	// 用途：削除使用します
	void deleteByBlogId(Long blogId);

}
