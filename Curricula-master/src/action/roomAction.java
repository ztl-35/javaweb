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
			//如果第一次启动，page是0.导致后面的计算会出错。
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
		//注意，这个地方是引用传递，所以在getrooms类中修改pager属性，在这个main方法中也会把对应的pager属性修改。
		List<Room> list = roomdao.getRooms(pager);
		//将数据存入到值栈中
		if (list.size()>0) {
			ServletActionContext.getContext().put("ROOMS", list);
			//设置导航条
			//1、要把当前页码放到中间
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
			//2、从起始到终点进行页面JSP的显示。需要对每一个页码进行跳转页面的设置
			for (int i = start; i <= end; i++) {
				stringBuilder.append("<a href="+request.getContextPath()+"/room/main?page="+i+">");
				//把当前页码突出显示
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
			//表示update是添加新数据操作而不是修改页面操作
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
