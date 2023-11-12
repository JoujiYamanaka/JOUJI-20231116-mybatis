package mybatis.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessCountResponse {
	/** 成功件数 */
	@JsonProperty("successCount")
	private Long successCount;
}