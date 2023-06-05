package org.example.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.request.method.*;


@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@ToString
public class Requests {
    Request getRequest;
    Request postRequest;
    Request putRequest;
    Request deleteRequest;

        public Requests() {
            this.getRequest = new GetRequest();
            this.postRequest = new PostRequest();
            this.putRequest = new PutRequest();
            this.deleteRequest = new DeleteRequest();
        }
}
