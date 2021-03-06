package com.room.sharing.service.web.member;

import com.room.sharing.service.member.FindMemberUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;

import static com.room.sharing.service.utils.RestDocFormatGenerator.getDateTimeFormat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("?????? ?????? v1")
@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@SpringBootTest
@ActiveProfiles("test")
public class MemberRestDoc {

  final MemberMockHelper helper = new MemberMockHelper();

  MockMvc mockMvc;

  @MockBean
  FindMemberUseCase findMemberUseCase;

  @BeforeEach
  void setUp(WebApplicationContext webApplicationContext,
             RestDocumentationContextProvider restDocumentation) {
      this.mockMvc = MockMvcBuilders
              .webAppContextSetup(webApplicationContext)
              .apply(documentationConfiguration(restDocumentation))
              .alwaysDo(print())
              .addFilters(new CharacterEncodingFilter("UTF-8", true))
              .build();
  }

  @Test
  @DisplayName("?????? ?????? ?????? API")
  void find() throws Exception {

      var memberResponses = helper.getFindMembers();
      given(findMemberUseCase.find(any()))
              .willReturn(memberResponses);

      // when
      final ResultActions resultActions = mockMvc.perform(
              get("/v1/members")
                      .param("name", "?????????")
                      .param("fromDateTime", "2021-08-23T23:22:22")
      );

      // then
      resultActions
              .andExpect(status().isOk())
              .andDo(
                      document("members/{method-name}",
                              preprocessRequest(prettyPrint()),
                              preprocessResponse(prettyPrint()),
                              requestParameters(
                                      parameterWithName("name")
                                              .description("??????"),
                                      parameterWithName("fromDateTime")
                                              .description("?????? ?????????")
                                ),
                                responseFields(
                                        beneathPath("data").withSubsectionId("data"),
                                        resultsSnippets()
                                )
                        ));
    }
    private List<FieldDescriptor> resultsSnippets() {
      return List.of(
              fieldWithPath("id").type(NUMBER).description("?????? ??????"),
              fieldWithPath("name").type(STRING).description("?????? ??????"),
              fieldWithPath("age").type(NUMBER).description("?????? ??????"),
              fieldWithPath("createdDateTime").type(STRING).description("?????? ?????????").attributes(getDateTimeFormat())
      );
  }
}
