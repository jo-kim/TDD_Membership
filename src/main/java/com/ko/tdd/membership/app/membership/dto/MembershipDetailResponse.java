package com.ko.tdd.membership.app.membership.dto;

import com.ko.tdd.membership.app.enums.MembershipType;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
public class MembershipDetailResponse {

    private final Long id;
    private final MembershipType membershipType;
    private final LocalDateTime createdAt;
    private final Integer point;
}
