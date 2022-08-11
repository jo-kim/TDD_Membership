package com.ko.tdd.membership.app.membership.service;

import com.ko.tdd.membership.app.enums.MembershipType;
import com.ko.tdd.membership.app.membership.dto.MembershipDetailResponse;
import com.ko.tdd.membership.app.membership.dto.MembershipResponse;
import com.ko.tdd.membership.app.membership.entity.Membership;
import com.ko.tdd.membership.app.membership.repository.MembershipRepository;
import com.ko.tdd.membership.exception.MembershipErrorResult;
import com.ko.tdd.membership.exception.MembershipException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository membershipRepository;
    public MembershipResponse addMembership(final String userId, final MembershipType membershipType, final Integer point) {
        final Membership result = membershipRepository.findByUserIdAndMembershipType(userId, membershipType);
        if (result != null) {
            throw new MembershipException(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER);
        }

        final Membership membership = Membership.builder()
                .userId(userId)
                .membershipType(membershipType)
                .point(point)
                .build();

        final Membership saveMembership = membershipRepository.save(membership);


        return MembershipResponse.builder()
                .id(saveMembership.getId())
                .membershipType(saveMembership.getMembershipType())
                .build();

    }

    public List<MembershipDetailResponse> getMembershipList(final String userId) {
        final List<Membership> membershipList = membershipRepository.findAllByUserId(userId);

        return membershipList.stream()
                .map(v -> MembershipDetailResponse.builder()
                        .id(v.getId())
                        .membershipType(v.getMembershipType())
                        .point(v.getPoint())
                        .createdAt(v.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    public MembershipDetailResponse getMembership(final Long membershipId, final String userId) {

        final Optional<Membership> optionalMembership = membershipRepository.findById(membershipId);
        final Membership membership = optionalMembership.orElseThrow(() -> new MembershipException(MembershipErrorResult.MEMBERSHIP_NOT_FOUND));
        if (!membership.getUserId().equals(userId)) {
            throw new MembershipException(MembershipErrorResult.NOT_MEMBERSHIP_OWNER);
        }

        return MembershipDetailResponse.builder()
                .id(membership.getId())
                .membershipType(membership.getMembershipType())
                .point(membership.getPoint())
                .createdAt(membership.getCreatedAt())
                .build();
    }
}
