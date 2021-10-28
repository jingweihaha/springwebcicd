package com.zjw.login.util;


import com.zjw.login.domain.LoginUser;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

/**
 * @author jingw
 * @created 10/26/2021 1:15 PM
 * @project login
 */
//@Configuration
public class BatchUtil {

    //@Bean
    public Job myJob(JobBuilderFactory jobBuilder, StepBuilderFactory stepBuilder){
        return jobBuilder.get("myJob").start(myStep(stepBuilder)).build();
    }


    //@Bean
    public Step myStep(StepBuilderFactory stepBuilder) {
        return stepBuilder.get("myStep").<LoginUser, LoginUser>chunk(10)
                .reader(myReader())
                .processor(myProcessor())
                .writer(myWriter()).build();
    }

    //@Bean
    public ItemReader<LoginUser> myReader() {
        return new FlatFileItemReaderBuilder<LoginUser>()
                .name("myReader")
                .resource(new ClassPathResource("input/input.csv"))
                .delimited().names("username", "password")
                .targetType(LoginUser.class).build();
    }

    //@Bean
    public ItemProcessor<LoginUser, LoginUser> myProcessor(){
        return new MyProcessor();
    }

    //@Bean
    public ItemWriter<LoginUser> myWriter(){
        BeanWrapperFieldExtractor<LoginUser> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[] {"username", "password"});
        return new FlatFileItemWriterBuilder<LoginUser>()
                .name("myWriter")
                .resource(new FileSystemResource("output/output.csv"))
                .delimited()
                .delimiter(",")
                .fieldExtractor(fieldExtractor)
                .build();

    }


}
