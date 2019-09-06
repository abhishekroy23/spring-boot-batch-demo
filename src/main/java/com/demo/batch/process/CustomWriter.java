package com.demo.batch.process;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.batch.entity.User;
import com.demo.batch.repository.UsersRepository;

@Component
public class CustomWriter implements ItemWriter<User>{
	
	@Autowired
	UsersRepository userRepository;

	@Override
	public void write(List<? extends User> items) throws Exception {
		userRepository.saveAll(items);		
	}

}
