package jp.co.minato.pmw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.minato.pmw.mapper.entity.UserEntity;

/**
 * ユーザDAO
 */
@Mapper
public interface UserMapper {

	public void insert(UserEntity entity);

	public List<UserEntity> find(UserEntity condition);

//	public String getFindSql(UserEntity condition);

}