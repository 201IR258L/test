package jp.co.minato.pmw.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class AbstractDto {
	private Boolean deleted;
	private Integer createUserId;
	private Timestamp createTimestamp;
	private Integer updateUserId;
	private Timestamp updateTimestamp;
	private Integer versionNo;

}
