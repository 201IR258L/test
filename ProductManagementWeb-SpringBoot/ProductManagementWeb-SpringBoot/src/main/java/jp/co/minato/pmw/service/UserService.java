package jp.co.minato.pmw.service;

import java.util.List;

import jp.co.minato.pmw.dto.UserDto;
import jp.co.minato.pmw.mapper.entity.UserEntity;

/**
 * ユーザ情報サービス
 *
 */
public interface UserService {

	public void issue(UserDto dto);
//
//	public UserEntity copyUserDtoToUserEntity(UserDto dto);

	public List<UserEntity> find(UserDto dto);
}
