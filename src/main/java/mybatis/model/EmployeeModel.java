package mybatis.model;

import mybatis.util.DateFormatUtil;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mybatis.request.EmployeeRegistRequest;

/**
 * 社員情報モデル
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeModel {
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

	public static EmployeeModel fromRequest(EmployeeRegistRequest request) {
		return EmployeeModel.builder()
				.employeeNumber(request.getEmployeeNumber())
				.affiliation(request.getAffiliation())
				.name(request.getName())
				.gender(request.getGender())
				.birthday(DateFormatUtil.getLocalDate(request.getBirthday()))
				.zipcode(request.getZipcode())
				.address(request.getAddress())
				.telephoneNumber(request.getTelephoneNumber())
				.mailAddress(request.getMailAddress())
				.dateOfEmployment(DateFormatUtil.getLocalDate(request.getDateOfEmployment()))
				.build();
	}
}