function valiPhone(phone) {
	var myreg = '/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/';
	if (!myreg.test(phone)) {
		alert('请输入有效的手机号码！');
		return false;
	}
}

function valihanzi(str){
	
}