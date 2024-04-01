function execDaumPostcode() {
	
	var width = 500; //팝업의 너비
	var height = 600; //팝업의 높이
	
	new daum.Postcode({
		width: width,
		height: height,
		oncomplete: function(data) {
			// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var roadAddr = data.roadAddress;		// 도로명 주소 변수
			var extraRoadAddr = '';					// 참고 항목 변수
			
			// 법정동명이 있을 경우 추가한다. (법정리는 제외)
			// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
			if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
				extraRoadAddr += data.bname;
			}
			
			// 건물명이 있고, 공동주택일 경우 추가한다.
			if(data.buildingName !== '' && data.apartment === 'Y'){
				extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
			}
			
			// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
			if(extraRoadAddr !== ''){
				extraRoadAddr = ' (' + extraRoadAddr + ')';
			}
			
			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById("postcode").value		= data.zonecode;
			document.getElementById("roadAddr").value		= roadAddr;
			document.getElementById("addr1").value			= data.jibunAddress;
			
			// 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
			if(roadAddr !== ''){
				document.getElementById("extraAddress").value = extraRoadAddr;
			}
			else {
				document.getElementById("extraAddress").value = '';
			}
			
			var guideTextBox = document.getElementById("guide");
			// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
			if(data.autoRoadAddress) {
				var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
				guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
				guideTextBox.style.display = 'block';
			
			}
			}
		}).open({
			left: (window.screen.width / 2) - (width / 2),
			top: (window.screen.height / 2) - (height / 2)
	});

}

function register() {
//	var check_id = check_id();
//	var check_pw = check_pw();
	
	if(!check_id()){
		alert("아이디를 확인해주세요");
		return;
	}
	
	if(!check_pw()){
		alert("비밀번호를 확인해주세요");
		return;
	}
	
	
	// 변경(if문 추가)
	var frmMain = document.getElementById("form");
	frmMain.action = "/backoffice/customer/writeProc.web";
	frmMain.submit();
	
	
}

function check_id(){
	var checkIdResult = document.getElementById("checkIdResult").value;
	
	if(checkIdResult == "false"){
		return false;
	}else{
		return true;
	}
}

function check_pw() {
	 
	var pw = document.getElementById('passwd').value;
	var SC = ["!","@","#","$","%"];
	var check_SC = 0;

	if(pw.length < 8 || pw.length16){
		window.alert('비밀번호는 8글자 이상, 16글자 이하만 이용 가능합니다.');
		document.getElementById('passwd').value='';
		return false;
	}
	for(var i=0;i<SC.length;i++){
		if(pw.indexOf(SC[i]) != -1){
			check_SC = 1;
		}
	}
	if(check_SC == 0){
		window.alert('!,@,#,$,% 의 특수문자가 들어가 있지 않습니다.')
		document.getElementById('passwd').value='';
		return false;
	}
	if(document.getElementById('passwd').value !='' && document.getElementById('passwd_').value!=''){
		if(document.getElementById('passwd').value==document.getElementById('passwd_').value){
			document.getElementById('checkPwd').innerHTML='비밀번호가 일치합니다.';
			document.getElementById('checkPwd').style.color='blue';
			return true;
		}
		else{
			document.getElementById('checkPwd').innerHTML='비밀번호가 일치하지 않습니다.';
			document.getElementById('checkPwd').style.color='red';
			return false;
		}
	}
	
}

/**
 * @author pluto#plutozone.com
 * @since 2016-01-07
 *
 * <p>DESCRIPTION: 값 얻기(Get Value)</p>
 * <p>IMPORTANT:</p>
 */
function getOptionValue(objThis) {
	if (!objThis) return "";
	return objThis.options[objThis.selectedIndex].value;
}

/**
 * @author pluto#plutozone.com
 * @since 2016-01-07
 *
 * <p>DESCRIPTION: 값 설정(Set Value)</p>
 * <p>IMPORTANT:</p>
 */
function setOption(objThis, value) {
	if (!objThis) return;
	
	for (var i = 0; i < objThis.options.length; i++) {
		if (objThis.options[i].value == value) {
			objThis.options.selectedIndex = i;
			break;
		}
	}
}

/**
 * @author pluto#plutozone.com
 * @since 2016-01-07
 *
 * <p>DESCRIPTION: 옵션 생성(Create Option)</p>
 * <p>IMPORTANT:</p>
 */
function createOption(objID, objStruct, defaultValue, setValue) {

	var combobox	= document.getElementById(objID);
	
	for (var i = 0; i < combobox.options.length; i++) {
		combobox.options.length = i;
	}
	
	var option		= null;
	if (defaultValue) {
		option = new Option(defaultValue, "");
		combobox.options[0] = option;
	}
		
	if (objStruct && objStruct.length > 0) {
		var j = 0;
		var optionCount = combobox.options.length;
		for (var i=optionCount; i < objStruct.length+optionCount; i++) {
			option = new Option(objStruct[j].text, objStruct[j].value);
			combobox.options[i] = option;
			j++;
		}
	}	
	setOption(combobox, setValue);
}

/**
 * @author pluto#plutozone.com
 * @since 2016-01-07
 *
 * <p>DESCRIPTION: 값 설정(Set Value)</p>
 * <p>IMPORTANT:</p>
 */
function setSelect(objThis, value) {
	if (!objThis) return;
	
	for (var i = 0; i < objThis.options.length; i++) {
		if (objThis.options[i].value == value) {
			objThis.options.selectedIndex = i;
			break;
		}
	}
}

/**
 * @author pluto#plutozone.com
 * @since 2016-01-07
 *
 * <p>DESCRIPTION: 셀렉트 생성(Create Select)</p>
 * <p>IMPORTANT:</p>
 */
function createSelect(objParentId, objID, objStruct, defaultValue, setValue, width) {
	var combobox		= document.createElement("select");
	combobox.id			= objID;
	combobox.name		= objID;
	
	if (width == undefined) width = "100";
	combobox.setAttribute("style", "width: " + width + "px; padding-top: 0px; padding-bottom: 0px;");
	
	var option		= null;
	if (defaultValue) {
		option = new Option(defaultValue, "");
		combobox.options[0] = option;
	}
	
	if (objStruct && objStruct.length > 0) {
		var j = 0;
		var optionCount = combobox.options.length;
		for (var i=optionCount; i < objStruct.length+optionCount; i++) {
			option = new Option(objStruct[j].text, objStruct[j].value);
			combobox.options[i] = option;
			
			j++;
		}
	}
	
	setSelect(combobox, setValue);
	document.getElementById(objParentId).appendChild(combobox);
}

/**
 * @author thepluto#hotmail.com
 * @since 2023-11-02
 *
 * <p>DESCRIPTION: 엘리먼트의 값을 쉼표로 표기</p>
 * <p>IMPORTANT:</p>
 */

function commaValue(object){
	var value		= object.value;
	value			= value.replaceAll(",", "");
	value			= value.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	object.value	= value;
}