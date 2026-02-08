package com.test.boot.batch;

import org.springframework.batch.item.ItemProcessor;
//ItemProcessor<String,String> means it takes String and process String

/*
The Spring Batch ItemProcessor<I, O>
I: The type of the input item read by the ItemReader.
O: The type of the output item passed to the ItemWriter
    (which can be the same type as
 */
public class Processor implements ItemProcessor<String,String> {

    @Override
    public String process(String item) throws Exception {
        System.out.println("inside process...!");
        return "PROCESSED "+item.toUpperCase();
    }
}
