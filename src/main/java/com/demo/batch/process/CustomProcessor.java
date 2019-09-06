package com.demo.batch.process;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.batch.entity.User;
import com.demo.batch.repository.UsersRepository;

@Component
public class CustomProcessor implements ItemProcessor<User, User> {
	
	@Autowired
	UsersRepository usersRepository;

	@Override
	public User process(User item) throws Exception {
		Optional<User> userFromDb = usersRepository.findById(item.getUserId());
		if(userFromDb.isPresent()) {
			item.setAccount(item.getAccount().add(userFromDb.get().getAccount()));
		}
		return item;
	}

}
