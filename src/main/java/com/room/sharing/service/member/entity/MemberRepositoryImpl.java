package com.room.sharing.service.member.entity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.room.sharing.service.member.dto.MemberQueryDto;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberCustom {
  private final JPAQueryFactory queryFactory;
  private final QMember member = QMember.member;

  @Override
  public List<Member> find(MemberQueryDto memberQueryDto) {
    return queryFactory
            .selectFrom(member)
            .where(
                    member.name.eq(memberQueryDto.getName()),
                    member.createdDateTime.between(
                            memberQueryDto.getFromDateTime(),
                            memberQueryDto.getToDateTime()
                    )
            )
            .fetch();
  }
}
