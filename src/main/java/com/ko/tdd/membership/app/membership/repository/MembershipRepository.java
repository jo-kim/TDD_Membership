package com.ko.tdd.membership.app.membership.repository;

import com.ko.tdd.membership.app.enums.MembershipType;
import com.ko.tdd.membership.app.membership.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembershipRepository extends JpaRepository<Membership, Long> {


   Membership findByUserIdAndMembershipType(final String userId, final MembershipType membershipType);

    List<Membership> findAllByUserId( final String userId);

}
