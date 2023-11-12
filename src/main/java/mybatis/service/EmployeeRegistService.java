package mybatis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import mybatis.util.MessageSourceUtil;
import mybatis.util.ResponseEntityUtil;
import lombok.RequiredArgsConstructor;
import mybatis.mapper.EmployeeMapper;
import mybatis.model.EmployeeModel;
import mybatis.response.SuccessCountResponse;

@Service
@RequiredArgsConstructor
public class EmployeeRegistService {
	private final EmployeeMapper employeeMapper;

	
	/**
	 * 社員情報登録処理
	 * 
	 * @param employeeModel	社員情報モデル
	 * @return レスポンス
	 */
	public ResponseEntity<Map<String, Object>> regist(EmployeeModel employeeModel) {

		// 社員番号重複チェック
		List<String> errorMessages = hasDuplicationCheck(null, employeeModel);
		if (errorMessages.size() != 0) {
			return ResponseEntityUtil.setResponseEntity(errorMessages, HttpStatus.NOT_FOUND, null);
		}

		Long count = employeeMapper.insertEmployee(employeeModel);
		return ResponseEntityUtil.setResponseEntity(null, HttpStatus.OK, new SuccessCountResponse(count));
	}

	/**
	 * 社員番号の重複チェック
	 * 
	 * @param id            ID
	 * @param employeeModel 社員情報モデル
	 * @return エラーメッセージリスト
	 */
	private List<String> hasDuplicationCheck(Long id, EmployeeModel employeeModel) {
		List<String> errorMessages = new ArrayList<String>();
		Long count = 0L;

		count = employeeMapper.hasDuplicationCheck(id, employeeModel.getEmployeeNumber());

		// 同じ社員番号が既に登録済の場合は重複エラーを返す
		if (count != 0) {
			errorMessages.add(MessageSourceUtil.getMessage("errors.employee.countCheck"));
		}

		return errorMessages;
	}
}
