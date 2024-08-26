package blogsite.com.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blogsite.com.models.entity.Admin;

@Repository
public interface AdminDao extends JpaRepository<Admin, Long> {
	// 保存処理と更新処理 insertとupate
	Admin save(Admin admin);

	// SELECT * FROM admin WHERE admin_email = ? (単一取得)
	// 用途：管理者の登録処理をする時に、同じメールアドレスがあったらば登録させないようにする
	// 1行だけしかレコードは取得できない
	Admin findByAdminEmail(String adminEmail);

	// SELECT * FROM admin WHERE admin_email = ? AND password = ?(単一取得)
	// 用途：ログイン処理に使用。入力したメールアドレスとパスワードが一致してる時だけデータを取得する
	Admin findByAdminEmailAndPassword(String adminEmail, String password);
}
