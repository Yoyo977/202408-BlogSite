package blogsite.com.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

/**
 * @data //get,set生成方法
 * @NoArgsConstructor //空のコンストラク生成
 * @AllArgsConstructor //ALLコンストラク生成
 * @RequiredArgsConstructor //作成@NonNull的コンストラク
 * @NonNull //必须变数作成コンストラク
 **/
public class Admin {
	// admin_id設定
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long adminId;

	// admin_name設定
	private String adminName;

	// admin_email設定
	private String adminEmail;

	// password設定
	private String password;

	// 空のコンストラク作成
	public Admin() {
	}

	// ALLコンストラク作成
	public Admin(String adminName, String adminEmail, String password) {
		this.adminName = adminName;
		this.adminEmail = adminEmail;
		this.password = password;
	}

	// get,set作成
	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
