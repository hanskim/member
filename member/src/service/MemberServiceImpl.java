package service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.MemberBean;
import dao.MemberDao;



public class MemberServiceImpl implements MemberService{
	/*
     * DAO �� �̱��� �������� �� �ϳ��� �ν��Ͻ��� �����Ѵٸ�
     * �װ��� ����ϴ� ���񽺵� �̱������� �����ؾ� �Ѵ�.
     * �׷��� ������ ���� ���� ���¿��� �ϳ��� �ν��Ͻ��� ����ϰԵǾ�
     * ���Ӻҷ� ������ �߻��Ѵ�.
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
            msg = "��ġ�ϴ� ID�� �����ϴ�.";
        }else{
            
            if(!(map.get("password").equals(password))){
                msg = "����� ��ġ���� �ʽ��ϴ�.";
            }else{
                msg = "ȯ���մϴ�..";
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
