package jp.co.minato.pmw.dto;

import lombok.Data;

@Data
public class UserDto extends AbstractDto {
	private Integer id;
	private String name;
	private String loginId;
	private String password;

}
