
/*$(document).ready(function(){
		console.log("nav_active");
		$('.nav-link').click(function () {
			console.log("nav-link click");
			if($('.nav-link').hasClass('active')) {
				console.log("nav-link hasClass active 그래서 remove");
				$('.nav-link').removeClass('active');
			}
			console.log("nav-link hasClass add active");
			$(this).addClass('active'); 
			
		})
});	*/

/*function fnCall(){
	console.log("fnCall="+$("#skey").val());
	
	var sKeyVal = $("#skey").val();
	console.log("fnCall=="+document.getElementById('skey').value);
	window.location.href = "/test/search.do?skey="+sKeyVal;
}*/

$(function() { 
	CKEDITOR.replace('contents',{ 
		filebrowserUploadUrl: 'fileUpload.do', 
		height: 500,
		enterMode: CKEDITOR.ENTER_BR,
		shiftEnterMode: CKEDITOR.ENTER_P
	});
	
	var editorStatus = false; //추후 form submit할 때 체크할 데이터
	var editor = CKEDITOR.instances.contents; //CKEDITOR.instances. 뒤에 textarea 의 id 값을 넣어줍니다.

	//editor 텍스트가 변경되면 fn_checkByte() 호출
	editor.on('change', function (event) { 
		fn_checkByte(this, 500);
	});
});

//ckeditor textarea 바이트 수 체크하는 함수
function fn_checkByte(obj, maxByte){
	var status = false; 
	var str = obj.getData(); //입력한 문자
	var totalByte = 0;
    const text_len = str.length; //입력한 문자수
    
    //totalByte 계산
    for(let i=0; i<text_len; i++){
    	const each_char = str.charAt(i);
        const uni_char = escape(each_char) //유니코드 형식으로 변환
        if(uni_char.length>4){
        	// 한글 : 2Byte
            totalByte += 2;
        }else{
        	// 영문,숫자,특수문자 : 1Byte
            totalByte += 1;
        }
    }
    
    if(totalByte>maxByte){
    	alert('최대 500Byte까지만 입력가능합니다.');
    	document.getElementById("nowByte").innerText = totalByte;
        document.getElementById("nowByte").style.color = "red";
        status = true;
    }else{
    	document.getElementById("nowByte").innerText = totalByte;
        document.getElementById("nowByte").style.color = "green";
    }
    
    //추후 form submit할때 체크할 데이터(true 일 경우 데이터 넘기지 않음-오류) 
    editorStatus = status;
    //자바스크립트 변수값을 JSP로 넘겨주기
    document.writeForm.editorStatus.value = editorStatus;
}


$(function() {
	$("#idCheckBtn").prop('disabled', true);
	$("#userId").keyup(function() {
        if($(this).val() != '') {
        	$("#idCheckBtn").prop('disabled', false);
        }
     });
});


