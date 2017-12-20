package com.simplus.challenge.persistence;

import java.util.List;

import com.simplus.challenge.model.User;

/**
 * Interface criada para tornar transparente uma futura altera��o da camada de
 * persist�ncia para banco de dados.
 *
 */

public interface UserRepository {
	
	public Long create(User user);

	public User findByID(final Long id);

	public Boolean userNameAlreadyExist(final String userName);
	
	public User update(final User user);

	public void delete(final Long id);

	public List<User> findAll();
	
}
