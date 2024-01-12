package jp.co.minato.pmw.mapper.entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
public abstract class AbstractEntity {
	private Boolean deleted;
	private Integer createUserId;
	private Timestamp createTimestamp;
	private Integer updateUserId;
	private Timestamp updateTimestamp;
	private Integer versionNo;

}
