package jp.co.minato.pmw.mapper.entity;

import lombok.Data;

@Data
public class UserEntity extends AbstractEntity {
	private Integer id;
	private String name;
	private String loginId;
	private String password;

}
