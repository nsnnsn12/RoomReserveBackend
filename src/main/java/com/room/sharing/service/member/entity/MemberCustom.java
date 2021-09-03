package com.room.sharing.service.member.entity;

import com.room.sharing.service.member.dto.MemberQueryDto;
import java.util.List;

public interface MemberCustom {

  List<Member> find(MemberQueryDto memberQueryDto);

}
