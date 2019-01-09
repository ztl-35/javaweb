package mypicture;

import java.util.ArrayList;

public class pictureService {
	public static String getJson(ArrayList<Picture> pictures,String contextPath) {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("{");
		sBuilder.append("\"title\":\"picture1\",");
		sBuilder.append("\"id\":0,");
		sBuilder.append("\"start\":0,");
		sBuilder.append("\"data\":[");
		for (int i = 0; i < pictures.size(); i++) {
			sBuilder.append("{");
			sBuilder.append("\"alt\":\"picture\",");
			sBuilder.append("\"pid\":0,");
			sBuilder.append("\"src\":"+"\""+contextPath+"/"+pictures.get(i).getUrl()+"\",");
			sBuilder.append("\"thumb\":\"\"");
			sBuilder.append("}");
			if (i<pictures.size()-1) {
				sBuilder.append(",");
			}
		}
		sBuilder.append("]");
		sBuilder.append("}");
		return sBuilder.toString();
	}
}
