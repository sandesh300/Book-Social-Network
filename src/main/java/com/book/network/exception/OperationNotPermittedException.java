package com.book.network.exception;

import org.apache.logging.log4j.message.Message;

public class OperationNotPermittedException extends RuntimeException{
    public OperationNotPermittedException(String Message) {
        super(Message);
    }
}
