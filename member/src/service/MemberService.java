package service;

import java.util.List;

import bean.MemberBean;

public interface MemberService {
	  /*ȸ������*/
    public int join(MemberBean bean);
    /*�α���*/
    public String login(String id, String password);
    /*ȸ�����*/
    public List<MemberBean> getList();
    

}
