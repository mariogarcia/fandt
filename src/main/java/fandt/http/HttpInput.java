package fandt.http;

import java.util.Map;
import java.util.concurrent.Future;

import fandt.Input;

public class HttpInput implements Input<String> {

    @Override
    public Future<String> produce(Map<String,String> configuration) {
        return null;
    }
}
