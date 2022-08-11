package com.ko.tdd.membership.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MembershipErrorResult {

    DUPLICATED_MEMBERSHIP_REGISTER(HttpStatus.BAD_REQUEST, "Duplicated Membership Register Request"),
    UNKNOWN_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR,"Unknown Exception"),
    MEMBERSHIP_NOT_FOUND(HttpStatus.NOT_FOUND,"Membership Not Found"),

    NOT_MEMBERSHIP_OWNER(HttpStatus.BAD_REQUEST, "Not a membership owner"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
