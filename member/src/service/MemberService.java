package service;

import java.util.List;

import bean.MemberBean;

public interface MemberService {
	  /*회원가입*/
    public int join(MemberBean bean);
    /*로그인*/
    public String login(String id, String password);
    /*회원목록*/
    public List<MemberBean> getList();
    

}
