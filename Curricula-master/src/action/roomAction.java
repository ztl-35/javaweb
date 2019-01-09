package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;

import common.Pager;
import dao.roomDAO;
import model.Room;

public class roomAction {
	private roomDAO roomdao = new roomDAO();
	StringBuilder stringBuilder = new StringBuilder();
	private Room room;
	private int page;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public String main() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (page==0) {
			//�����һ��������page��0.���º���ļ�������
			page=1;
		}
		Pager pager = new Pager();
		roomdao.calulatePageCount(pager);
		if (page>pager.getPageCount()) {
			page = pager.getPageCount();
		}
		if (ServletActionContext.getContext().get("ADD")!=null) {
			page = pager.getPageCount();
		}
		pager.setPageNo(page);
		//ע�⣬����ط������ô��ݣ�������getrooms�����޸�pager���ԣ������main������Ҳ��Ѷ�Ӧ��pager�����޸ġ�
		List<Room> list = roomdao.getRooms(pager);
		//�����ݴ��뵽ֵջ��
		if (list.size()>0) {
			ServletActionContext.getContext().put("ROOMS", list);
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
				stringBuilder.append("<a href="+request.getContextPath()+"/room/main?page="+i+">");
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
		return "roommain";
	}
	public String update() {
		return updateIt();
	}
	private String updateIt() {
		if (room.getId()==0) {
			//��ʾupdate����������ݲ����������޸�ҳ�����
			ServletActionContext.getContext().put("ADD", true);
		}
		roomdao.update(room);
		return main();
	}
	public String modify() {
		return updateIt();
	}
	public String delete() {
		roomdao.deleteRoom(room);
		return null;
	}
	public String getRooms() throws IOException {
		room = roomdao.getRoom(room);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(room.getId()+"!"+room.getName()+"!"+room.getAddress());
		return null;
	}
}
