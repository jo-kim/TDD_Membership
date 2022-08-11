package com.ko.tdd.membership.app.membership.dto;

import com.ko.tdd.membership.app.enums.MembershipType;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class MembershipRequest {

    @NotNull
    @Min(0)
    private final Integer point;

    @NotNull
    private final MembershipType membershipType;
}
