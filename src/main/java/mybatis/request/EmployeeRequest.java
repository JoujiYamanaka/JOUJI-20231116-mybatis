package mybatis.request;

import java.util.Objects;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

import org.apache.commons.validator.routines.EmailValidator;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.StringUtils;

import lombok.Data;

@Data
public class EmployeeRequest {

	/** ID */
	@Max(value = 2147483646, message = "{errors.employee.id.max}")
	private Long id;

	/** 社員番号 */
	@Length(max = 255, message = "{errors.employee.employeeNumber.maxLength}")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "{errors.employee.employeeNumber.Pattern}")
	private String employeeNumber;

	/** 所属 */
	@Length(max = 255, message = "{errors.employee.affiliation.maxLength}")
	private String affiliation;

	/** 氏名 */
	@Length(max = 255, message = "{errors.employee.name.maxLength}")
	private String name;

	/** 性別 */
	@Length(max = 10, message = "{errors.employee.gender.maxLength}")
	private String gender;

	/** 誕生日 */
	@Pattern(regexp = "^\\d{4}\\/\\d{2}\\/\\d{2}$", message = "{errors.employee.birthday.Pattern}")
	private String birthday;

	/** 郵便番号 */
	@Pattern(regexp = "^\\d{3}\\-?\\d{4}$", message = "{errors.employee.zipcode.Pattern}")
	private String zipcode;

	/** 電話番号 */
	@Length(max = 18, message = "{errors.employee.telephoneNumber.maxLength}")
	@Pattern(regexp = "^[0-9]*$", message = "{errors.employee.telephoneNumber.Pattern}")
	private String telephoneNumber;

	/** メールアドレス */
	@Length(max = 254, message = "{errors.employee.mailAddress.maxLength}")
	private String mailAddress;

	/**
	 * 検索条件未入力チェック
	 * 
	 * @return true：検索条件入力済、false：検索条件未入力
	 */
	@AssertTrue(message = "{errors.EmployeeInfoGetRequest.isSearchCriteriaInputCheck}")
	private boolean isSearchCriteriaInputCheck() {

		// nullか空か半角スペースかどうかを判定
		if (Objects.isNull(id) && !StringUtils.hasText(employeeNumber) && !StringUtils.hasText(affiliation)
				&& !StringUtils.hasText(name) && !StringUtils.hasText(zipcode) && !StringUtils.hasText(telephoneNumber)
				&& !StringUtils.hasText(mailAddress)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * メールアドレスチェック
	 * 
	 * @return true：メールアドレス、false：メールアドレスではない メールアドレスがnullか空か半角スペースの場合は判定しないので、true
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