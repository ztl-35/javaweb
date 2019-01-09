package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import common.Pager;
import dao.teacherDAO;
import model.Teacher;

public class teacherAction {
	private teacherDAO teacherdao = new teacherDAO();
	StringBuilder stringBuilder = new StringBuilder();
	private Teacher teacher;
	private int page;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public String main() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (page==0) {
			//�����һ��������page��0.���º���ļ�������
			page=1;
		}
		Pager pager = new Pager();
		teacherdao.calulatePageCount(pager);
		if (page>pager.getPageCount()) {
			page = pager.getPageCount();
		}
		if (ServletActionContext.getContext().get("ADD")!=null) {
			page = pager.getPageCount();
		}
		pager.setPageNo(page);
		//ע�⣬����ط������ô��ݣ�������getteachers�����޸�pager���ԣ������main������Ҳ��Ѷ�Ӧ��pager�����޸ġ�
		List<Teacher> list = teacherdao.getTeachers(pager);
		//�����ݴ��뵽ֵջ��
		if (list.size()>0) {
			ServletActionContext.getContext().put("TEACHERS", list);
			//���õ�����
			//1��Ҫ�ѵ�ǰҳ��ŵ��м�
			int btncount = pager.getPageBtn();
			btncount = btncount/2;
			int start = page-btncount;
			int end = page+btncount;
			if (start<1) {
				start = 1;
				end = start+2*btncount;
				
			}
			if (end>pager.getPageCount()) {
				end = pager.getPageCount();
				start = end-2*btncount;
			}
			if (start<1) {
				start=1;
			}
			//2������ʼ���յ����ҳ��JSP����ʾ����Ҫ��ÿһ��ҳ�������תҳ�������
			for (int i = start; i <= end; i++) {
				stringBuilder.append("<a href="+request.getContextPath()+"/teacher/main?page="+i+">");
				//�ѵ�ǰҳ��ͻ����ʾ
				if (i==page) {
					stringBuilder.append("<font color=red><b>");
					stringBuilder.append(i);
					stringBuilder.append("</b></font>");
				}else {
					stringBuilder.append(i);
				}
				stringBuilder.append("</a>");
				stringBuilder.append("&nbsp&nbsp");
			}
			ServletActionContext.getContext().put("NVRBAR", stringBuilder.toString());
		}
		return "teachermain";
	}
	public String update() {
		return updateIt();
	}
	private String updateIt() {
		if (teacher.getId()==0) {
			//��ʾupdate����������ݲ����������޸�ҳ�����
			ServletActionContext.getContext().put("ADD", true);
		}
		teacherdao.update(teacher);
		return main();
	}
	public String modify() {
		return updateIt();
	}
	public String delete() {
		teacherdao.deleteTeacher(teacher);
		return null;
	}
	public String getTeachers() throws IOException {
		teacher = teacherdao.getTeacher(teacher);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(teacher.getId()+"!"+teacher.getName()+"!"+teacher.getSex()+"!"+teacher.getPhone());
		return null;
	}
}
