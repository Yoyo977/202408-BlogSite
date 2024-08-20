package blogsite.com.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blogsite.com.models.entity.Admin;

@Repository
public interface AdminDao extends JpaRepository<Admin, Long> {
	// 保存処理と更新処理 insertとupate
	Admin save(Admin admin);

	// SELECT * FROM admin WHERE admin_email = ? (単一取得)
	Admin findByAdminEmail(String adminEmail);

	// SELECT * FROM admin WHERE admin_email = ? AND password = ?(単一取得)
	Admin findByAdminEmailAndPassword(String adminEmail, String password);
}
