/* 변수 선언 시 var 키워드를 사용 */
/* const : 상수화 */
/* 자바스크립트는 함수명도 인자값으로 지정 가능 */
/*
	function a(b, c) {
		for(var i = 0; i <= 5; i++) {
			console.log(b());
		}
	}
	
	function b() {
		...;
	}
 */

/* window.onload : 브라우저 창이 로드가 되는 대로 수행 */

/* null : 값이 없거나 비어있음, undefined : 값이 초기화 되지 않았음 */

/* 함수 정의 방식 */
/*
	1)
	function a(b, c, d, ...) {
		...;
	}
	
	2)
	var a = function (b, c, d, ...) {
		...;
	}
	
	3)
	var a = new function (b, c, d, ..., 함수 구현부); 
 */

/* 함수 호출시 유의사항 */
/* 
	1) 함수에 정의된 parameter 수보다 많은 인자 값을 전달하는 경우에는, 초과되는 인자 값들은 무시됨. 
	2) 함수에 정의된 parameter 수보다 적은 인자 값을 전달하는 경우에는 에러가 발생.
*/

/* 객체 생성/접근/변경/추가 */
/*
	1) 생성
	var object = {
		"속성이름1" : 속성 값;
		"속성이름2" : 속성 값;
		...
	}
	
	2) 접근
		1> object["속성이름"]
		2> object.속성이름
		
		* 가급적이면 1>의 방식을 사용하는 것을 권장
	
	3) 변경
	object["속성이름"] = 속성 값
	
	4) 추가
	object["객체에 없는 속성이름"] = 속성 값
	
	* 속성 이름이 현재 객체에 없는 속성인 경우, 동적으로 추가됨
*/

function hello2(name) {
	alert("Hello2 " + name);
	console.log("Hello2");
}


/* window : 브라우저에서 제공하는 창을 open */
/* opener : 부모 창의 window 객체 */
/* alert : 알림창, confirm : 확인/취소창, prompt : 입력창 */

/* navigator : 브라우저의 정보가 내장된 객체 */

/* location : 현재 페이지 주소와 연관된 정보 */

/* history : 브라우저의 페이지 이력 */


/* DOM (Document Object Model) : 계층 구조로 HTML을 표현 */
/* 최상위 node : document */

/*
	1) id 속성 찾기 :		  getElementById("id") 
	2) tagName 속성 찾기 :	  getElementsByTagName("tagName") 
	3) name 속성 찾기 :		  getElementsByName("name") 
	4) className 속성 찾기 : getElementsByClassName("className") 
 */

/* Event */
/* 마우스/키보드/Frame/Form 등의 객체에서 발생하는 이벤트에 대한 처리 */
/* <tag이름 on...="javascript:함수명();"> ... </tag이름> */
/*
	마우스 :  onclick/ondbclock/onmouseup/onmousedown/onmouseenter/onmouseleave
	키보드 :  onkeypress/onkeydown/onkeyup
	Frame : onload/onabort/onerror/onresize/onscroll
	Form :  onsubmit/onreset/onblur/onchange/onfocus/onselect
 */








