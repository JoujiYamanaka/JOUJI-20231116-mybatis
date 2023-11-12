package mybatis.controller;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import mybatis.entity.EmployeeEntity;
import mybatis.mapper.EmployeeMapper;
import mybatis.model.EmployeeModel;
import mybatis.request.EmployeeRegistRequest;
import mybatis.request.EmployeeRequest;
import mybatis.service.EmployeeRegistService;
import mybatis.util.ResponseEntityUtil;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/employee")
@Validated
public class EmployeeController {
	private final EmployeeMapper employeeMapper;
	private final EmployeeRegistService employeeRegistService;

	/**
	 * 1.社員情報登録
	 * 
	 * @param EmployeePostRequest
	 * @return ResponseEntity
	 */
	@PostMapping(value = "/regist", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> registrationEmployee(@RequestBody @Valid EmployeeRegistRequest employeeRegistRequest) {
		EmployeeModel employeeModel = EmployeeModel.fromRequest(employeeRegistRequest);

		return employeeRegistService.regist(employeeModel);
	}

	/**
	 * 2.社員情報取得
	 * 
	 * @param EmployeeGetRequest
	 * @return ResponseEntity
	 */
	@PostMapping(value = "/get", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getEmployeeList(@Validated EmployeeRequest employeeRequest) {
		List<EmployeeEntity> employeeList = employeeMapper.getEmployeeList(employeeRequest);
		return ResponseEntityUtil.setResponseEntity(null, HttpStatus.OK, employeeList);
	}
}