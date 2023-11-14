package mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import mybatis.entity.EmployeeEntity;
import mybatis.model.EmployeeModel;
import mybatis.request.EmployeeRequest;

@Mapper
@Transactional
public interface EmployeeMapper {

	/** 社員情報登録 */
	public Long insertEmployee(EmployeeModel employeeModel);

	/** 社員情報取得 */
	public List<EmployeeEntity> getEmployeeList(EmployeeRequest employeeRequest);

	/** 社員番号重複チェック */
	public Long hasDuplicationCheck(@Param("id") Long id, @Param("employeeNumber") String employeeNumber);
}
