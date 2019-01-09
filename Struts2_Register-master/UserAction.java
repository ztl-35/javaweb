package myuser;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class UserAction extends DispatchAction {

	public ActionForward add(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3)
			throws Exception {
		User user=(User)arg1;
		UserDAO userDAO = new UserDAO();
		userDAO.addUser(user);
		//�ڶ�Ӧ��xml�ļ������õ���תҳ��ļ�ֵ��
		return arg0.findForward("userlist");
	}
	public ActionForward modify(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3)
			throws Exception {
		User user = (User)arg1;
		UserDAO userDAO = new UserDAO();
		userDAO.modifyUser(user);
		return arg0.findForward("userlist");
	}
	public ActionForward delete(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3)
			throws Exception {
		User user = (User)arg1;
		UserDAO userDAO = new UserDAO();
		userDAO.deleteUser(user);
		
		//�������return�������ת������ҳ���ǲ��еģ�������ǰ�˴�����location.href����ת
		return null;
	}
	public ActionForward CheckExists(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3)
			throws Exception {
		User user=(User)arg1;
		UserDAO userDAO = new UserDAO();
		PrintWriter out = arg3.getWriter();
		if (userDAO.checkExists(user)) {
			System.out.println("�û����Ѿ�ע��");
			out.print("1");
		}else {
			out.print("0");
		}
		return null;
	}
	public ActionForward checkLogin(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3)
			throws Exception {
		User user=(User)arg1;
		UserDAO userDAO = new UserDAO();
		PrintWriter out = arg3.getWriter();
		System.out.println("user:::"+user.userName+"    "+user.pwd);
		if (userDAO.checkUserLogin(user)) {
			System.out.println("chenggong");
			out.print("1");
		}else {
			out.print("0");
		}
		return null;
	}
}
