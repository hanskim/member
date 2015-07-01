package service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.MemberBean;
import dao.MemberDao;



public class MemberServiceImpl implements MemberService{
	/*
     * DAO 가 싱글톤 패턴으로 단 하나의 인스턴스만 리턴한다면
     * 그것을 사용하는 서비스도 싱글톤으로 구성해야 한다.
     * 그러지 않으면 다중 접속 상태에서 하나의 인스턴스만 사용하게되어
     * 접속불량 현상이 발생한다.
     * */
    private static MemberService service = new MemberServiceImpl();
    private MemberServiceImpl() {}
    public static MemberServiceImpl getInstance(){
        return (MemberServiceImpl) service;
    }
    
    Map<String,Object> map = new HashMap<String,Object>();
    MemberBean bean = new MemberBean();
    
    @Override
    public int join(MemberBean bean) {
        return MemberDao.getInstance().join(bean);
    }
 
    @Override
    public String login(String id, String password) {
        String msg = "";
        System.out.println(" ID :" + map.get("id"));
        if(!map.containsKey("id") || !(map.get("id").equals(id))){ 
            msg = "일치하는 ID가 없습니다.";
        }else{
            
            if(!(map.get("password").equals(password))){
                msg = "비번이 일치하지 않습니다.";
            }else{
                msg = "환영합니다..";
            }
        }
        return msg;
    }
 
    @Override
    public List<MemberBean> getList() {
        MemberDao dao = MemberDao.getInstance();
        return dao.getList();
    }
}
