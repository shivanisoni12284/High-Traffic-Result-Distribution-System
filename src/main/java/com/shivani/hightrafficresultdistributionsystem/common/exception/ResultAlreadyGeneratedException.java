package com.shivani.hightrafficresultdistributionsystem.common.exception;

import static io.lettuce.core.pubsub.PubSubOutput.Type.message;

public class ResultAlreadyGeneratedException extends RuntimeException{

    public ResultAlreadyGeneratedException(String message){
        super(message);
    }
}
