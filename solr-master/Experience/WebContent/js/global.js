// ����ķ������˵�ַ
var URL = "localhost:8080/Experience";

//��������url����
function getParam(key) {

	//�����������url
	var url = location.href;

	// �õ��ʺ�֮����ַ���
	url = url.substring( url.indexOf('?') + 1 );
	
	/// ����&���ŷֽ��ַ���Ϊ����
	var params = url.split('&');

	//��������Ԫ��
	for (var i = 0;i < params.length;i ++) {			
		// ���յȺŷֽ�ɼ�ֵ��
		var str = params[i].split('=');
		
		if (str[0] == key) {
			return str[1];
		}
	}

}