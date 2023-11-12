package mybatis.request;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.util.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class EmployeeRegistRequest {
	/** 社員番号 */
	@NotBlank(message = "{errors.employee.employeeNumber.notBlank}")
	@Length(max = 255, message = "{errors.employee.employeeNumber.maxLength}")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "{errors.employee.employeeNumber.Pattern}")
	private String employeeNumber;

	/** 所属 */
	@Length(max = 255, message = "{errors.employee.affiliation.maxLength}")
	private String affiliation;

	/** 氏名 */
	@NotBlank(message = "{errors.employee.name.notBlank}")
	@Length(max = 255, message = "{errors.employee.name.maxLength}")
	private String name;

	/** 性別 */
	@Length(max = 10, message = "{errors.employee.gender.maxLength}")
	private String gender;

	/** 誕生日 */
	@Pattern(regexp = "^\\d{4}\\/\\d{2}\\/\\d{2}$", message = "{errors.employee.birthday.Pattern}")
	private String birthday;

	/** 郵便番号 */
	@NotBlank(message = "{errors.employee.zipcode.notBlank}")
	@Pattern(regexp = "^\\d{3}\\-?\\d{4}$", message = "{errors.employee.zipcode.Pattern}")
	private String zipcode;

	/** 住所 */
	@NotBlank(message = "{errors.employee.address.notBlank}")
	@Length(max = 255, message = "{errors.employee.address.maxLength}")
	private String address;

	/** 電話番号 */
	@NotBlank(message = "{errors.employee.telephoneNumber.notBlank}")
	@Length(max = 18, message = "{errors.employee.telephoneNumber.maxLength}")
	@Pattern(regexp = "^[0-9]*$", message = "{errors.employee.telephoneNumber.Pattern}")
	private String telephoneNumber;

	/** メールアドレス */
	@NotBlank(message = "{errors.employee.mailAddress.notBlank}")
	@Length(max = 254, message = "{errors.employee.mailAddress.maxLength}")
	private String mailAddress;

	/** 入社日 */
	@NotBlank(message = "{errors.employee.dateOfEmployment.notBlank}")
	@Pattern(regexp = "^\\d{4}\\/\\d{2}\\/\\d{2}$", message = "{errors.employee.dateOfEmployment.Pattern}")
	private String dateOfEmployment;

	/**
	 * メールアドレスチェック
	 * 
	 * @return true：メールアドレス、false：メールアドレスではない
	 *         メールアドレスがnullか空か半角スペースのときはすでに判定しているので、true
	 */
	@AssertTrue(message = "{errors.employee.mailAddress.isCheckEmailAddress}")
	private boolean isCheckEmailAddress() {

		// メールアドレスがnullか空か半角スペースかどうかを判定
		if (!StringUtils.hasText(mailAddress)) {
			return true;
		} else {
			// メールアドレスかどうかを判定
			if (EmailValidator.getInstance().isValid(mailAddress)) {
				return true;
			} else {
				return false;
			}
		}
	}
}