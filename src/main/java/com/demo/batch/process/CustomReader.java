package com.demo.batch.process;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.Resource;

import com.demo.batch.entity.User;

public class CustomReader extends FlatFileItemReader<User> {

	public CustomReader(Resource resource) {
		super();
		setResource(resource);
		
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(",");
		lineTokenizer.setNames(new String[] {"userId","name","dept","account"});
		lineTokenizer.setStrict(false);
		
		BeanWrapperFieldSetMapper<User> fieldMapper = new BeanWrapperFieldSetMapper<>();
		fieldMapper.setTargetType(User.class);
		
		DefaultLineMapper<User> lineMapper = new DefaultLineMapper<>();
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldMapper);
		
		setLineMapper(lineMapper);
	}
}
