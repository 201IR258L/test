package jp.co.minato.pmw.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.minato.pmw.dto.UserDto;
import jp.co.minato.pmw.mapper.UserMapper;
import jp.co.minato.pmw.mapper.entity.UserEntity;
import jp.co.minato.pmw.service.UserService;

/**
 * ユーザ情報サービス
 *
 */
@Service
public class UserServiceImpl extends AbstractService implements UserService {

	@Autowired
	private UserMapper userMapper;

	public void issue(UserDto dto) {
		UserEntity entity = copyUserDtoToUserEntity(dto);
		Timestamp now = new Timestamp(System.currentTimeMillis());
		entity.setDeleted(Boolean.FALSE);
		entity.setCreateUserId(0);
		entity.setCreateTimestamp(now);
		entity.setUpdateUserId(0);
		entity.setUpdateTimestamp(now);
		entity.setVersionNo(0);

		userMapper.insert(entity);
	}

	private UserEntity copyUserDtoToUserEntity(UserDto dto) {
		UserEntity entity = new UserEntity();
		entity.setName(dto.getName());
		entity.setLoginId(dto.getLoginId());
		entity.setPassword(dto.getPassword());
		return entity;
	}

	public List<UserEntity> find(UserDto dto) {
		UserEntity entity = copyUserDtoToUserEntity(dto);
		return userMapper.find(entity);
	}
}
