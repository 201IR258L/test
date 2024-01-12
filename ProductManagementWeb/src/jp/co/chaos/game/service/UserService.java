package jp.co.chaos.game.service;

import java.sql.Timestamp;
import java.util.List;

import jp.co.chaos.game.dao.UserDao;
import jp.co.chaos.game.dto.UserDto;
import jp.co.chaos.game.entity.UserEntity;

/**
 * ユーザ情報サービス
 *
 */
public class UserService {

	public void issue(UserDto dto) {
		UserDao dao = new UserDao();
		UserEntity entity = copyUserDtoToUserEntity(dto);
		Timestamp now = new Timestamp(System.currentTimeMillis());
		entity.setDeleted(Boolean.FALSE);
		entity.setCreateUserId(0);
		entity.setCreateTimestamp(now);
		entity.setUpdateUserId(0);
		entity.setUpdateTimestamp(now);
		entity.setVersionNo(0);

		dao.insert(entity);
	}

	private static UserEntity copyUserDtoToUserEntity(UserDto dto) {
		UserEntity entity = new UserEntity();
		entity.setName(dto.getName());
		entity.setLoginId(dto.getLoginId());
		entity.setPassword(dto.getPassword());
		return entity;
	}

	public List<UserEntity> find(UserDto dto) {
		UserDao dao = new UserDao();
		UserEntity entity = copyUserDtoToUserEntity(dto);
		return dao.find(entity);
	}
}
