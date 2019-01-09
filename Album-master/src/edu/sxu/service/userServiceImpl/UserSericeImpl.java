package edu.sxu.service.userServiceImpl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import edu.sxu.dao.UserDao;
import edu.sxu.model.User;
import edu.sxu.service.UserService;

@Component(value="UserService")
@Scope(value="singleton")
public class UserSericeImpl implements UserService {
	//���ֱ����dao�������new һ�������൱�ڰ��������д����(������Щ��Ҫд���ļ�����Щ��Ҫд�����ݿ�)��
	//Ӧ�ô�spring�ж�̬���ã�spring IOC=���Ʒ�ת   �ѳ���Ա�Ķ���Ȩ�޸���spring�����ļ��У�
	//���Ҫ��������ļ�����Ҫ��dao����ע���������spring�����ļ��������setDao����ע�루DI = ����ע�� = denpendence injection��
	UserDao dao;
	public UserDao getDao() {
		return dao;
	}
//	ע��ķ�ʽע������
	@Resource(name="sqlDao")
	public void setDao(UserDao dao) {
		this.dao = dao;
	}
//	���ǹ��캯����ʽ��ע������
//	public UserSericeImpl(UserDao dao) {
//		super();
//		this.dao = dao;
//	}
	//����ط���Ӧ��UserService��Userservice��Ӧ��UserDao����
	//�����userdao���ж��ʵ�ַ����������е�Ҫд���ļ��У��е�Ҫд�����ݿ��У���ô��userService��ʵ����ҲҪʵ�ֶ������
	//���addӦ��������implementʵ�֣�һ����Ӧ��userdaoMysqlImpl  һ����Ӧ��userdaoFileImpl
	//������userServic������Ӧ��������Java�ļ���������action���õ�ʱ��ֻ��newһ��ʵ��������ô�û���ôȥ��̬���޸ĵ����ĸ�Java�ļ��أ�
	//�����õ���spring������

	@Override
	public void add(User user) {
		// dao�ķ�װ
		dao.save(user);
	}
	@Override
	public boolean checkUser(User user) {
		return dao.isExistUser(user);
	}
	@Override
	public User getUser(User user) {
		return dao.getUser(user);
	}

	
}
