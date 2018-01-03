package org.smart4j.sample.service.impl;

import java.util.List;
import java.util.Map;

import org.smart4j.framework.orm.DataSet;
import org.smart4j.framework.tx.annotation.Service;
import org.smart4j.framework.tx.annotation.Transaction;
import org.smart4j.sample.entity.User;
import org.smart4j.sample.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	public List<User> findUserList() {
		return DataSet.selectList(User.class);
	}

	public User findUser(long id) {
		return DataSet.select(User.class, "id = ?", id);
	}

	@Transaction
	public boolean saveUser(Map<String, Object> fieldMap) {
		return DataSet.insert(User.class, fieldMap);
	}

	@Transaction
	public boolean updateUser(long id, Map<String, Object> fieldMap) {
		return DataSet.update(User.class, fieldMap, "id = ?", id);
	}

	@Transaction
	public boolean deleteUser(long id) {
		return DataSet.delete(User.class, "id = ?", id);
	}

   
}
