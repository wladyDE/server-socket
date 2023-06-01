package org.example.http_request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.http_request.method.DeleteRequest;
import org.example.http_request.method.GetRequest;
import org.example.http_request.method.PostRequest;
import org.example.http_request.method.PutRequest;


@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@ToString
public class Requests {
    GetRequest getRequest;
    PostRequest postRequest;
    PutRequest putRequest;
    DeleteRequest deleteRequest;

        public Requests() {
            this.getRequest = new GetRequest();
            this.postRequest = new PostRequest();
            this.putRequest = new PutRequest();
            this.deleteRequest = new DeleteRequest();
        }
}
