package mybatis.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 社員情報
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/** ID */
	private Long id;

	/** 社員番号 */
	private String employeeNumber;

	/** 所属 */
	private String affiliation;

	/** 氏名 */
	private String name;

	/** 性別 */
	private String gender;

	/** 誕生日 */
	private LocalDate birthday;

	/** 郵便番号 */
	private String zipcode;

	/** 住所 */
	private String address;

	/** 電話番号 */
	private String telephoneNumber;

	/** メールアドレス */
	private String mailAddress;

	/** 入社日 */
	private LocalDate dateOfEmployment;

	/** 作成日時 */
	private LocalDateTime createdAt;

	/** 更新日時 */
	private LocalDateTime updatedAt;

	/** 削除日時 */
	private LocalDateTime deletedAt;
}