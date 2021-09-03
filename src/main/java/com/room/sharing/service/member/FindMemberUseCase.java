package com.room.sharing.service.member;

import com.room.sharing.service.web.member.response.FindMemberResponse;
import java.util.List;

public interface FindMemberUseCase {

  List<FindMemberResponse> find(FindMemberCommand command);
}
